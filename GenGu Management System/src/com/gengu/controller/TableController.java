package com.gengu.controller;

import com.gengu.action.PurchaseAction;
import com.gengu.ui.MainFrame;

/**
 * �����б��������(�޸�\���\ɾ��\)��صĴ���
 * 
 * @author XUZH
 *
 */
public class TableController
{
	public void refreshAction()
	{
		int selectTabIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
		String title = MainFrame.getInstance().getTabPane().getTitleAt(selectTabIndex);
		switch (title)
		{
		case "�ɹ�":
			new PurchaseAction().refreshPurchaseList();
			break;
		case "����":

			break;
		case "�����":

			break;

		default:
			break;
		}
	}
}
