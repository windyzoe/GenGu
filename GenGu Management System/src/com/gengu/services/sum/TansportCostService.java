package com.gengu.services.sum;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gengu.dao.PurchaseDao;
import com.gengu.dao.SaleDao;

public class TansportCostService
{
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final TansportCostService single = new TansportCostService();

	/**
	 * 单例模式
	 * @return
	 */
	public static TansportCostService getInstance()
	{
		return single;
	}
	private TansportCostService(){
		
	}
	public double getTansportCost(String strBeforeDate , String strAfterDate)
	{
		double allCost=0;
		try
		{
			List<Map<String, Object>>  saleTransCost = SaleDao.getInstance().getTransportCost(strBeforeDate, strAfterDate);
			List<Map<String, Object>>  purchaseTransCost = PurchaseDao.getInstance().getTransportCost(strBeforeDate, strAfterDate);
			for (Map<String, Object> map : purchaseTransCost)
			{
				allCost = allCost+Double.valueOf(map.get("COST").toString());
			}
			for (Map<String, Object> map : saleTransCost)
			{
				allCost = allCost+Double.valueOf(map.get("COST").toString());
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return allCost;
	}
}
