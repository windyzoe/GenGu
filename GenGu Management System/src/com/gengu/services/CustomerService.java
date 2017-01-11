package com.gengu.services;

import com.gengu.dao.PurchaseDao;

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

}
