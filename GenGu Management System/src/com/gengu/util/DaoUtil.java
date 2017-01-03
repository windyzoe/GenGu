package com.gengu.util;

import java.util.List;

/**
 * ���ݿ��������Ĺ�����
 * @author XUZH
 *
 */
public class DaoUtil
{
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final DaoUtil single = new DaoUtil();
	/**
	 * ����ģʽ
	 * @return
	 */
	public static DaoUtil getInstance()
	{
		return single;
	}
	/**
	 * ˽�й�����
	 */
	private DaoUtil()
	{
		System.out.println("DaoUtil");
	}
	/**
	 * @param sqlFirst	SQLǰ�������(insert into TABLE)
	 * @param list	������
	 * @return	PreparedStatement���SQL���
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
