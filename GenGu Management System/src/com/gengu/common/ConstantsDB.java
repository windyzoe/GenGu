package com.gengu.common;

/**
 * @author XUZH
 * ��¼DB�����ĵĶ�Ӧ��ϵ
 */
public class ConstantsDB
{
	/**
	 * �ɹ���ͷ
	 */
	public final static String[] PurchaseHead =
		{ "���", "¼������", "��������", "��Ӧ��", "���", "�ƺ�", "����", "����", "����", "�ܼ�", "����",
				"�����ַ", "����", "����/����", "�������" };
	/**
	 * �ɹ���ͷ���ݿ����
	 */
	public final static String[] PurchaseHeadDB =
		{ "ID", "CreateTime", "OrderTime", "Supplier", "Classification", "Model", "Factory", "UnitPrice", "Quantity", "TotalPrice", "BatchLot",
				"PickingAddress", "Distrabution", "Car", "TansCost" };
	/**
	 * ���۱�ͷ
	 */
	public final static String[] SaleHead =
		{ "���", "¼������", "��������", "�ͻ�", "���", "�ƺ�", "����", "����", "����", "�ܼ�", "����",
				"�����ַ", "����", "����/����", "�������" };
	/**
	 * ���۱�ͷ���ݿ����
	 */
	public final static String[] SaleHeadDB =
		{ "ID", "CreateTime", "OrderTime", "Customer", "Classification", "Model", "Factory", "UnitPrice", "Quantity", "TotalPrice", "BatchLot",
				"PickingAddress", "Distrabution", "Car", "TansCost" };
	/**
	 * �ֿ��ͷ
	 */
	public final static String[] WareHouseHead =
		{ "���", "¼������", "���������", "����", "���", "�ƺ�", "����",  "����", "��ע" };
	/**
	 * �ֿ��ͷ���ݿ����
	 */
	public final static String[] WareHouseHeadDB =
		{ "ID", "CreateTime", "OrderTime", "Style", "Classification", "Model", "Factory", "Quantity", "Remark"};
	/**
	 * �ƺ����ݿ�
	 */
	public final static String[] ModelDB =
		{"InternalName","Name","Classification"};
}
