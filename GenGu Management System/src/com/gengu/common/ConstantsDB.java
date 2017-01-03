package com.gengu.common;

/**
 * @author XUZH
 * 记录DB和中文的对应关系
 */
public class ConstantsDB
{
	/**
	 * 采购表头
	 */
	public final static String[] PurchaseHead =
		{ "采购编号", "录入日期", "订单日期", "供应商", "类别", "牌号", "厂家", "单价", "数量", "总价", "批号",
				"提货地址", "是否配送", "物流/车号", "运输费用" };
	/**
	 * 采购表头数据库对照
	 */
	public final static String[] PurchaseHeadDB =
		{ "PurchaseID", "CreateTime", "OrderTime", "Supplier", "Classification", "Model", "Factory", "UnitPrice", "Quantity", "TotalPrice", "BatchLot",
				"PickingAddress", "Distrabution", "Car", "TansCost" };
	/**
	 * 牌号数据库
	 */
	public final static String[] ModelDB =
		{"InternalName","Name","Classification"};
}
