package com.gengu.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gengu.dao.CustomerDao;
import com.gengu.dao.PurchaseDao;
import com.gengu.dao.SupplierDao;

/**
 * �ͻ�ҵ����
 * @author XUZH
 *
 */
public class CustomerService
{
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final CustomerService single = new CustomerService();

	/**
	 * ����ģʽ
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
