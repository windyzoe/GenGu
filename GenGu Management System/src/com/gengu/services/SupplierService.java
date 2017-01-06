package com.gengu.services;

import java.sql.SQLException;
import java.util.Map;

import com.gengu.dao.SupplierDao;

public class SupplierService
{
	public boolean create(Map<String, Object> map)
	{
		try
		{
			SupplierDao.create(map);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
