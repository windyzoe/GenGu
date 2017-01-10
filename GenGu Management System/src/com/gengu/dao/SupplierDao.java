package com.gengu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gengu.util.DaoUtil;
import com.gengu.util.JdbcUtil;

public class SupplierDao
{
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final SupplierDao single = new SupplierDao();

	/**
	 * ����ģʽ
	 * @return
	 */
	public static SupplierDao getInstance()
	{
		return single;
	}
	private SupplierDao(){
		
	};
	/**������й�Ӧ�̵�����
	 * @return
	 * @throws SQLException
	 */
	public List<String> getAllNames() throws SQLException
	{
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		List<String> strSupplierNameList = new ArrayList<>();
		try
		{
			List<Map<String, Object>> templist = jdbcUtil.findResult("select Name from supplier", null);
			for (Map<String, Object> map : templist)
			{
				for (Map.Entry<String, Object> entry : map.entrySet())
				{
					strSupplierNameList.add(entry.getValue().toString());
				}
			}
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			jdbcUtil.releaseConn();
			System.out.println("release supplier");
		}
		return strSupplierNameList;
	}
	/**����һ����Ӧ��
	 * @param map
	 * @throws SQLException
	 */
	public void create(Map<String, Object> map) throws SQLException
	{
		DaoUtil.getInstance().createOneTableLine("supplier", map);
	}
}
