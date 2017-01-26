package com.gengu.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gengu.util.DaoUtil;
import com.gengu.util.JdbcUtil;

/**
 * 销售单的数据库操作
 * @author XUZH
 *
 */
public class SaleDao
{
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final SaleDao single = new SaleDao();

	/**
	 * 单例模式
	 * @return
	 */
	public static SaleDao getInstance()
	{
		return single;
	}
	private SaleDao(){
		
	}
	public List<Map<String, Object>> getAllList() throws SQLException
	{
		List<Map<String, Object>> maplist = null;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			maplist = jdbcUtil.findResult("select * from salelist", null);
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			jdbcUtil.releaseConn();
		}
		return maplist;
	}
	public List<Map<String, Object>> getPaging(int currentPage) throws SQLException
	{
		List<Map<String, Object>> maplist=DaoUtil.getInstance().getPagingRows("salelist", currentPage);
		return maplist;
	}
	public void deleteRows(List<Integer> IDs) throws SQLException
	{
		DaoUtil.getInstance().deleteRows("salelist", IDs);
	}
	public void updateRows(List<Integer> IDs,Map<String, String> map) throws SQLException
	{
		DaoUtil.getInstance().updateRows("salelist", IDs, map);
	}
	/**
	 * 创建一行采购记录
	 * @param map	列名+值
	 * @throws SQLException 
	 */
	public void createOneList(Map<String, Object> map) throws SQLException
	{
		DaoUtil.getInstance().createOneTableLine("salelist", map);
	}
}