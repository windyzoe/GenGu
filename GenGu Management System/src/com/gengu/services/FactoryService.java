package com.gengu.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gengu.dao.CarDao;
import com.gengu.dao.CustomerDao;
import com.gengu.dao.FactoryDao;

public class FactoryService
{
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final FactoryService single = new FactoryService();

	/**
	 * ����ģʽ
	 * 
	 * @return
	 */
	public static FactoryService getInstance()
	{
		return single;
	}

	private FactoryService(){
		
	};

	public boolean create(Map<String, Object> map)
	{
		try
		{
			FactoryDao.getInstance().create(map);
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
			strNameList = FactoryDao.getInstance().getAllNames();
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
			FactoryDao.getInstance().deleteRows(IDs);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
