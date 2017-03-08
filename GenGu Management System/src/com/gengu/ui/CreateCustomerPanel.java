package com.gengu.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gengu.common.Constants;
import com.gengu.services.CustomerService;
import com.gengu.services.SupplierService;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class CreateCustomerPanel extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JTextField jtfName;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JButton		jbOK;
	private JButton 	jbCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			CreateCustomerPanel dialog = new CreateCustomerPanel();
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
	public CreateCustomerPanel()
	{
		initLayout();
		addListeners();	
	}
	private void initLayout()
	{
		setModal(true);
		setBounds(100, 100, 768, 300);
		setTitle("新建客户");
		setLocation((Constants.SCREEN_WIDTH - this.getWidth()) / 2, (Constants.SCREEN_HEIGHT - this.getHeight()) / 2);
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
				jtfName = new JTextField();
				panel.add(jtfName);
				jtfName.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u8054\u7CFB\u4EBA:");
				panel.add(label);
			}
			{
				textField_1 = new JTextField();
				panel.add(textField_1);
				textField_1.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u8054\u7CFB\u7535\u8BDD:");
				panel.add(label);
			}
			{
				textField_2 = new JTextField();
				panel.add(textField_2);
				textField_2.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u5DE5\u4F5C\u65F6\u95F4:");
				panel.add(label);
			}
			{
				textField_3 = new JTextField();
				panel.add(textField_3);
				textField_3.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u5468\u516D\u65E5\u662F\u5426\u4E0A\u73ED:");
				panel.add(label);
			}
			{
				JComboBox comboBox = new JComboBox();
				panel.add(comboBox);
			}
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("\u6536\u8D27\u4EBA:");
				panel.add(label);
			}
			{
				textField_4 = new JTextField();
				panel.add(textField_4);
				textField_4.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u6536\u8D27\u4EBA\u7535\u8BDD:");
				panel.add(label);
			}
			{
				textField_5 = new JTextField();
				panel.add(textField_5);
				textField_5.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("\u6536\u8D27\u5730\u5740:");
				panel.add(lblNewLabel);
			}
			{
				textField_6 = new JTextField();
				panel.add(textField_6);
				textField_6.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("\u6536\u53D1\u7968\u5730\u5740:");
				panel.add(lblNewLabel_1);
			}
			{
				textField_7 = new JTextField();
				panel.add(textField_7);
				textField_7.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("\u66FE\u91C7\u8D2D\u54C1\u724C:");
				panel.add(label);
			}
			{
				textField_8 = new JTextField();
				panel.add(textField_8);
				textField_8.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u7C7B\u522B:");
				panel.add(label);
			}
			{
				textField_9 = new JTextField();
				panel.add(textField_9);
				textField_9.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u724C\u53F7:");
				panel.add(label);
			}
			{
				textField_10 = new JTextField();
				panel.add(textField_10);
				textField_10.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				jbOK = new JButton("OK");
				jbOK.setActionCommand("OK");
				buttonPane.add(jbOK);
				getRootPane().setDefaultButton(jbOK);
			}
			{
				jbCancel = new JButton("Cancel");
				jbCancel.setActionCommand("Cancel");
				buttonPane.add(jbCancel);
			}
		}
	}
	private void addListeners()
	{
		CreateCustomerPanel dialog=this;
		jbOK.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Boolean bl = CustomerService.getInstance().create(getInformation());
				if (!bl)
				{
					JOptionPane.showMessageDialog(contentPanel, "创建客户失败！", "WARNING", JOptionPane.WARNING_MESSAGE);
				}else {
					dialog.dispose();
				}
			}
		});
	}
	private Map<String, Object> getInformation()
	{
		Map<String, Object> map=new HashMap<>();
		String strName = jtfName.getText();
		map.put("Name", strName);
		return map;
	}

}
