package com.gengu.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gengu.util.DaoUtil;
import com.gengu.util.JdbcUtil;

/**
 * 采购单的数据库操作
 * @author XUZH
 *
 */
public class PurchaseDao
{
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final PurchaseDao single = new PurchaseDao();

	/**
	 * 单例模式
	 * @return
	 */
	public static PurchaseDao getInstance()
	{
		return single;
	}
	private PurchaseDao(){
		
	}
	public List<Map<String, Object>> getAllList() throws SQLException
	{
		List<Map<String, Object>> maplist = null;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			maplist = jdbcUtil.findResult("select * from purchaselist", null);
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			jdbcUtil.releaseConn();
		}
		return maplist;
	}
	public List<Map<String, Object>> getTransportCost(String strBeforeDate , String strAfterDate) throws SQLException
	{
		List<Map<String, Object>> maplist = null;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			maplist = jdbcUtil.findResult("SELECT SUM(TansCost) AS COST FROM purchaselist where OrderTime Between Date('"+strBeforeDate+"') and Date('"+strAfterDate+"')", null);
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
		List<Map<String, Object>> maplist=DaoUtil.getInstance().getPagingRows("purchaselist", currentPage);
		return maplist;
	}
	public void deleteRows(List<Object> IDs) throws SQLException
	{
		DaoUtil.getInstance().deleteRows("purchaselist", IDs);
	}
	public void updateRows(List<Integer> IDs,Map<String, String> map) throws SQLException
	{
		DaoUtil.getInstance().updateRows("purchaselist", IDs, map);
	}
	/**
	 * 创建一行采购记录
	 * @param map	列名+值
	 * @throws SQLException 
	 */
	public void createOneList(Map<String, Object> map) throws SQLException
	{
		DaoUtil.getInstance().createOneTableLine("PURCHASELIST", map);
	}
	public static void main(String[] args)
	{
		try
		{
			List<Map<String, Object>> maplist = PurchaseDao.getInstance().getTransportCost("2017-03-02","2017-04-01");
			for (Map<String, Object> map : maplist)
			{
				System.out.println(map.get("COST"));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
