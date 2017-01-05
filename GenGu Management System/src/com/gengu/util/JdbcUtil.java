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
	// ��ʾ�������ݿ���û���
	private static String USERNAME;

	// �������ݿ������
	private static String PASSWORD;

	// �������ݿ��������Ϣ
	private static String DRIVER;

	// ����������ݿ�ĵ�ַ
	private static String URL;

	// �������ݿ������
	private Connection connection;

	// ����sql����ִ�ж���
	private PreparedStatement pstmt;

	// �����ѯ���صĽ������
	private ResultSet resultSet;

	static
	{
		// �������ݿ�������Ϣ��������ص����Ը�ֵ
		loadConfig();
	}

	/**
	 * �������ݿ�������Ϣ��������ص����Ը�ֵ
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
			throw new RuntimeException("��ȡ���ݿ������ļ��쳣��", e);
		}
	}

	public JdbcUtil()
	{

	}

	/**
	 * ��ȡ���ݿ�����
	 * 
	 * @return ���ݿ�����
	 */
	public Connection getConnection()
	{
		try
		{
			Class.forName(DRIVER).newInstance(); // ע������
			String connectURL = URL + ";user=" + USERNAME + ";password=" + PASSWORD + ";bootPassword=Aa123456";
			connection = DriverManager.getConnection(connectURL); // ��ȡ����
		} catch (Exception e)
		{
			throw new RuntimeException("get connection error!", e);
		}
		return connection;
	}

	/**
	 * ִ�и��²���
	 * 
	 * @param sql
	 *            sql���
	 * @param params
	 *            ִ�в���
	 * @return ִ�н��
	 * @throws SQLException
	 */
	public boolean updateByPreparedStatement(String sql, List<?> params) throws SQLException
	{
		boolean flag = false;
		int result = -1;// ��ʾ���û�ִ�����ɾ�����޸ĵ�ʱ����Ӱ�����ݿ������
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		// ���sql����е�ռλ��
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
	 * �������²���
	 * @param sql
	 * 			���ʺŵ�SQL���
	 * @param paramList
	 * 			�����PreparedStatement��������в���(��λ����)
	 * @throws SQLException 
	 */
	public void updateManyByPreparedStatement(String sql, List<List<?>> paramList) throws SQLException
	{
		try
		{
			connection.setAutoCommit(false);//������Ƴɲ��Զ��ύ,��Ϊ��������,�������һ������,���ύ
			pstmt = connection.prepareStatement(sql);
			for (int i = 0; i < paramList.size(); i++)
			{
				List<?> params = paramList.get(i);
				int index = 1;
				// ���sql����е�ռλ��
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
			connection.commit();// ִ������ֶ��ύ����
		} catch (SQLException e)
		{
			connection.rollback();
			throw e;
		}

	}

	/**
	 * ִ�в�ѯ����
	 * 
	 * @param sql
	 *            sql���
	 * @param params
	 *            ִ�в���
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
	 * �ͷ���Դ
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
	 * Derby�Ͽ����ӵķ�ʽ(�˷�ʽ����ȫ�Ͽ�,����Ҫ�û�������)
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
			System.out.println("��Ȼһ��");
		}else {
			System.out.println("ȷʵ��һ��");
		}
	}

}
