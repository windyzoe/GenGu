package com.gengu.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	/**��һ��������һ�еĹ�������
	 * @param strTableName
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public void createOneTableLine(String strTableName , Map<String, Object> map) throws SQLException
	{
		List<String> strNameList=new ArrayList<>();
		List<Object> strValueList=new ArrayList<>();
		for (Map.Entry<String, Object> entry : map.entrySet())
		{
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			strNameList.add(entry.getKey());
			strValueList.add(entry.getValue());
		}
		String strSQL =getSQLString("insert into "+strTableName, strNameList);
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			jdbcUtil.updateByPreparedStatement(strSQL, strValueList);
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			jdbcUtil.releaseConn();
		}
	}
}
