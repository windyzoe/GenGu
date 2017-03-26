package com.gengu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	/**获取时间之前的时间
	 * @param date	日期
	 * @param value	间隔的时间
	 * @return
	 */
	public String getDateBefore(Date date,int value)
	{
		 Calendar c = Calendar.getInstance();
		 c.setTime(date);
		 int day = c.get(Calendar.DATE);  
		 c.set(Calendar.DATE, day-value);
		 String strDateBefore="";
		 SimpleDateFormat sdf = new SimpleDateFormat(DataType);
		 strDateBefore = sdf.format(c.getTime());
		 return strDateBefore;
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
	public String getDateType()
	{
		return DataType;
	}
}
