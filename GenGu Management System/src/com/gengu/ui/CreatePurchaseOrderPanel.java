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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import com.gengu.action.CarAction;
import com.gengu.action.MaterialInfoAction;
import com.gengu.action.SupplierAction;
import com.gengu.action.FactoryAction;
import com.gengu.common.Constants;
import com.gengu.services.PurchaseService;
import com.gengu.util.DateUtil;

import javax.swing.JComboBox;

public class CreatePurchaseOrderPanel extends JDialog
{
	private final JPanel contentPanel = new JPanel();
	private JComboBox Classification;
	private JComboBox Model;
	private JTextField UnitPrice;
	private JTextField Quantity;
	private JComboBox Factory;
	private JTextField BatchLot;
	private JTextField OrderTime;
	private JComboBox Supplier;
	private JTextField PickingAddress;
	private JComboBox Distrabution;
	private JComboBox Car;
	private JTextField TansCost;
	private	JButton okButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			CreatePurchaseOrderPanel dialog = new CreatePurchaseOrderPanel();
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
	public CreatePurchaseOrderPanel()
	{
		initLayout();
		addListeners();
		initInfos();
	}

	private void initLayout()
	{
		setModal(true);
		setTitle("创建采购记录");
		setIconImage(new ImageIcon(Constants.PATH_GenGuIcon).getImage());
		setBounds(100, 100, 1000, 314);
		setLocationRelativeTo(MaterialInfoPanel.getInstance());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel.setBorder(new TitledBorder(null, "\u6750\u6599:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("\u7C7B\u522B:");
				panel.add(label);
			}
			{
				Classification = new JComboBox();
				panel.add(Classification);
			}
			{
				JLabel label = new JLabel("\u724C\u53F7:");
				panel.add(label);
			}
			{
				Model = new JComboBox();
				panel.add(Model);
			}
			{
				JLabel label = new JLabel("\u5355\u4EF7(\u5143):");
				panel.add(label);
			}
			{
				UnitPrice = new JTextField();
				panel.add(UnitPrice);
				UnitPrice.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u6570\u91CF(\u5428):");
				panel.add(label);
			}
			{
				Quantity = new JTextField();
				panel.add(Quantity);
				Quantity.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u5382\u5BB6:");
				panel.add(label);
			}
			{
				Factory = new JComboBox();
				panel.add(Factory);
			}
			{
				JLabel label = new JLabel("\u6279\u53F7:");
				panel.add(label);
			}
			{
				BatchLot = new JTextField();
				panel.add(BatchLot);
				BatchLot.setColumns(10);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "\u8BA2\u5355:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel_1);
			{
				JLabel label = new JLabel("\u8BA2\u5355\u65E5\u671F:");
				panel_1.add(label);
			}
			{
				OrderTime = new JTextField();
				panel_1.add(OrderTime);
				OrderTime.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u4F9B\u5E94\u5546:");
				panel_1.add(label);
			}
			{
				Supplier = new JComboBox();
				panel_1.add(Supplier);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "\u7269\u6D41\u76F8\u5173:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("\u63D0\u8D27\u5730\u5740:");
				panel.add(label);
			}
			{
				PickingAddress = new JTextField();
				panel.add(PickingAddress);
				PickingAddress.setColumns(10);
			}
			{
				JLabel lblyn = new JLabel("\u662F\u5426\u914D\u9001(Y/N):");
				panel.add(lblyn);
			}
			{
				Distrabution = new JComboBox();
				panel.add(Distrabution);
			}
			{
				JLabel label = new JLabel("\u7269\u6D41/\u8F66\u53F7:");
				panel.add(label);
			}
			{
				Car = new JComboBox();
				panel.add(Car);
			}
			{
				JLabel label = new JLabel("\u8FD0\u8F93\u8D39\u7528:");
				panel.add(label);
			}
			{
				TansCost = new JTextField();
				panel.add(TansCost);
				TansCost.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	private Map<String, Object> checkAndMap()throws Exception
	{
		Map<String, Object> map=new HashMap<>();
		double quantity,unitprice,totalprice;
		try
		{
			quantity = Double.valueOf(Quantity.getText());
			unitprice = Double.valueOf(UnitPrice.getText());
		} catch (NumberFormatException e)
		{
			throw e;
		}
		map.put("Classification", Classification.getSelectedItem().toString());
		map.put("Model", Model.getSelectedItem().toString());
		map.put("UnitPrice", unitprice);
		map.put("Quantity", quantity);
		map.put("TotalPrice", unitprice*quantity);
		map.put("Factory", Factory.getSelectedItem().toString());
		map.put("BatchLot", BatchLot.getText());
		map.put("OrderTime", OrderTime.getText());
		map.put("Supplier", Supplier.getSelectedItem().toString());
		map.put("PickingAddress", PickingAddress.getText());
		map.put("Distrabution", Distrabution.getSelectedItem().toString());
		map.put("Car", Car.getSelectedItem().toString());
		map.put("TansCost", TansCost.getText());
		map.put("CreateTime", DateUtil.getInstance().getNowTime());
		return map;
	}
	private void addListeners()
	{
		CreatePurchaseOrderPanel createPurchaseOrderPanel=this;
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
					JOptionPane.showMessageDialog(createPurchaseOrderPanel, "输入错误,请检查输入!", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try
				{
					PurchaseService.getInstance().createPurchaseList(map);
				} catch (SQLException e1)
				{
					JOptionPane.showMessageDialog(createPurchaseOrderPanel, "创建失败,请检查输入!", "ERROR", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				createPurchaseOrderPanel.dispose();
			}
		});
	}

	/**
	 * init data
	 */
	private void initInfos()
	{
		new MaterialInfoAction().refreshClass(Classification);//初始化分类
		new SupplierAction().refreshSupplierNames(Supplier);//初始化供应商
		new CarAction().refreshAction(Car);
		new FactoryAction().refreshAction(Factory);
		OrderTime.setText(DateUtil.getInstance().getNowDate());//初始化订单日期(可修改)
		Distrabution.addItem("是");//是否配送
		Distrabution.addItem("否");//是否配送
	}
}
