package com.gengu.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import com.gengu.common.ConstantsDB;
import com.gengu.services.PurchaseService;
import com.gengu.ui.MainFrame;
import com.gengu.util.JTableUtil;

public class PurchaseAction
{
	public void refreshPurchaseList()
	{

		new SwingWorker<List<String[]>, Void>()
		{
			@Override
			protected List<String[]> doInBackground() throws Exception//���ҵ�ǰ�������б�
			{
				List<String[]> strRowList = new ArrayList<>();
				List<Map<String, Object>> maplist = PurchaseService.getInstance().getPurchaseList();
				for (Map<String, Object> map : maplist)
				{
					List<String> strTempList = new ArrayList<>();
					for (String string : ConstantsDB.PurchaseHeadDB)
					{
						//System.out.println(string);
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
				tableModel.setRowCount(0);//���
				try
				{
					List<String[]> strList = get();
					for (String[] strings : strList)
					{
						tableModel.addRow(strings);
					}
					JTableUtil.fitTableColumns(MainFrame.getInstance().purchaseTable);//����Ӧ���
				} catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}

		}.execute();
	}
}
