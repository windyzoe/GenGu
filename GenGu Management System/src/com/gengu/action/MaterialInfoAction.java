package com.gengu.action;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.gengu.dao.MaterialModelDao;
import com.gengu.services.MaterialInfoService;
import com.gengu.ui.MaterialInfoPanel;

public class MaterialInfoAction
{
	MaterialInfoService materialInfoService;

	public MaterialInfoAction()
	{
		materialInfoService = new MaterialInfoService();
	}

	public void createMaterialAction()
	{
		String strMaterial = JOptionPane.showInputDialog(MaterialInfoPanel.getInstance(), "请输入材料名称", "材料输入", JOptionPane.DEFAULT_OPTION);
		if (strMaterial.trim().equals(""))
		{
			showErrorMessage("材料名不为空!");
		}
		String strJudge = materialInfoService.createMaterial(strMaterial);
		if (!strJudge.equals("OK"))
		{
			showErrorMessage("创建材料失败");
		}
	}

	public void deleteMaterialAction()
	{
		String strMaterial = JOptionPane.showInputDialog(MaterialInfoPanel.getInstance(), "请输入材料名称", "材料输入", JOptionPane.DEFAULT_OPTION);
		if (strMaterial.trim().equals(""))
		{
			showErrorMessage("材料名不为空!");
		}
	}

	public void createModelAction(String strMaterial)
	{
		String strModel = JOptionPane.showInputDialog(MaterialInfoPanel.getInstance(), "请在" + strMaterial + "材料中创建新牌号", "牌号输入", JOptionPane.DEFAULT_OPTION);
		if (strModel.trim().equals(""))
		{
			showErrorMessage("输入不为空!");
		}
		String strJudge = materialInfoService.createModel(strModel, strMaterial);
		if (!strJudge.equals("OK"))
		{
			showErrorMessage("创建牌号失败");
		}
	}

	public void deleteModelAction()
	{
		String strMaterial = JOptionPane.showInputDialog(MaterialInfoPanel.getInstance(), "请输入材料名称", "材料输入", JOptionPane.DEFAULT_OPTION);
		if (strMaterial.trim().equals(""))
		{
			showErrorMessage("材料名不为空!");
		}
	}

	public void refreshAction()
	{
		refreshCombox();
		//refreshJlist();
	}

	public void comboxChangeAction(String strClass)
	{
		refreshJlist();
	}

	private void showErrorMessage(String strMes)
	{
		JOptionPane.showMessageDialog(MaterialInfoPanel.getInstance(), strMes, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	private void refreshJlist()
	{
		String strClasString = MaterialInfoPanel.getInstance().getComboBox().getSelectedItem().toString();
		new SwingWorker<String[], Void>()
		{

			@Override
			protected String[] doInBackground() throws Exception
			{
				List<String> strlist = materialInfoService.getModelsFromClassification(strClasString);
				String[] arr = (String[]) strlist.toArray(new String[strlist.size()]);
				return arr;
			}

			@Override
			protected void done()
			{
				try
				{
					MaterialInfoPanel.getInstance().getJList().setListData(get());
				} catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}

		}.execute();
	}

	/**
	 * 刷新combobox
	 * 
	 * @param strClass
	 */
	private void refreshCombox()
	{
		new SwingWorker<List<String>, Void>()
		{
			@Override
			protected List<String> doInBackground() throws Exception
			{
				List<String> strClassesList = materialInfoService.getAllClassification();
				return strClassesList;
			}

			@Override
			protected void done()
			{
				DefaultComboBoxModel boxModel = (DefaultComboBoxModel) MaterialInfoPanel.getInstance().getComboBox().getModel();
				try
				{
					boxModel.removeAllElements();
					List<String> strClassesList = get();
					for (String string : strClassesList)
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
}
