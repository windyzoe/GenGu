package com.gengu.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gengu.dao.PurchaseDao;

public class PurchaseService
{	
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final PurchaseService single = new PurchaseService();

	/**
	 * 单例模式
	 * @return
	 */
	public static PurchaseService getInstance()
	{
		return single;
	}
	private PurchaseService(){
		
	};
	
	/**
	 * 建立一行采购单
	 * @param map
	 * @throws SQLException 
	 */
	public void createPurchaseList(Map<String, Object> map) throws SQLException
	{
		PurchaseDao.getInstance().createPurchaseList(map);
	}
	
	public List<Map<String, Object>> getPurchaseList()
	{
		try
		{
			return PurchaseDao.getInstance().getAllPurchaseList();
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
