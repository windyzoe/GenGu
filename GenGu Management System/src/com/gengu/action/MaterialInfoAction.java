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
 * ������Ϣ��action��
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
	 * ��������
	 */
	public void createMaterialAction()
	{
		String strMaterial = JOptionPane.showInputDialog(MaterialInfoPanel.getInstance(), "�������������", "��������", JOptionPane.DEFAULT_OPTION);
		if (strMaterial.trim().equals(""))
		{
			showErrorMessage("��������Ϊ��!");
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
						showErrorMessage("��������ʧ��");
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
		String strMessage="ȷ��Ҫɾ������-'"+strCurrentClass+"'��?�ò��ϰ������ƺ�Ҳ��ɾ��!";
		int index = JOptionPane.showConfirmDialog(MaterialInfoPanel.getInstance(), strMessage, "ɾ������ȷ��", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
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
							showErrorMessage("ɾ������ʧ��");
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
	 * �����ƺ�
	 * @param strMaterial
	 */
	public void createModelAction(String strMaterial)
	{
		String strModel = JOptionPane.showInputDialog(MaterialInfoPanel.getInstance(), "���ڲ���-'" + strMaterial + "'�д������ƺ�", "�ƺ�����", JOptionPane.DEFAULT_OPTION);
		if (strModel.trim().equals(""))
		{
			showErrorMessage("���벻Ϊ��!");
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
						showErrorMessage("�����ƺ�ʧ��");
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
		StringBuffer strMessge=new StringBuffer("ȷ��Ҫɾ��'");
		strMessge.append(strClass);
		strMessge.append("'������ : ");
		for (String string : modelList)
		{
			strMessge.append(string);
			strMessge.append("/");
		}
		strMessge.append("��Щ�ƺ���?");
		
		int index = JOptionPane.showConfirmDialog(MaterialInfoPanel.getInstance(), strMessge.toString(), "ɾ���ƺ�ȷ��", JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION);
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
							System.out.println("ɾ���ɹ�");
							refreshModel(MaterialInfoPanel.getInstance().getComboBox(),MaterialInfoPanel.getInstance().getJList());
						}else{
							showErrorMessage("ɾ��ʧ��");
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
	 * ˢ�°�ť����
	 * ����combobox֮��,����иı�,���Զ��ĸ����ƺ��б�
	 * ��Ϊ���б�����˼�����
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
	 * ˢ���ƺ��б�
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
	/**����ˢ���ƺ�
	 * @param strClass
	 * 	���
	 * @param modelBox
	 * 	�ƺ�
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
	/**ˢ�����������˵�
	 * @param jComboBox
	 * 	�����˵�
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
