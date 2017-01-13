package com.gengu.controller;

import com.gengu.action.PurchaseAction;
import com.gengu.ui.MainFrame;

/**
 * 各个列表的总命令(修改\添加\删除\)相关的处理
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
}
