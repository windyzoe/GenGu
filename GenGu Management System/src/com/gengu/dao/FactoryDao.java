package com.gengu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gengu.util.DaoUtil;
import com.gengu.util.JdbcUtil;

public class FactoryDao
{
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final FactoryDao single = new FactoryDao();

	/**
	 * ����ģʽ
	 * @return
	 */
	public static FactoryDao getInstance()
	{
		return single;
	}
	private FactoryDao(){
		
	};
	/**������пͻ�������
	 * @return
	 * @throws SQLException
	 */
	public List<String> getAllNames() throws SQLException
	{
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<String> strNameList = new ArrayList<>();
		try
		{
			List<Map<String, Object>> templist = jdbcUtil.findResult("select ID from factory", null);
			for (Map<String, Object> map : templist)
			{
				for (Map.Entry<String, Object> entry : map.entrySet())
				{
					strNameList.add(entry.getValue().toString());
				}
			}
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			jdbcUtil.releaseConn();
		}
		return strNameList;
	}
	/**����һ����Ӧ��
	 * @param map
	 * @throws SQLException
	 */
	public void create(Map<String, Object> map) throws SQLException
	{
		DaoUtil.getInstance().createOneTableLine("factory", map);
	}
	public void deleteRows(List<Object> IDs) throws SQLException
	{
		DaoUtil.getInstance().deleteRows("factory", IDs);
	}
}
