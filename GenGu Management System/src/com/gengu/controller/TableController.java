package com.gengu.controller;

import java.util.Map;

import javax.swing.JTabbedPane;
import com.gengu.action.PurchaseAction;
import com.gengu.action.SaleAction;
import com.gengu.action.WarehouseAction;
import com.gengu.component.PagingPanel;
import com.gengu.ui.MainFrame;

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
		case "出入库":
			new WarehouseAction().refreshAction();
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
		case "出入库":
			new WarehouseAction().pagingAction(currentPage);
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
		case "出入库":
			new WarehouseAction().deleteAction();
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
		case "出入库":
			new WarehouseAction().modifyAction(map);
			break;

		default:
			break;
		}
	}
	/**
	 * 标签页切换事件处理
	 * 更新分页组件
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
