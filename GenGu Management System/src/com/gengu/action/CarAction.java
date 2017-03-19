package com.gengu.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.gengu.services.CarService;
import com.gengu.ui.CarInfoDialog;

public class CarAction
{
	public void refreshAction()
	{
		new SwingWorker<List<String>, Void>()
		{
			@Override
			protected List<String> doInBackground() throws Exception
			{
				List<String> strNameList = CarService.getInstance().getNames();
				return strNameList;
			}

			@Override
			protected void done()
			{
				try
				{
					List<String> strNameList = get();
					System.out.println("*******"+strNameList.get(0));
					CarInfoDialog.getInstance().getJlist().setListData(strNameList.toArray());
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
				List<String> strNameList = CarService.getInstance().getNames();
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
		int judge = JOptionPane.showConfirmDialog(CarInfoDialog.getInstance(), "确实要删除吗?", "确认删除",JOptionPane.YES_NO_OPTION);
		if (judge==1)
			return;
		//获得选中的行
		JList jlist=CarInfoDialog.getInstance().getJlist();
		List<Object> selectList = jlist.getSelectedValuesList();

		new SwingWorker<Boolean, Void>()
		{
			@Override
			protected Boolean doInBackground() throws Exception// 查找当前的所有列表
			{
				return CarService.getInstance().deleteRows(selectList);
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
		String strId =JOptionPane.showInputDialog(CarInfoDialog.getInstance(), "请输入创建名称：", "创建物流/车辆名称", JOptionPane.INFORMATION_MESSAGE);
		new SwingWorker<Boolean, Void>()
		{
			@Override
			protected Boolean doInBackground() throws Exception// 查找当前的所有列表
			{
				Map<String, Object> map = new HashMap<>();
				map.put("ID", strId);
				return CarService.getInstance().create(map);
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
