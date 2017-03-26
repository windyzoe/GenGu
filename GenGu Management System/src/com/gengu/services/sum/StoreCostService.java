package com.gengu.services.sum;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gengu.common.Constants;
import com.gengu.dao.WareHouseDao;
import com.gengu.util.DateUtil;

/**
 * 仓储费
 * 
 * @author Zac
 *
 */
public class StoreCostService
{
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final StoreCostService single = new StoreCostService();

	/**
	 * 单例模式
	 * 
	 * @return
	 */
	public static StoreCostService getInstance()
	{
		return single;
	}

	private StoreCostService()
	{

	}

	/**
	 * 获取当前时间的仓储量
	 * 
	 * @param strBeforeDate
	 * @return
	 */
	public double getStoreAmount(String strBeforeDate)
	{
		double allCost = 0;
		try
		{
			List<Map<String, Object>> inAmount = WareHouseDao.getInstance().getStoreAmount(strBeforeDate, true);
			List<Map<String, Object>> outAmount = WareHouseDao.getInstance().getStoreAmount(strBeforeDate, false);
			for (Map<String, Object> map : inAmount)
			{
				try
				{
					allCost = allCost + Double.valueOf(map.get("AMOUNT").toString());
				} catch (Exception e)
				{
				}
			}
			for (Map<String, Object> map : outAmount)
			{
				try
				{
					allCost = allCost - Double.valueOf(map.get("AMOUNT").toString());
				} catch (Exception e)
				{
				}	
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return allCost;
	}

	/**
	 * 获取时间内的出入库记录
	 * 
	 * @param strBeforeDate
	 * @param strAfterDate
	 * @return key:时间 value:出入库数量
	 */
	public Map<String, Double> getStoreList(String strBeforeDate, String strAfterDate)
	{
		Map<String, Double> resultMap = new HashMap<>();
		try
		{
			List<Map<String, Object>> Amount = WareHouseDao.getInstance().getStoreAmount(strBeforeDate, strAfterDate);
			for (Map<String, Object> map : Amount)
			{
				String strDate = map.get("ORDERTIME").toString();
				double quntity = Double.valueOf(map.get("QUANTITY").toString());
				String strStyle = map.get("STYLE").toString();
				if (strStyle.equals("出库"))
					quntity = 0 - quntity;

				if (resultMap.containsKey(strDate))
				{
					double resultQuntity = resultMap.get(strDate);
					resultQuntity = resultQuntity + quntity;
					resultMap.put(strDate, resultQuntity);
				} else
				{
					resultMap.put(strDate, quntity);
				}
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return resultMap;
	}

	public double getStoreCost(String strBeforeDate, String strAfterDate)
	{
		double cost = 0;
		double Amount = this.getStoreAmount(strBeforeDate);
		Map<String, Double> map = this.getStoreList(strBeforeDate, strAfterDate);
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.getInstance().getDateType());
		try
		{
			Date beforeDate = sdf.parse(strBeforeDate);
			Date afterDate = sdf.parse(strAfterDate);

			while (!beforeDate.after(afterDate))
			{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(beforeDate);
				
				String strDate =sdf.format(beforeDate);
				if (map.containsKey(strDate))
				{
					double dayAmount =map.get(strDate);
					Amount=Amount+dayAmount;
				}
				cost = cost+Amount*Constants.STORECOST;
				System.out.println("cost :"+cost+" day:"+strDate+" amount:"+Amount+" daycost :"+Amount*Constants.STORECOST);
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
				beforeDate = sdf.parse(sdf.format(calendar.getTime()));
			}
		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		return cost;
	}
	public static void main(String[] args)
	{
		double cost=StoreCostService.getInstance().getStoreCost("2017-03-19", "2017-03-26");
		System.out.println(cost);
	}
}
