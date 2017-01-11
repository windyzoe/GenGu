package com.gengu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 处理日期工具类
 * @author XUZH
 *
 */
public class DateUtil
{
	private static final String DataType="yyyy-MM-dd";
	private static final String TimeType="yyyy-MM-dd hh:mm:ss";
	
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
	}
	/**当前日期
	 * @return
	 */
	public String getNowDate()
	{
		String strNowDate="";
		SimpleDateFormat sdf = new SimpleDateFormat(DataType);
		strNowDate = sdf.format(new Date());
		return strNowDate;
	}
	/**当前时间
	 * @return
	 */
	public String getNowTime()
	{
		String strNowTime="";
		SimpleDateFormat sdf = new SimpleDateFormat(TimeType);
		strNowTime = sdf.format(new Date());
		return strNowTime;
	}
}
