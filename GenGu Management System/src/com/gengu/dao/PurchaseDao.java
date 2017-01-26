package com.gengu.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gengu.util.DaoUtil;
import com.gengu.util.JdbcUtil;

/**
 * �ɹ��������ݿ����
 * @author XUZH
 *
 */
public class PurchaseDao
{
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final PurchaseDao single = new PurchaseDao();

	/**
	 * ����ģʽ
	 * @return
	 */
	public static PurchaseDao getInstance()
	{
		return single;
	}
	private PurchaseDao(){
		
	}
	public List<Map<String, Object>> getAllList() throws SQLException
	{
		List<Map<String, Object>> maplist = null;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			maplist = jdbcUtil.findResult("select * from purchaselist", null);
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			jdbcUtil.releaseConn();
		}
		return maplist;
	}
	public List<Map<String, Object>> getPaging(int currentPage) throws SQLException
	{
		List<Map<String, Object>> maplist=DaoUtil.getInstance().getPagingRows("purchaselist", currentPage);
		return maplist;
	}
	public void deleteRows(List<Integer> IDs) throws SQLException
	{
		DaoUtil.getInstance().deleteRows("purchaselist", IDs);
	}
	public void updateRows(List<Integer> IDs,Map<String, String> map) throws SQLException
	{
		DaoUtil.getInstance().updateRows("purchaselist", IDs, map);
	}
	/**
	 * ����һ�вɹ���¼
	 * @param map	����+ֵ
	 * @throws SQLException 
	 */
	public void createOneList(Map<String, Object> map) throws SQLException
	{
		DaoUtil.getInstance().createOneTableLine("PURCHASELIST", map);
	}
}
