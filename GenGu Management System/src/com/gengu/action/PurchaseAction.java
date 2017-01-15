package com.gengu.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import com.gengu.common.ConstantsDB;
import com.gengu.component.PagingPanel;
import com.gengu.services.PurchaseService;
import com.gengu.ui.MainFrame;
import com.gengu.util.DaoUtil;
import com.gengu.util.JTableUtil;

public class PurchaseAction
{
	public void pagingAction(int currentPage)
	{

		new SwingWorker<List<String[]>, Void>()
		{
			@Override
			protected List<String[]> doInBackground() throws Exception// 查找当前的所有列表
			{
				List<String[]> strRowList = new ArrayList<>();
				List<Map<String, Object>> maplist = PurchaseService.getInstance().getPaging(currentPage);
				for (Map<String, Object> map : maplist)
				{
					List<String> strTempList = new ArrayList<>();
					for (String string : ConstantsDB.PurchaseHeadDB)
					{
						// System.out.println(string);
						strTempList.add(map.get(string.toUpperCase()).toString());
					}
					String[] arr = (String[]) strTempList.toArray(new String[strTempList.size()]);
					strRowList.add(arr);
				}
				return strRowList;
			}

			@Override
			protected void done()
			{
				DefaultTableModel tableModel = (DefaultTableModel) MainFrame.getInstance().purchaseTable.getModel();
				tableModel.setRowCount(0);// 清空
				try
				{
					List<String[]> strList = get();
					for (String[] strings : strList)
					{
						tableModel.addRow(strings);
					}
					JTableUtil.fitTableColumns(MainFrame.getInstance().purchaseTable);// 自适应宽度
					// 刷新ToolTip
					int tabIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
					String oldTips = MainFrame.getInstance().getTabPane().getToolTipTextAt(tabIndex);
					String newTips = oldTips.substring(oldTips.indexOf(":"), oldTips.length());
					MainFrame.getInstance().getTabPane().setToolTipTextAt(tabIndex, currentPage + newTips);
				} catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}

		}.execute();
	}

	/**
	 * 刷新采购单
	 *  1刷新列表
	 *   2刷新分页组件
	 *    3刷新toolTip
	 */
	public void refreshAction()
	{
		int count = 0;
		try
		{
			count = DaoUtil.getInstance().countTableRows("purchaselist");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		// 刷新toolTip
		int tabIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
		MainFrame.getInstance().getTabPane().setToolTipTextAt(tabIndex, 1 + ":" + count);
		// 刷新组件
		PagingPanel.getInstance().setPanel(count);
		// 刷新列表
		this.pagingAction(1);

	}

	public void deleteAction()
	{
		DefaultTableModel tableModel = (DefaultTableModel) MainFrame.getInstance().purchaseTable.getModel();
		int[] indexs = MainFrame.getInstance().purchaseTable.getSelectedRows();
		List<Integer> IDs = new ArrayList<>();
		for (int index : indexs)
		{
			int ID = Integer.valueOf(tableModel.getValueAt(index, 0).toString());
			IDs.add(ID);
		}
		for (int i = indexs.length - 1; i >= 0; i--)
		{
			tableModel.removeRow(indexs[i]);
		}
		new SwingWorker<Void, Void>()
		{
			@Override
			protected Void doInBackground() throws Exception// 查找当前的所有列表
			{
				PurchaseService.getInstance().deleteRows(IDs);
				return null;
			}

			@Override
			protected void done()
			{
			}

		}.execute();
	}
}
