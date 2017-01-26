package com.gengu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gengu.util.DaoUtil;
import com.gengu.util.JdbcUtil;

public class CustomerDao
{
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final CustomerDao single = new CustomerDao();

	/**
	 * 单例模式
	 * @return
	 */
	public static CustomerDao getInstance()
	{
		return single;
	}
	private CustomerDao(){
		
	};
	/**获得所有客户的名称
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
			List<Map<String, Object>> templist = jdbcUtil.findResult("select Name from customer", null);
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
	/**创建一个供应商
	 * @param map
	 * @throws SQLException
	 */
	public void create(Map<String, Object> map) throws SQLException
	{
		DaoUtil.getInstance().createOneTableLine("customer", map);
	}
}
