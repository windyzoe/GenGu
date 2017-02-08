package com.gengu.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gengu.util.DaoUtil;
import com.gengu.util.JdbcUtil;

/**
 * @author XUZH
 * 处理数据库中的仓库信息
 */
public class WareHouseDao
{
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final WareHouseDao single = new WareHouseDao();

	/**
	 * 单例模式
	 * @return
	 */
	public static WareHouseDao getInstance()
	{
		return single;
	}
	private WareHouseDao(){
		
	}
	public List<Map<String, Object>> getAllList() throws SQLException
	{
		List<Map<String, Object>> maplist = null;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			maplist = jdbcUtil.findResult("select * from warehouselist", null);
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
		List<Map<String, Object>> maplist=DaoUtil.getInstance().getPagingRows("warehouselist", currentPage);
		return maplist;
	}
	public void deleteRows(List<Integer> IDs) throws SQLException
	{
		DaoUtil.getInstance().deleteRows("warehouselist", IDs);
	}
	public void updateRows(List<Integer> IDs,Map<String, String> map) throws SQLException
	{
		DaoUtil.getInstance().updateRows("warehouselist", IDs, map);
	}
	/**
	 * 创建一行采购记录
	 * @param map	列名+值
	 * @throws SQLException 
	 */
	public void createOneList(Map<String, Object> map) throws SQLException
	{
		DaoUtil.getInstance().createOneTableLine("warehouselist", map);
	}
}
