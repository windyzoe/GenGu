package com.gengu.util;

import java.util.List;

/**
 * 数据库操作层面的工具类
 * @author XUZH
 *
 */
public class DaoUtil
{
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final DaoUtil single = new DaoUtil();
	/**
	 * 单例模式
	 * @return
	 */
	public static DaoUtil getInstance()
	{
		return single;
	}
	/**
	 * 私有构造器
	 */
	private DaoUtil()
	{
		System.out.println("DaoUtil");
	}
	/**
	 * @param sqlFirst	SQL前面的命令(insert into TABLE)
	 * @param list	列名称
	 * @return	PreparedStatement版的SQL语句
	 */
	public String getSQLString(String sqlFirst,List<String> list)
	{
		int size=list.size();
		StringBuffer strSQL=new StringBuffer();
		strSQL.append(sqlFirst);
		strSQL.append("(");
		for(String strTemp : list)
		{
			strSQL.append(strTemp);
			strSQL.append(",");
		}
		strSQL.deleteCharAt(strSQL.lastIndexOf(","));
		strSQL.append(") values(");
		for(int i=0;i<size;i++)
		{
			strSQL.append("?");
			strSQL.append(",");
		}
		strSQL.deleteCharAt(strSQL.lastIndexOf(","));
		strSQL.append(")");
		System.out.println(strSQL);
		return strSQL.toString();
	}

}
