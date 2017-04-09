package com.gengu.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gengu.dao.WareHouseDao;

public class WarehouseService
{
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final WarehouseService single = new WarehouseService();

	/**
	 * ����ģʽ
	 * @return
	 */
	public static WarehouseService getInstance()
	{
		return single;
	}
	private WarehouseService(){
		
	};
	
	/**
	 * ����һ�вɹ���
	 * @param map
	 * @throws SQLException 
	 */
	public void create(Map<String, Object> map) throws SQLException
	{
		WareHouseDao.getInstance().createOneList(map);
	}
	
	public List<Map<String, Object>> getAllList()
	{
		try
		{
			return WareHouseDao.getInstance().getAllList();
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
			return WareHouseDao.getInstance().getPaging(currentPage);
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
			WareHouseDao.getInstance().deleteRows(IDs);
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
			WareHouseDao.getInstance().updateRows(IDs, map);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
