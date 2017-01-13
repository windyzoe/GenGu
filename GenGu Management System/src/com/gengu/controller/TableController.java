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
	public void refreshControl()
	{
		int selectTabIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
		String title = MainFrame.getInstance().getTabPane().getTitleAt(selectTabIndex);
		switch (title)
		{ 
		case "�ɹ�":
			new PurchaseAction().refreshAction();
			break;
		case "����":

			break;
		case "�����":

			break;

		default:
			break;
		}
	}
	public void pagingControl(int currentPage)
	{
		int selectTabIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
		String title = MainFrame.getInstance().getTabPane().getTitleAt(selectTabIndex);
		switch (title)
		{ 
		case "�ɹ�":
			new PurchaseAction().pagingAction(currentPage);
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
