package com.gengu.services;

import com.gengu.dao.PurchaseDao;

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

}
