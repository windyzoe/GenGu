package com.gengu.test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.gengu.services.PurchaseService;
import com.gengu.services.WarehouseService;
import com.gengu.util.DateUtil;

public class testForTable
{
	public static void main(String[] args)
	{

		testWarehouse();
	}
	private static void testPurcharse()
	{
		Map<String, Object> map=new HashMap<>();
		double quantity,unitprice,totalprice;
		quantity=1;
		unitprice=1;
		map.put("Classification", "test");
		map.put("Model", "test");
		map.put("UnitPrice", unitprice);
		map.put("Quantity", quantity);
		map.put("TotalPrice", unitprice*quantity);
		map.put("Factory", "test");
		map.put("BatchLot", "test");
		map.put("OrderTime", DateUtil.getInstance().getNowDate());
		map.put("Supplier", "test");
		map.put("PickingAddress", "test");
		map.put("Distrabution", "test");
		map.put("Car", "test");
		map.put("TansCost", 100);
		map.put("CreateTime", DateUtil.getInstance().getNowTime());
		for (int i = 0; i < 10000; i++)
		{
			try
			{
				PurchaseService.getInstance().createPurchaseList(map);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static void testWarehouse()
	{
		Map<String, Object> map=new HashMap<>();
		double quantity,unitprice,totalprice;
		quantity=1;
		unitprice=1;
		map.put("Style", "1");
		map.put("Classification", "1");
		map.put("Model", "1");
		map.put("Quantity", quantity);
		map.put("Factory", "1");
		map.put("OrderTime", DateUtil.getInstance().getNowDate());
		map.put("CreateTime", DateUtil.getInstance().getNowTime());
		map.put("Remark", "11111111111111111");
		for (int i = 0; i < 10000; i++)
		{
			try
			{
				WarehouseService.getInstance().create(map);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
