package com.gengu.util;

/**
 * �������ڹ�����
 * @author XUZH
 *
 */
public class DateUtil
{
	private static final String DataType="yyyy-mm-dd";
	private static final String TimeType="yyyy-mm-ddhh:mm:ss.f...";
	
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final DateUtil single = new DateUtil();
	/**
	 * ����ģʽ
	 * @return
	 */
	public static DateUtil getInstance()
	{
		return single;
	}
	/**
	 * ˽�й�����
	 */
	private DateUtil()
	{
		System.out.println("xxx");
	}
}
