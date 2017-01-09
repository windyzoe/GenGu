package com.gengu.services;

import java.util.Map;

public class PurchaseListService
{	
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final PurchaseListService single = new PurchaseListService();

	/**
	 * 单例模式
	 * @return
	 */
	public static PurchaseListService getInstance()
	{
		return single;
	}
	private PurchaseListService(){
		
	};
	
	/**
	 * 建立一行采购单
	 * @param map
	 */
	public void createPurchaseList(Map<String, String> map)
	{
		
	}
}
