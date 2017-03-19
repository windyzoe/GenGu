package com.gengu.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gengu.dao.CarDao;
import com.gengu.dao.CustomerDao;
import com.gengu.dao.SaleDao;

public class CarService
{
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final CarService single = new CarService();

	/**
	 * ����ģʽ
	 * 
	 * @return
	 */
	public static CarService getInstance()
	{
		return single;
	}

	private CarService(){
		
	};

	public boolean create(Map<String, Object> map)
	{
		try
		{
			CarDao.getInstance().create(map);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<String> getNames()
	{
		List<String> strNameList = new ArrayList<>();
		try
		{
			strNameList = CarDao.getInstance().getAllNames();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return strNameList;
	}
	public boolean deleteRows(List<Object> IDs)
	{
		try
		{
			CarDao.getInstance().deleteRows(IDs);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
