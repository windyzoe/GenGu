package com.gengu.dao;

public class CustomerDao
{
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final CustomerDao single = new CustomerDao();

	/**
	 * ����ģʽ
	 * @return
	 */
	public static CustomerDao getInstance()
	{
		return single;
	}
	private CustomerDao(){
		
	};
}
