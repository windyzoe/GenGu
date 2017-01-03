package com.gengu.action;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
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
		materialInfoService = new MaterialInfoService();
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
		}
		String strJudge = materialInfoService.createMaterial(strMaterial);
		if (!strJudge.equals("OK"))
		{
			showErrorMessage("��������ʧ��");
		}
	}

	public void deleteMaterialAction()
	{

		String strMaterial = JOptionPane.showInputDialog(MaterialInfoPanel.getInstance(), "�������������", "��������", JOptionPane.DEFAULT_OPTION);
		if (strMaterial.trim().equals(""))
		{
			showErrorMessage("��������Ϊ��!");
		}
	}

	/**
	 * �����ƺ�
	 * @param strMaterial
	 */
	public void createModelAction(String strMaterial)
	{
		String strModel = JOptionPane.showInputDialog(MaterialInfoPanel.getInstance(), "����" + strMaterial + "�����д������ƺ�", "�ƺ�����", JOptionPane.DEFAULT_OPTION);
		if (strModel.trim().equals(""))
		{
			showErrorMessage("���벻Ϊ��!");
		}
		String strJudge = materialInfoService.createModel(strModel, strMaterial);
		if (!strJudge.equals("OK"))
		{
			showErrorMessage("�����ƺ�ʧ��");
		}
	}

	public void deleteModelAction()
	{
		String strClass = MaterialInfoPanel.getInstance().getComboBox().getSelectedItem().toString();
		List<String> modelList = MaterialInfoPanel.getInstance().getJList().getSelectedValuesList();
		StringBuffer strMessge=new StringBuffer("ȷ��Ҫɾ��");
		strMessge.append(strClass);
		strMessge.append("������ : ");
		for (String string : modelList)
		{
			strMessge.append(string);
			strMessge.append("/");
		}
		strMessge.append("��Щ�ƺ���?");
		
		int index = JOptionPane.showConfirmDialog(MaterialInfoPanel.getInstance(), strMessge.toString(), "ɾ���ƺ�ȷ��", JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION);
		if (index==0)
		{
			String result= materialInfoService.deleteModel(strClass,modelList);
			if (result.equals("OK"))
			{
				System.out.println("ɾ���ɹ�");
			}else{
				showErrorMessage("ɾ��ʧ��");
			}
		}
	}

	/**
	 * ˢ�°�ť����
	 * ����combobox֮��,����иı�,���Զ��ĸ����ƺ��б�
	 * ��Ϊ���б�����˼�����
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
	 * ˢ���ƺ��б�
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
	 * ˢ��combobox
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
