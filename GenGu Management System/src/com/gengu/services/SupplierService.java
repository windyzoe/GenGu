package com.gengu.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gengu.dao.SupplierDao;

public class SupplierService
{
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final SupplierService single = new SupplierService();

	/**
	 * ����ģʽ
	 * @return
	 */
	public static SupplierService getInstance()
	{
		return single;
	}
	private SupplierService(){
		
	};
	public boolean create(Map<String, Object> map)
	{
		try
		{
			SupplierDao.getInstance().create(map);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<String> getNames()
	{
		List<String> strSupplierNameList=new ArrayList<>();
		try
		{
			strSupplierNameList =SupplierDao.getInstance().getAllNames();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return strSupplierNameList;
	}
}
