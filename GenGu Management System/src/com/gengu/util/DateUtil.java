package com.gengu.util;

/**
 * 处理日期工具类
 * @author XUZH
 *
 */
public class DateUtil
{
	private static final String DataType="yyyy-mm-dd";
	private static final String TimeType="yyyy-mm-ddhh:mm:ss.f...";
	
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final DateUtil single = new DateUtil();
	/**
	 * 单例模式
	 * @return
	 */
	public static DateUtil getInstance()
	{
		return single;
	}
	/**
	 * 私有构造器
	 */
	private DateUtil()
	{
		System.out.println("xxx");
	}
}
