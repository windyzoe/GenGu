package com.gengu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.gengu.common.Constants;

public class JdbcUtil
{
	// 表示定义数据库的用户名
	private static String USERNAME;

	// 定义数据库的密码
	private static String PASSWORD;

	// 定义数据库的驱动信息
	private static String DRIVER;

	// 定义访问数据库的地址
	private static String URL;

	// 定义数据库的链接
	private Connection connection;

	// 定义sql语句的执行对象
	private PreparedStatement pstmt;

	// 定义查询返回的结果集合
	private ResultSet resultSet;

	static
	{
		// 加载数据库配置信息，并给相关的属性赋值
		loadConfig();
	}

	/**
	 * 加载数据库配置信息，并给相关的属性赋值
	 */
	public static void loadConfig()
	{
		try
		{
			InputStream inStream = new FileInputStream(new File(Constants.PATH_JDBCProperty));
			Properties prop = new Properties();
			prop.load(inStream);
			USERNAME = prop.getProperty("jdbc.username");
			PASSWORD = prop.getProperty("jdbc.password");
			DRIVER = prop.getProperty("jdbc.driver");
			URL = prop.getProperty("jdbc.url");
			System.out.println("USERNAME: " + USERNAME);
		} catch (Exception e)
		{
			throw new RuntimeException("读取数据库配置文件异常！", e);
		}
	}

	public JdbcUtil()
	{

	}

	/**
	 * 获取数据库连接
	 * 
	 * @return 数据库连接
	 */
	public Connection getConnection()
	{
		try
		{
			Class.forName(DRIVER).newInstance(); // 注册驱动
			String connectURL = URL + ";user=" + USERNAME + ";password=" + PASSWORD + ";bootPassword=Aa123456";
			connection = DriverManager.getConnection(connectURL); // 获取连接
		} catch (Exception e)
		{
			throw new RuntimeException("get connection error!", e);
		}
		return connection;
	}

	/**
	 * 执行更新操作
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            执行参数
	 * @return 执行结果
	 * @throws SQLException
	 */
	public boolean updateByPreparedStatement(String sql, List<?> params) throws SQLException
	{
		boolean flag = false;
		int result = -1;// 表示当用户执行添加删除和修改的时候所影响数据库的行数
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		// 填充sql语句中的占位符
		if (params != null && !params.isEmpty())
		{
			for (int i = 0; i < params.size(); i++)
			{
				pstmt.setObject(index++, params.get(i));
			}
		}
		result = pstmt.executeUpdate();
		flag = result > 0 ? true : false;
		return flag;
	}

	/**
	 * 批量更新操作
	 * @param sql
	 * 			带问号的SQL语句
	 * @param paramList
	 * 			这个是PreparedStatement里面的所有参数(二位数组)
	 * @throws SQLException 
	 */
	public void updateManyByPreparedStatement(String sql, List<List<?>> paramList) throws SQLException
	{
		try
		{
			connection.setAutoCommit(false);//事务设计成不自动提交,因为是批处理,如果其中一步出错,则不提交
			pstmt = connection.prepareStatement(sql);
			for (int i = 0; i < paramList.size(); i++)
			{
				List<?> params = paramList.get(i);
				int index = 1;
				// 填充sql语句中的占位符
				if (params != null && !params.isEmpty())
				{
					for (int j = 0; j < params.size(); j++)
					{
						pstmt.setObject(index++, params.get(j));
					}
					pstmt.addBatch();
				}
			}
			pstmt.executeBatch();
			connection.commit();// 执行完后，手动提交事务
		} catch (SQLException e)
		{
			connection.rollback();
			throw e;
		}

	}

	/**
	 * 执行查询操作
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            执行参数
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findResult(String sql, List<?> params) throws SQLException
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty())
		{
			for (int i = 0; i < params.size(); i++)
			{
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next())
		{
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++)
			{
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null)
				{
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 释放资源
	 */
	public void releaseConn()
	{
		if (resultSet != null)
		{
			try
			{
				resultSet.close();
			} catch (SQLException e)
			{
				printSQLException(e);
			}
		}
		if (pstmt != null)
		{
			try
			{
				pstmt.close();
			} catch (SQLException e)
			{
				printSQLException(e);
			}
		}
		if (connection != null)
		{
			try
			{
				connection.close();
			} catch (SQLException e)
			{
				printSQLException(e);
			}
		}
		shutdownEngine();
	}

	/**
	 * Derby断开连接的方式(此方式能完全断开,不需要用户名密码)
	 */
	private void shutdownEngine()
	{
		String shutdownURL = "jdbc:derby:;shutdown=true";
		try
		{
			System.out.println("Shutting down engine via this URL: " + shutdownURL);
			DriverManager.getConnection(shutdownURL);
		} catch (SQLException se)
		{
			if (se.getSQLState().equals("XJ015"))
			{
				System.out.println("Derby engine shut down normally");
			} else
			{
				printSQLException(se);
			}
		}
	}

	/**
	 * Print a list of SQLExceptions.
	 */
	private void printSQLException(SQLException sqle)
	{
		while (sqle != null)
		{
			System.out.println("\n---SQLException Caught---\n");
			System.out.println(" SQLState: " + (sqle).getSQLState());
			System.out.println(" Severity: " + (sqle).getErrorCode());
			System.out.println(" Message: " + (sqle).getMessage());
			sqle.printStackTrace();
			sqle = sqle.getNextException();
		}
	}

	public static void main(String[] args)
	{
		JdbcUtil jdbcUtil1 = new JdbcUtil();
		JdbcUtil jdbcUtil2 = new JdbcUtil();
		if (jdbcUtil1.getConnection()==jdbcUtil2.getConnection())
		{
			System.out.println("竟然一样");
		}else {
			System.out.println("确实不一样");
		}
	}

}
