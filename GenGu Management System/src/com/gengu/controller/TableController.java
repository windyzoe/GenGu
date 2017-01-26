package com.gengu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import com.gengu.action.PurchaseAction;
import com.gengu.action.SaleAction;
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
		String title = MainFrame.getInstance().getTabName();
		switch (title)
		{ 
		case "采购":
			new PurchaseAction().refreshAction();
			break;
		case "销售":
			new SaleAction().refreshAction();
			break;
		case "仓库":

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
		String title = MainFrame.getInstance().getTabName();
		switch (title)
		{ 
		case "采购":
			new PurchaseAction().pagingAction(currentPage);
			break;
		case "销售":
			new SaleAction().pagingAction(currentPage);
			break;
		case "仓库":

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
		String title = MainFrame.getInstance().getTabName();
		switch (title)
		{ 
		case "采购":
			new PurchaseAction().deleteAction();
			break;
		case "销售":
			new SaleAction().deleteAction();
			break;
		case "仓库":

			break;

		default:
			break;
		}
	}
	/**
	 * 修改记录的处理
	 */
	public void modifyController(Map<String, String> map)
	{
		String title = MainFrame.getInstance().getTabName();
		switch (title)
		{ 
		case "采购":
			new PurchaseAction().modifyAction(map);
			break;
		case "销售":
			new SaleAction().modifyAction(map);
			break;
		case "仓库":

			break;

		default:
			break;
		}
	}
	
}
