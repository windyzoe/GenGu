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
	 * �ƺ����ݿ�
	 */
	public final static String[] ModelDB =
		{"InternalName","Name","Classification"};
}
