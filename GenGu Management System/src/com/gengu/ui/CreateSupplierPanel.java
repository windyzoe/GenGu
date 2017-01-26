package com.gengu.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gengu.common.Constants;
import com.gengu.dao.SupplierDao;
import com.gengu.services.SupplierService;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CreateSupplierPanel extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfContactor;
	private JTextField tfContactorPhone;
	private JTextField tfWorkTime;
	private JTextField tfWeekend;
	private JTextField tfStoreAddress;
	private JTextField tfStoreWorkTime;
	private JTextField tfStorePhone;
	private JTextField tfOverWork;
	private JTextField tfBrand;
	private JTextField tfClassification;
	private JTextField tfModel;
	private JButton		jbOK;
	private JButton 	jbCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			CreateSupplierPanel dialog = new CreateSupplierPanel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Create the dialog.
	 */
	public CreateSupplierPanel(){
		initLayout();
		addListeners();	
	}
	public void initLayout()
	{
		setModal(true);
		setBounds(100, 100, 993, 277);
		setTitle("新建供应商");
		setLocation((Constants.SCREEN_WIDTH - this.getWidth()) / 2, (Constants.SCREEN_HEIGHT - this.getHeight()) / 2);
		//
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("\u540D\u79F0:");
				panel.add(label);
			}
			{
				tfName = new JTextField();
				panel.add(tfName);
				tfName.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u8054\u7CFB\u4EBA:");
				panel.add(label);
			}
			{
				tfContactor = new JTextField();
				panel.add(tfContactor);
				tfContactor.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u8054\u7CFB\u4EBA\u7535\u8BDD:");
				panel.add(label);
			}
			{
				tfContactorPhone = new JTextField();
				panel.add(tfContactorPhone);
				tfContactorPhone.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u5DE5\u4F5C\u65F6\u95F4:");
				panel.add(label);
			}
			{
				tfWorkTime = new JTextField();
				panel.add(tfWorkTime);
				tfWorkTime.setColumns(10);
				
			}
			{
				JLabel label = new JLabel("\u5468\u516D\u65E5\u662F\u5426\u4E0A\u73ED:");
				panel.add(label);
			}
			{
				tfWeekend = new JTextField();
				panel.add(tfWeekend);
				tfWeekend.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("\u4ED3\u5E93\u5730\u5740:");
				panel.add(label);
			}
			{
				tfStoreAddress = new JTextField();
				panel.add(tfStoreAddress);
				tfStoreAddress.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u4ED3\u5E93\u5DE5\u4F5C\u65F6\u95F4:");
				panel.add(label);
			}
			{
				tfStoreWorkTime = new JTextField();
				panel.add(tfStoreWorkTime);
				tfStoreWorkTime.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u4ED3\u5E93\u7535\u8BDD:");
				panel.add(label);
			}
			{
				tfStorePhone = new JTextField();
				panel.add(tfStorePhone);
				tfStorePhone.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u52A0\u73ED\u8D39:");
				panel.add(label);
			}
			{
				tfOverWork = new JTextField();
				panel.add(tfOverWork);
				tfOverWork.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("\u7ECF\u8425\u54C1\u724C:");
				panel.add(label);
			}
			{
				tfBrand = new JTextField();
				panel.add(tfBrand);
				tfBrand.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u7C7B\u522B:");
				panel.add(label);
			}
			{
				tfClassification = new JTextField();
				panel.add(tfClassification);
				tfClassification.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u724C\u53F7:");
				panel.add(label);
			}
			{
				tfModel = new JTextField();
				panel.add(tfModel);
				tfModel.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			jbOK = new JButton("OK");
			jbOK.setActionCommand("OK");
			buttonPane.add(jbOK);
			getRootPane().setDefaultButton(jbOK);
			jbCancel = new JButton("Cancel");
			jbCancel.setActionCommand("Cancel");
			buttonPane.add(jbCancel);
		}
	}
	private void addListeners()
	{
		CreateSupplierPanel dialog=this;
		jbOK.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Boolean bl = SupplierService.getInstance().create(getInformation());
				if (!bl)
				{
					JOptionPane.showMessageDialog(contentPanel, "创建供应商失败！", "WARNING", JOptionPane.WARNING_MESSAGE);
				}else {
					dialog.dispose();
				}
			}
		});
	}
	private Map<String, Object> getInformation()
	{
		Map<String, Object> map=new HashMap<>();
		String strName = tfName.getText();
		map.put("Name", strName);
		return map;
	}
}
