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
	 * ɾ��,���ݿ�ɾ���ɹ���,�����ɾ��
	 */
	public void deleteAction()
	{
		//ɾ��ȷ��
		int judge = JOptionPane.showConfirmDialog(CarInfoDialog.getInstance(), "ȷʵҪɾ����?", "ȷ��ɾ��",JOptionPane.YES_NO_OPTION);
		if (judge==1)
			return;
		//���ѡ�е���
		JList jlist=CarInfoDialog.getInstance().getJlist();
		List<Object> selectList = jlist.getSelectedValuesList();

		new SwingWorker<Boolean, Void>()
		{
			@Override
			protected Boolean doInBackground() throws Exception// ���ҵ�ǰ�������б�
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
		String strId =JOptionPane.showInputDialog(CarInfoDialog.getInstance(), "�����봴�����ƣ�", "��������/��������", JOptionPane.INFORMATION_MESSAGE);
		new SwingWorker<Boolean, Void>()
		{
			@Override
			protected Boolean doInBackground() throws Exception// ���ҵ�ǰ�������б�
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
