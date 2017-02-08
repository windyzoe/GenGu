package com.gengu.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gengu.action.MaterialInfoAction;
import com.gengu.common.Constants;
import com.gengu.services.WarehouseService;
import com.gengu.util.DateUtil;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class CreateInOutWareHousePanel extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JTextField OrderTime;
	private JTextField Quantity;
	private JTextField Factory;
	private JTextField Remark;
	private JComboBox Style;
	private JComboBox Classification;
	private JComboBox Model;
	private JButton okButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			CreateInOutWareHousePanel dialog = new CreateInOutWareHousePanel();
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
	public CreateInOutWareHousePanel()
	{
		initLayout();
		addListeners();
		initInfos();
	}
	private void initLayout()
	{
		setTitle("创建出入库记录");
		setModal(true);
		setBounds(100, 100, 1000, 154);
		setLocation((Constants.SCREEN_WIDTH - this.getWidth()) / 2, (Constants.SCREEN_HEIGHT - this.getHeight()) / 2);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEADING);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("\u7C7B\u578B\uFF1A");
				panel.add(label);
			}
			{
				Style = new JComboBox();
				panel.add(Style);
			}
			{
				JLabel lblNewLabel = new JLabel("\u51FA\u5165\u5E93\u65E5\u671F\uFF1A");
				panel.add(lblNewLabel);
			}
			{
				OrderTime = new JTextField();
				panel.add(OrderTime);
				OrderTime.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u6750\u6599\u7C7B\u522B\uFF1A");
				panel.add(label);
			}
			{
				Classification = new JComboBox();
				panel.add(Classification);
			}
			{
				JLabel label = new JLabel("\u724C\u53F7\uFF1A");
				panel.add(label);
			}
			{
				Model = new JComboBox();
				panel.add(Model);
			}
			{
				JLabel label = new JLabel("\u6570\u91CF\uFF1A");
				panel.add(label);
			}
			{
				Quantity = new JTextField();
				panel.add(Quantity);
				Quantity.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u5382\u5BB6\uFF1A");
				panel.add(label);
			}
			{
				Factory = new JTextField();
				panel.add(Factory);
				Factory.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("\u5907\u6CE8\uFF1A");
				panel.add(label);
			}
			{
				Remark = new JTextField();
				panel.add(Remark);
				Remark.setColumns(30);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	private void addListeners()
	{
		CreateInOutWareHousePanel dialog=this;
		Classification.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					System.out.println("now combo Changed");
					new MaterialInfoAction().refreshModel(Classification.getSelectedItem().toString(), Model);
				}
			}
		});
		okButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Map<String, Object> map=new HashMap<>();
				try
				{
					map =checkAndMap();
				} catch (Exception e1)
				{
					JOptionPane.showMessageDialog(dialog, "输入错误,请检查输入!", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try
				{
					WarehouseService.getInstance().create(map);
				} catch (SQLException e1)
				{
					JOptionPane.showMessageDialog(dialog, "创建失败,请检查输入!", "ERROR", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				dialog.dispose();
			}
		});
	}
	/**
	 * init data
	 */
	private void initInfos()
	{
		new MaterialInfoAction().refreshClass(Classification);//初始化分类
		OrderTime.setText(DateUtil.getInstance().getNowDate());//初始化订单日期(可修改)
		Style.addItem("出库");//是否配送
		Style.addItem("入库");//是否配送
	}
	private Map<String, Object> checkAndMap()throws Exception
	{
		Map<String, Object> map=new HashMap<>();
		double quantity;
		try
		{
			quantity = Double.valueOf(Quantity.getText());
		} catch (NumberFormatException e)
		{
			throw e;
		}
		map.put("Style", Style.getSelectedItem().toString());
		map.put("Classification", Classification.getSelectedItem().toString());
		map.put("Model", Model.getSelectedItem().toString());
		map.put("Quantity", quantity);
		map.put("Factory", Factory.getText());
		map.put("OrderTime", OrderTime.getText());
		map.put("CreateTime", DateUtil.getInstance().getNowTime());
		map.put("Remark", Remark.getText());
		return map;
	}

}
