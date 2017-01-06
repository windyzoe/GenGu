package com.gengu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gengu.util.DaoUtil;
import com.gengu.util.JdbcUtil;

public class SupplierDao
{
	/**获得所有供应商的名称
	 * @return
	 * @throws SQLException
	 */
	public List<String> getAllSupplierName() throws SQLException
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
		}
		return strSupplierNameList;
	}
	public static void create(Map<String, Object> map) throws SQLException
	{
		DaoUtil.getInstance().createOneTableLine("supplier", map);
	}
}
