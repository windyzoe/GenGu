package com.gengu.controller;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.gengu.action.PurchaseAction;
import com.gengu.common.Constants;
import com.gengu.common.ConstantsDB;
import com.gengu.ui.MainFrame;
import com.gengu.ui.ModifyTablePanel;

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
	/**��ҳ����Ĵ���
	 * @param currentPage
	 * ��ǰҳ
	 */
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
	/**ɾ����¼�Ĵ���
	 * 
	 */
	public void deleteControl()
	{
		int selectTabIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
		String title = MainFrame.getInstance().getTabPane().getTitleAt(selectTabIndex);
		switch (title)
		{ 
		case "�ɹ�":
			new PurchaseAction().deleteAction();
			break;
		case "����":

			break;
		case "�����":

			break;

		default:
			break;
		}
	}
	/**
	 * �޸ļ�¼�Ĵ���
	 */
	public void modifyControl()
	{
		int selectTabIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
		String title = MainFrame.getInstance().getTabPane().getTitleAt(selectTabIndex);
		switch (title)
		{ 
		case "�ɹ�":
			new ModifyTablePanel(ConstantsDB.PurchaseHead);
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
