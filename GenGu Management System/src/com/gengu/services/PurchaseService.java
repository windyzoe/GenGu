package com.gengu.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gengu.dao.PurchaseDao;

public class PurchaseService
{	
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final PurchaseService single = new PurchaseService();

	/**
	 * ����ģʽ
	 * @return
	 */
	public static PurchaseService getInstance()
	{
		return single;
	}
	private PurchaseService(){
		
	};
	
	/**
	 * ����һ�вɹ���
	 * @param map
	 * @throws SQLException 
	 */
	public void createPurchaseList(Map<String, Object> map) throws SQLException
	{
		PurchaseDao.getInstance().createOneList(map);
	}
	
	public List<Map<String, Object>> getPurchaseList()
	{
		try
		{
			return PurchaseDao.getInstance().getAllList();
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public List<Map<String, Object>> getPaging(int currentPage)
	{
		try
		{
			return PurchaseDao.getInstance().getPaging(currentPage);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
