package com.gengu.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gengu.dao.SaleDao;

public class SaleService
{	
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final SaleService single = new SaleService();

	/**
	 * ����ģʽ
	 * @return
	 */
	public static SaleService getInstance()
	{
		return single;
	}
	private SaleService(){
		
	};
	
	/**
	 * ����һ�вɹ���
	 * @param map
	 * @throws SQLException 
	 */
	public void create(Map<String, Object> map) throws SQLException
	{
		SaleDao.getInstance().createOneList(map);
	}
	
	public List<Map<String, Object>> getAllList()
	{
		try
		{
			return SaleDao.getInstance().getAllList();
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
			return SaleDao.getInstance().getPaging(currentPage);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public boolean deleteRows(List<Object> IDs)
	{
		try
		{
			SaleDao.getInstance().deleteRows(IDs);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean modifyRows(List<Integer> IDs,Map<String, String> map)
	{
		try
		{
			SaleDao.getInstance().updateRows(IDs, map);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
