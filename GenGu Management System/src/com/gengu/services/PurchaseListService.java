package com.gengu.services;

import java.util.Map;

public class PurchaseListService
{	
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final PurchaseListService single = new PurchaseListService();

	/**
	 * ����ģʽ
	 * @return
	 */
	public static PurchaseListService getInstance()
	{
		return single;
	}
	private PurchaseListService(){
		
	};
	
	/**
	 * ����һ�вɹ���
	 * @param map
	 */
	public void createPurchaseList(Map<String, String> map)
	{
		
	}
}
