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
		{ "�ɹ����", "¼������", "��������", "��Ӧ��", "���", "�ƺ�", "����", "����", "����", "�ܼ�", "����",
				"�����ַ", "�Ƿ�����", "����/����", "�������" };
	/**
	 * �ɹ���ͷ���ݿ����
	 */
	public final static String[] PurchaseHeadDB =
		{ "PurchaseID", "CreateTime", "OrderTime", "Supplier", "Classification", "Model", "Factory", "UnitPrice", "Quantity", "TotalPrice", "BatchLot",
				"PickingAddress", "Distrabution", "Car", "TansCost" };
	/**
	 * �ƺ����ݿ�
	 */
	public final static String[] ModelDB =
		{"InternalName","Name","Classification"};
}
