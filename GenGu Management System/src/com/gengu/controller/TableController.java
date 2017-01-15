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
 * 各个列表的总命令(修改\添加\删除\)相关的处理
 * 
 * @author XUZH
 *
 */
public class TableController
{
	/**刷新处理
	 * 
	 */
	public void refreshControl()
	{
		int selectTabIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
		String title = MainFrame.getInstance().getTabPane().getTitleAt(selectTabIndex);
		switch (title)
		{ 
		case "采购":
			new PurchaseAction().refreshAction();
			break;
		case "销售":

			break;
		case "出入库":

			break;

		default:
			break;
		}
	}
	/**分页命令的处理
	 * @param currentPage
	 * 当前页
	 */
	public void pagingControl(int currentPage)
	{
		int selectTabIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
		String title = MainFrame.getInstance().getTabPane().getTitleAt(selectTabIndex);
		switch (title)
		{ 
		case "采购":
			new PurchaseAction().pagingAction(currentPage);
			break;
		case "销售":

			break;
		case "出入库":

			break;

		default:
			break;
		}
	}
	/**删除记录的处理
	 * 
	 */
	public void deleteControl()
	{
		int selectTabIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
		String title = MainFrame.getInstance().getTabPane().getTitleAt(selectTabIndex);
		switch (title)
		{ 
		case "采购":
			new PurchaseAction().deleteAction();
			break;
		case "销售":

			break;
		case "出入库":

			break;

		default:
			break;
		}
	}
	/**
	 * 修改记录的处理
	 */
	public void modifyControl()
	{
		int selectTabIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
		String title = MainFrame.getInstance().getTabPane().getTitleAt(selectTabIndex);
		switch (title)
		{ 
		case "采购":
			new ModifyTablePanel(ConstantsDB.PurchaseHead);
			break;
		case "销售":

			break;
		case "出入库":

			break;

		default:
			break;
		}
	}
}
