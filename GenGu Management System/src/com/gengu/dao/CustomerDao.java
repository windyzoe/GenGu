package com.gengu.dao;

public class CustomerDao
{
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final CustomerDao single = new CustomerDao();

	/**
	 * 单例模式
	 * @return
	 */
	public static CustomerDao getInstance()
	{
		return single;
	}
	private CustomerDao(){
		
	};
}
