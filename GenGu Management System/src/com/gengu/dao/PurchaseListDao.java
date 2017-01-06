package com.gengu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
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
public class PurchaseListDao
{
	public static List<Map<String, Object>> getAllPurchaseList()
	{
		List<Map<String, Object>> maplist = null;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			maplist = jdbcUtil.findResult("select * from purchase", null);
			for (Map<String, Object> m : maplist)
			{
				System.out.println(m);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
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
	public static  void test(){
    	Map<String, Object> map=new HashMap<String, Object> ();
    	map.put("Classification", "1");
    	map.put("Model", "2");
    	map.put("UnitPrice", "3");
    	map.put("Quantity", "4");
    	map.put("Factory", "5");
    	map.put("BatchLot", "6");
    	map.put("OrderTime", new Date());
    	map.put("Company", "8");
    	map.put("PickingAddress", "9");
    	map.put("Distrabution", "Y");
    	map.put("Car", "11");
    	map.put("TansCost", "12");
    	map.put("PurchaseID", "aassd1");
    	map.put("CreateTime", "2016-10-11");
    	map.put("TotalPrice", "12");
    	try
		{
			createPurchaseList(map);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		test();
		getAllPurchaseList();
	}
}
