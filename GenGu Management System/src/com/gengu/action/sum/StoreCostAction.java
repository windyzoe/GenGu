package com.gengu.action.sum;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.gengu.component.CustomTable;
import com.gengu.services.sum.StoreCostService;
import com.gengu.services.sum.TansportCostService;

public class StoreCostAction
{
	public void getStoreCost(CustomTable table ,String strBeforeDate , String strAfterDate)
	{
		new SwingWorker<Double, Void>()
		{
			@Override
			protected Double doInBackground() throws Exception
			{
				Double cost = StoreCostService.getInstance().getStoreCost(strBeforeDate, strAfterDate);
				return cost;
			}

			@Override
			protected void done()
			{
				try
				{
					Double cost = get();
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setRowCount(0);// 清空
					String[] infos = {"运输费用",cost+""};
					tableModel.addRow(infos);
				} catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}
		}.execute();
	}
}
