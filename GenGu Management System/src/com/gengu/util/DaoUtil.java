package com.gengu.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gengu.common.Constants;

/**
 * 数据库操作层面的工具类
 * @author XUZH
 *
 */
public class DaoUtil
{
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final DaoUtil single = new DaoUtil();
	/**
	 * 单例模式
	 * @return
	 */
	public static DaoUtil getInstance()
	{
		return single;
	}
	/**
	 * 私有构造器
	 */
	private DaoUtil()
	{
	}
	/**
	 * @param sqlFirst	SQL前面的命令(insert into TABLE)
	 * @param list	列名称
	 * @return	PreparedStatement版的SQL语句
	 */
	public String getSQLString(String sqlFirst,List<String> list)
	{
		int size=list.size();
		StringBuffer strSQL=new StringBuffer();
		strSQL.append(sqlFirst);
		strSQL.append("(");
		for(String strTemp : list)
		{
			strSQL.append(strTemp);
			strSQL.append(",");
		}
		strSQL.deleteCharAt(strSQL.lastIndexOf(","));
		strSQL.append(") values(");
		for(int i=0;i<size;i++)
		{
			strSQL.append("?");
			strSQL.append(",");
		}
		strSQL.deleteCharAt(strSQL.lastIndexOf(","));
		strSQL.append(")");
		System.out.println(strSQL);
		return strSQL.toString();
	}
	/**在一个表单创建一行的公共方法
	 * @param strTableName
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public void createOneTableLine(String strTableName , Map<String, Object> map) throws SQLException
	{
		List<String> strNameList=new ArrayList<>();
		List<Object> strValueList=new ArrayList<>();
		for (Map.Entry<String, Object> entry : map.entrySet())
		{
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			strNameList.add(entry.getKey());
			strValueList.add(entry.getValue());
		}
		String strSQL =getSQLString("insert into "+strTableName, strNameList);
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			jdbcUtil.updateByPreparedStatement(strSQL, strValueList);
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			jdbcUtil.releaseConn();
		}
	}
	/**获得某个表的记录总数
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public int countTableRows(String tableName) throws SQLException
	{
		int count=0;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<Map<String, Object>> maplist;
		try
		{
			maplist = jdbcUtil.findResult("select count(*) from "+tableName, null);
			Map<String, Object> map = maplist.get(0);
			for (Map.Entry<String, Object> entry : map.entrySet())
			{
				count=(int)entry.getValue();
			}
		} catch (SQLException e)
		{
			throw e;
		}finally {
			jdbcUtil.releaseConn();
		}
		return count;
	}
	/**
	 * 获得某个表单的分页数据
	 * @param tableName
	 * 表单名
	 * @param currentPage
	 * 当前页
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getPagingRows(String tableName , int currentPage) throws SQLException
	{
		List<Map<String, Object>> maplist = null;
		int count = countTableRows(tableName);
		int offset = (currentPage-1)*Constants.ROWSIZE;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			maplist = jdbcUtil.findResult("select * from "+tableName+" order by id desc offset "+offset+" rows fetch next "+Constants.ROWSIZE+" rows only", null);
		} catch (SQLException e)
		{
			throw e;
		}finally {
			jdbcUtil.releaseConn();
		}
		return maplist;
	}
}
