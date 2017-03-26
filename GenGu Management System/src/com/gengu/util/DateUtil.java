package com.gengu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * �������ڹ�����
 * @author XUZH
 *
 */
public class DateUtil
{
	private static final String DataType="yyyy-MM-dd";
	private static final String TimeType="yyyy-MM-dd hh:mm:ss";
	
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
	}
	/**��ǰ����
	 * @return
	 */
	public String getNowDate()
	{
		String strNowDate="";
		SimpleDateFormat sdf = new SimpleDateFormat(DataType);
		strNowDate = sdf.format(new Date());
		return strNowDate;
	}
	/**��ȡʱ��֮ǰ��ʱ��
	 * @param date	����
	 * @param value	�����ʱ��
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
	/**��ǰʱ��
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
