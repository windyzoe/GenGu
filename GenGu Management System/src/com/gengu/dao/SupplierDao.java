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
	 * 饿汉单例模式,线程安全
	 */
	private static final SupplierDao single = new SupplierDao();

	/**
	 * 单例模式
	 * @return
	 */
	public static SupplierDao getInstance()
	{
		return single;
	}
	private SupplierDao(){
		
	};
	/**获得所有供应商的名称
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
	/**创建一个供应商
	 * @param map
	 * @throws SQLException
	 */
	public void create(Map<String, Object> map) throws SQLException
	{
		DaoUtil.getInstance().createOneTableLine("supplier", map);
	}
}
