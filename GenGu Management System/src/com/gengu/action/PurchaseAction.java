package com.gengu.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import com.gengu.common.ConstantsDB;
import com.gengu.component.PagingPanel;
import com.gengu.services.PurchaseService;
import com.gengu.ui.MainFrame;
import com.gengu.util.DaoUtil;

public class PurchaseAction
{
	public void pagingAction(int currentPage)
	{

		new SwingWorker<List<String[]>, Void>()
		{
			@Override
			protected List<String[]> doInBackground() throws Exception// ���ҵ�ǰ�������б�
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
				DefaultTableModel tableModel = (DefaultTableModel) MainFrame.getInstance().getCurrentTable().getModel();
				tableModel.setRowCount(0);// ���
				try
				{
					List<String[]> strList = get();
					for (String[] strings : strList)
					{
						tableModel.addRow(strings);
					}
					MainFrame.getInstance().getCurrentTable().fitTableColumns();// ����Ӧ���
					// ˢ��ToolTip
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
	 * ˢ�²ɹ���
	 *  1ˢ���б�
	 *   2ˢ�·�ҳ���
	 *    3ˢ��toolTip
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
		// ˢ��toolTip
		int tabIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
		MainFrame.getInstance().getTabPane().setToolTipTextAt(tabIndex, 1 + ":" + count);
		// ˢ�����
		PagingPanel.getInstance().setPanel(count);
		// ˢ���б�
		this.pagingAction(1);
	}

	/**
	 * ɾ��,���ݿ�ɾ���ɹ���,�����ɾ��
	 */
	public void deleteAction()
	{
		//ɾ��ȷ��
		int judge = JOptionPane.showConfirmDialog(MainFrame.getInstance().getTabPane(), "ȷʵҪɾ����Щ��¼��?", "ȷ��ɾ��",JOptionPane.YES_NO_OPTION);
		if (judge==1)
			return;
		//���ѡ�е���
		DefaultTableModel tableModel = (DefaultTableModel) MainFrame.getInstance().getCurrentTable().getModel();
		int[] indexs = MainFrame.getInstance().getCurrentTable().getSelectedRows();
		List<Integer> IDs = new ArrayList<>();
		for (int index : indexs)
		{
			int ID = Integer.valueOf(tableModel.getValueAt(index, 0).toString());
			IDs.add(ID);
		}

		new SwingWorker<Boolean, Void>()
		{
			@Override
			protected Boolean doInBackground() throws Exception// ���ҵ�ǰ�������б�
			{
				return PurchaseService.getInstance().deleteRows(IDs);
			}
			@Override
			protected void done()
			{
				try
				{
					if (get())
					{
						for (int i = indexs.length - 1; i >= 0; i--)
						{
							tableModel.removeRow(indexs[i]);
						}
					}
				} catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}
		}.execute();
	}
	/**�޸�,���ݿ��޸ĳɹ���,���޸Ľ���
	 * @param map
	 */
	public void modifyAction(Map<String, String> map)
	{
		//���ѡ�е���
		DefaultTableModel tableModel = (DefaultTableModel) MainFrame.getInstance().getCurrentTable().getModel();
		int[] indexs = MainFrame.getInstance().getCurrentTable().getSelectedRows();
		List<Integer> IDs = new ArrayList<>();
		for (int index : indexs)
		{
			int ID = Integer.valueOf(tableModel.getValueAt(index, 0).toString());
			IDs.add(ID);
		}
		//ת��
		Map<String, String> newMap = new HashMap<>();
		List<String> list = Arrays.asList(ConstantsDB.PurchaseHead);
		for(Map.Entry<String, String> entry : map.entrySet())
		{
			int listIndex = list.indexOf(entry.getKey());
			System.out.println("key :"+ConstantsDB.PurchaseHeadDB[listIndex]+" value : "+entry.getValue());
			newMap.put(ConstantsDB.PurchaseHeadDB[listIndex], entry.getValue());
		}
		new SwingWorker<Boolean, Void>()
		{
			@Override
			protected Boolean doInBackground() throws Exception//���ҵ�ǰ�������б�
			{
				return PurchaseService.getInstance().modifyRows(IDs, newMap);
			}
			@Override
			protected void done()
			{
				try
				{
					if (get())
					{
						MainFrame.getInstance().getCurrentTable().modifyTableValues(map);					}
				} catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}
		}.execute();
	}
}
