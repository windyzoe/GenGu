package com.gengu.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.gengu.services.CustomerService;
import com.gengu.services.FactoryService;
import com.gengu.services.PurchaseService;
import com.gengu.ui.FactoryInfoDialog;
import com.gengu.ui.MainFrame;

public class FactoryAction
{
	public void refreshAction()
	{
		new SwingWorker<List<String>, Void>()
		{
			@Override
			protected List<String> doInBackground() throws Exception
			{
				List<String> strNameList = FactoryService.getInstance().getNames();
				return strNameList;
			}

			@Override
			protected void done()
			{
				try
				{
					List<String> strNameList = get();
					FactoryInfoDialog.getInstance().getJlist().setListData(strNameList.toArray());
				} catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}
		}.execute();
	}
	public void refreshAction(JComboBox jComboBox)
	{
		new SwingWorker<List<String>, Void>()
		{
			@Override
			protected List<String> doInBackground() throws Exception
			{
				List<String> strNameList = FactoryService.getInstance().getNames();
				return strNameList;
			}

			@Override
			protected void done()
			{
				DefaultComboBoxModel boxModel = (DefaultComboBoxModel) jComboBox.getModel();
				try
				{
					boxModel.removeAllElements();
					List<String> strNameList = get();
					for (String string : strNameList)
					{
						boxModel.addElement(string);
					}
				} catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}
		}.execute();
	}
	/**
	 * 删除,数据库删除成功后,界面才删除
	 */
	public void deleteAction()
	{
		//删除确认
		int judge = JOptionPane.showConfirmDialog(FactoryInfoDialog.getInstance(), "确实要删除吗?", "确认删除",JOptionPane.YES_NO_OPTION);
		if (judge==1)
			return;
		//获得选中的行
		JList jlist=FactoryInfoDialog.getInstance().getJlist();
		List<Object> selectList = jlist.getSelectedValuesList();

		new SwingWorker<Boolean, Void>()
		{
			@Override
			protected Boolean doInBackground() throws Exception// 查找当前的所有列表
			{
				return FactoryService.getInstance().deleteRows(selectList);
			}
			@Override
			protected void done()
			{
				try
				{
					if (get())
					{
						refreshAction();
					}
				} catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}
		}.execute();
	}
	public void createAction()
	{
		String strId =JOptionPane.showInputDialog(FactoryInfoDialog.getInstance(), "请输入创建名称：", "创建工厂名称", JOptionPane.INFORMATION_MESSAGE);
		new SwingWorker<Boolean, Void>()
		{
			@Override
			protected Boolean doInBackground() throws Exception// 查找当前的所有列表
			{
				Map<String, Object> map = new HashMap<>();
				map.put("ID", strId);
				return FactoryService.getInstance().create(map);
			}
			@Override
			protected void done()
			{
				try
				{
					if (get())
					{
						refreshAction();
					}
				} catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}
		}.execute();
	}
}
