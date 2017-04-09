package com.gengu.action;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
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
		materialInfoService = MaterialInfoService.getInstance();
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
			return;
		}
		new SwingWorker<String, Void>(){

			@Override
			protected String doInBackground() throws Exception
			{
				String strJudge = materialInfoService.createMaterial(strMaterial);
				return strJudge;
			}
			@Override
			protected void done()
			{
				try
				{
					if (!get().equals("OK"))
					{
						showErrorMessage("创建材料失败");
					}else {
						refreshClass(MaterialInfoPanel.getInstance().getComboBox());
					}
				} catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}
			 
		}.execute();

	}

	public void deleteMaterialAction()
	{
		String strCurrentClass = MaterialInfoPanel.getInstance().getComboBox().getSelectedItem().toString();
		String strMessage="确认要删除材料-'"+strCurrentClass+"'嘛?该材料包含的牌号也会删除!";
		int index = JOptionPane.showConfirmDialog(MaterialInfoPanel.getInstance(), strMessage, "删除材料确认", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (index==0)
		{
			new SwingWorker<String, Void>(){

				@Override
				protected String doInBackground() throws Exception
				{
					String strJudge = materialInfoService.deleteMaterial(strCurrentClass);
					return strJudge;
				}
				@Override
				protected void done()
				{
					try
					{
						if (get()!="OK"){
							showErrorMessage("删除材料失败");
						}
						else {
							refreshClass(MaterialInfoPanel.getInstance().getComboBox());
						}
					} catch (InterruptedException | ExecutionException e)
					{
						e.printStackTrace();
					}
				}
			}.execute();
		}
	}

	/**
	 * 创建牌号
	 * @param strMaterial
	 */
	public void createModelAction(String strMaterial)
	{
		String strModel = JOptionPane.showInputDialog(MaterialInfoPanel.getInstance(), "请在材料-'" + strMaterial + "'中创建新牌号", "牌号输入", JOptionPane.DEFAULT_OPTION);
		if (strModel.trim().equals(""))
		{
			showErrorMessage("输入不为空!");
			return;
		}
		new SwingWorker<String, Void>(){

			@Override
			protected String doInBackground() throws Exception
			{
				String strJudge = materialInfoService.createModel(strModel, strMaterial);
				return strJudge;
			}
			@Override
			protected void done()
			{
				try
				{
					if (!get().equals("OK"))
					{
						showErrorMessage("创建牌号失败");
					}else {
						refreshModel(MaterialInfoPanel.getInstance().getComboBox(),MaterialInfoPanel.getInstance().getJList());
					}
				} catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}
		}.execute();
	}

	public void deleteModelAction()
	{
		String strClass = MaterialInfoPanel.getInstance().getComboBox().getSelectedItem().toString();
		List<String> modelList = MaterialInfoPanel.getInstance().getJList().getSelectedValuesList();
		if (modelList.size()<1)
		{
			return;
		}
		StringBuffer strMessge=new StringBuffer("确定要删除'");
		strMessge.append(strClass);
		strMessge.append("'材料里 : ");
		for (String string : modelList)
		{
			strMessge.append(string);
			strMessge.append("/");
		}
		strMessge.append("这些牌号嘛?");
		
		int index = JOptionPane.showConfirmDialog(MaterialInfoPanel.getInstance(), strMessge.toString(), "删除牌号确认", JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION);
		if (index==0)
		{
			new SwingWorker<String, Void>(){

				@Override
				protected String doInBackground() throws Exception
				{
					String result= materialInfoService.deleteModel(strClass,modelList);
					return result;
				}
				@Override
				protected void done()
				{
					try
					{
						if (get().equals("OK"))
						{
							System.out.println("删除成功");
							refreshModel(MaterialInfoPanel.getInstance().getComboBox(),MaterialInfoPanel.getInstance().getJList());
						}else{
							showErrorMessage("删除失败");
						}
					} catch (InterruptedException | ExecutionException e)
					{
						e.printStackTrace();
					}
				}
			}.execute();
			

		}
	}

	/**
	 * 刷新按钮命令
	 * 更新combobox之后,如果有改变,会自动的更新牌号列表
	 * 因为给列表添加了监听器
	 */
	public void refreshAction(JComboBox comboBox)
	{
		refreshClass(comboBox);
	}

	public void comboxChangeAction(String strClass)
	{
		refreshModel(MaterialInfoPanel.getInstance().getComboBox(),MaterialInfoPanel.getInstance().getJList());
	}

	private void showErrorMessage(String strMes)
	{
		JOptionPane.showMessageDialog(MaterialInfoPanel.getInstance(), strMes, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * 刷新牌号列表
	 */
	private void refreshModel(JComboBox classBox,JList modelList)
	{
		String strClasString = classBox.getSelectedItem().toString();
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
					modelList.setListData(get());
				} catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}

		}.execute();
	}
	/**用来刷新牌号
	 * @param strClass
	 * 	类别
	 * @param modelBox
	 * 	牌号
	 */
	public void refreshModel(String strClass ,JComboBox modelBox)
	{
		new SwingWorker<List<String>, Void>()
		{
			@Override
			protected List<String> doInBackground() throws Exception
			{
				List<String> strlist = materialInfoService.getModelsFromClassification(strClass);
				return strlist;
			}

			@Override
			protected void done()
			{
				DefaultComboBoxModel boxModel = (DefaultComboBoxModel) modelBox.getModel();
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
	/**刷新类别的下拉菜单
	 * @param jComboBox
	 * 	下拉菜单
	 */
	public void refreshClass(JComboBox jComboBox)
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
				DefaultComboBoxModel boxModel = (DefaultComboBoxModel) jComboBox.getModel();
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
