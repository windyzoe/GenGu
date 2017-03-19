package com.gengu.controller;

import java.util.Map;

import javax.swing.JTabbedPane;
import com.gengu.action.PurchaseAction;
import com.gengu.action.SaleAction;
import com.gengu.action.WarehouseAction;
import com.gengu.component.PagingPanel;
import com.gengu.ui.MainFrame;

/**
 * �����б��������(�޸�\���\ɾ��\)��صĴ���
 * 
 * @author XUZH
 *
 */
public class TableController
{
	/**ˢ�´���
	 * 
	 */
	public void refreshControl()
	{
		String title = MainFrame.getInstance().getTabName();
		switch (title)
		{ 
		case "�ɹ�":
			new PurchaseAction().refreshAction();
			break;
		case "����":
			new SaleAction().refreshAction();
			break;
		case "�����":
			new WarehouseAction().refreshAction();
			break;

		default:
			break;
		}
	}
	/**��ҳ����Ĵ���
	 * @param currentPage
	 * ��ǰҳ
	 */
	public void pagingControl(int currentPage)
	{
		String title = MainFrame.getInstance().getTabName();
		switch (title)
		{ 
		case "�ɹ�":
			new PurchaseAction().pagingAction(currentPage);
			break;
		case "����":
			new SaleAction().pagingAction(currentPage);
			break;
		case "�����":
			new WarehouseAction().pagingAction(currentPage);
			break;

		default:
			break;
		}
	}
	/**ɾ����¼�Ĵ���
	 * 
	 */
	public void deleteControl()
	{
		String title = MainFrame.getInstance().getTabName();
		switch (title)
		{ 
		case "�ɹ�":
			new PurchaseAction().deleteAction();
			break;
		case "����":
			new SaleAction().deleteAction();
			break;
		case "�����":
			new WarehouseAction().deleteAction();
			break;

		default:
			break;
		}
	}
	/**
	 * �޸ļ�¼�Ĵ���
	 */
	public void modifyController(Map<String, String> map)
	{
		String title = MainFrame.getInstance().getTabName();
		switch (title)
		{ 
		case "�ɹ�":
			new PurchaseAction().modifyAction(map);
			break;
		case "����":
			new SaleAction().modifyAction(map);
			break;
		case "�����":
			new WarehouseAction().modifyAction(map);
			break;

		default:
			break;
		}
	}
	/**
	 * ��ǩҳ�л��¼�����
	 * ���·�ҳ���
	 */
	public void updatePaging()
	{
		JTabbedPane jTabbedPane=MainFrame.getInstance().getTabPane();
		int index = jTabbedPane.getSelectedIndex();
		String tip = jTabbedPane.getToolTipTextAt(index);
		if (tip==null|| tip.equals(""))
		{
			refreshControl();
			return;
		}
		int currentPage = Integer.parseInt(tip.substring(0, tip.lastIndexOf(":")));
		int total = Integer.parseInt(tip.substring(tip.lastIndexOf(":")+1));
		System.out.println(currentPage +":"+total);
		PagingPanel.getInstance().setPanel(total, currentPage);
	}
	
	
}
