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
		{ "编号", "录入日期", "订单日期", "供应商", "类别", "牌号", "厂家", "单价", "数量", "总价", "批号",
				"提货地址", "配送", "物流/车号", "运输费用" };
	/**
	 * 采购表头数据库对照
	 */
	public final static String[] PurchaseHeadDB =
		{ "ID", "CreateTime", "OrderTime", "Supplier", "Classification", "Model", "Factory", "UnitPrice", "Quantity", "TotalPrice", "BatchLot",
				"PickingAddress", "Distrabution", "Car", "TansCost" };
	/**
	 * 销售表头
	 */
	public final static String[] SaleHead =
		{ "编号", "录入日期", "订单日期", "客户", "类别", "牌号", "厂家", "单价", "数量", "总价", "批号",
				"提货地址", "配送", "物流/车号", "运输费用" };
	/**
	 * 销售表头数据库对照
	 */
	public final static String[] SaleHeadDB =
		{ "ID", "CreateTime", "OrderTime", "Customer", "Classification", "Model", "Factory", "UnitPrice", "Quantity", "TotalPrice", "BatchLot",
				"PickingAddress", "Distrabution", "Car", "TansCost" };
	/**
	 * 仓库表头
	 */
	public final static String[] WareHouseHead =
		{ "编号", "录入日期", "出入库日期", "类型", "类别", "牌号", "厂家",  "数量", "备注" };
	/**
	 * 仓库表头数据库对照
	 */
	public final static String[] WareHouseHeadDB =
		{ "ID", "CreateTime", "OrderTime", "Style", "Classification", "Model", "Factory", "Quantity", "Remark"};
	/**
	 * 牌号数据库
	 */
	public final static String[] ModelDB =
		{"InternalName","Name","Classification"};
}
