package com.gengu.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
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
		
	};
	public static List<Map<String, Object>> getAllPurchaseList() throws SQLException
	{
		List<Map<String, Object>> maplist = null;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			maplist = jdbcUtil.findResult("select * from purchaselist", null);
			for (Map<String, Object> m : maplist)
			{
				System.out.println(m);
			}
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			jdbcUtil.releaseConn();
		}
		return maplist;
	}
	/**
	 * 创建一行采购记录
	 * @param map	列名+值
	 * @throws SQLException 
	 */
	public static void createPurchaseList(Map<String, Object> map) throws SQLException
	{
		DaoUtil.getInstance().createOneTableLine("PURCHASELIST", map);
	}
	public static void main(String[] args){
		try
		{
			getAllPurchaseList();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
