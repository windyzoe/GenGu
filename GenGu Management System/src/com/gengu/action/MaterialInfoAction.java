package com.gengu.action;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.gengu.services.MaterialInfoService;
import com.gengu.ui.MaterialInfoPanel;

/**
 * 材料信息的action类
 * @author XUZH
 *
 */
public class MaterialInfoAction
{
	MaterialInfoService materialInfoService;

	public MaterialInfoAction()
	{
		materialInfoService = new MaterialInfoService();
	}

	/**
	 * 创建材料
	 */
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

	/**
	 * 创建牌号
	 * @param strMaterial
	 */
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
		String strClass = MaterialInfoPanel.getInstance().getComboBox().getSelectedItem().toString();
		List<String> modelList = MaterialInfoPanel.getInstance().getJList().getSelectedValuesList();
		StringBuffer strMessge=new StringBuffer("确定要删除");
		strMessge.append(strClass);
		strMessge.append("材料里 : ");
		for (String string : modelList)
		{
			strMessge.append(string);
			strMessge.append("/");
		}
		strMessge.append("这些牌号嘛?");
		
		int index = JOptionPane.showConfirmDialog(MaterialInfoPanel.getInstance(), strMessge.toString(), "删除牌号确认", JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION);
		if (index==0)
		{
			String result= materialInfoService.deleteModel(strClass,modelList);
			if (result.equals("OK"))
			{
				System.out.println("删除成功");
			}else{
				showErrorMessage("删除失败");
			}
		}
	}

	/**
	 * 刷新按钮命令
	 * 更新combobox之后,如果有改变,会自动的更新牌号列表
	 * 因为给列表添加了监听器
	 */
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

	/**
	 * 刷新牌号列表
	 */
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
