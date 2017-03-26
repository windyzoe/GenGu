package com.gengu.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gengu.util.DaoUtil;
import com.gengu.util.JdbcUtil;

/**
 * @author XUZH
 * �������ݿ��еĲֿ���Ϣ
 */
public class WareHouseDao
{
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final WareHouseDao single = new WareHouseDao();

	/**
	 * ����ģʽ
	 * @return
	 */
	public static WareHouseDao getInstance()
	{
		return single;
	}
	private WareHouseDao(){
		
	}
	public List<Map<String, Object>> getAllList() throws SQLException
	{
		List<Map<String, Object>> maplist = null;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			maplist = jdbcUtil.findResult("select * from warehouselist", null);
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			jdbcUtil.releaseConn();
		}
		return maplist;
	}
	/**��ȡ��ǰ�ĳ�������
	 * @param strBeforeDate ��ǰʱ��
	 * @param isIn �������⻹�ǳ���
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getStoreAmount(String strBeforeDate,boolean isIn) throws SQLException
	{
		String strStyle="����";
		if (isIn)
			strStyle="���";
		List<Map<String, Object>> maplist = null;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			maplist = jdbcUtil.findResult("SELECT SUM(Quantity) AS Amount FROM warehouselist where OrderTime < Date('"+strBeforeDate+"') AND Style='"+strStyle+"'", null);
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			jdbcUtil.releaseConn();
		}
		return maplist;
	}
	/**��ȡһ��ʱ���ڵĳ������Ϣ
	 * @param strBeforeDate	��ʼʱ��
	 * @param strAfterDate	��ֹʱ��
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getStoreAmount(String strBeforeDate,String strAfterDate) throws SQLException
	{
		List<Map<String, Object>> maplist = null;
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try
		{
			maplist = jdbcUtil.findResult("SELECT ID,STYLE,QUANTITY,OrderTime  FROM warehouselist where OrderTime Between Date('"+strBeforeDate+"') and Date('"+strAfterDate+"')", null);
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
		List<Map<String, Object>> maplist=DaoUtil.getInstance().getPagingRows("warehouselist", currentPage);
		return maplist;
	}
	public void deleteRows(List<Object> IDs) throws SQLException
	{
		DaoUtil.getInstance().deleteRows("warehouselist", IDs);
	}
	public void updateRows(List<Integer> IDs,Map<String, String> map) throws SQLException
	{
		DaoUtil.getInstance().updateRows("warehouselist", IDs, map);
	}
	/**
	 * ����һ�вɹ���¼
	 * @param map	����+ֵ
	 * @throws SQLException 
	 */
	public void createOneList(Map<String, Object> map) throws SQLException
	{
		DaoUtil.getInstance().createOneTableLine("warehouselist", map);
	}
}
