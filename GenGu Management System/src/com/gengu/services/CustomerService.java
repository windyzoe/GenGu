package com.gengu.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gengu.dao.CustomerDao;
import com.gengu.dao.PurchaseDao;
import com.gengu.dao.SupplierDao;

/**
 * 客户业务类
 * @author XUZH
 *
 */
public class CustomerService
{
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final CustomerService single = new CustomerService();

	/**
	 * 单例模式
	 * @return
	 */
	public static CustomerService getInstance()
	{
		return single;
	}
	private CustomerService(){
		
	};
	public boolean create(Map<String, Object> map)
	{
		try
		{
			CustomerDao.getInstance().create(map);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<String> getNames()
	{
		List<String> strNameList=new ArrayList<>();
		try
		{
			strNameList =CustomerDao.getInstance().getAllNames();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return strNameList;
	}
}
