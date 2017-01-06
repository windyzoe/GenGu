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
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import com.gengu.action.CreateAction;
import com.gengu.action.CreateAction.CreatePurchaseOrderActionListener;
import com.gengu.common.Constants;

import javax.swing.JComboBox;

public class CreatePurchaseOrderPanel extends JDialog
{
	private final JPanel contentPanel = new JPanel();
	private JComboBox Classification;
	private JComboBox Model;
	private JTextField UnitPrice;
	private JTextField Quantity;
	private JTextField Factory;
	private JTextField BatchLot;
	private JTextField OrderTime;
	private JComboBox Company;
	private JTextField PickingAddress;
	private JComboBox Distrabution;
	private JTextField Car;
	private JTextField TansCost;

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
	 * 饿汉单例模式,线程安全
	 */
	private static final CreatePurchaseOrderPanel single = new CreatePurchaseOrderPanel();

	/**
	 * 单例模式
	 * 
	 * @return
	 */
	public static CreatePurchaseOrderPanel getInstance()
	{
		return single;
	}

	/**
	 * Create the dialog.
	 */
	public CreatePurchaseOrderPanel()
	{
		setTitle("创建采购记录");
		setIconImage(new ImageIcon(Constants.PATH_GenGuIcon).getImage());
		setBounds(100, 100, 1000, 314);
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
				JLabel label = new JLabel("\u5355\u4EF7:");
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
				Factory = new JTextField();
				panel.add(Factory);
				Factory.setColumns(10);
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
				Company = new JComboBox();
				panel_1.add(Company);
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
				Car = new JTextField();
				panel.add(Car);
				Car.setColumns(10);
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
				JButton okButton = new JButton("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new OkButtonActionListener());
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
    /**
     * @author XUZH
     *	处理OK按钮事件
     */
    private class OkButtonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        	Map<String, String> map=new HashMap<String, String> ();
        	map.put("Classification", Classification.getSelectedItem().toString());
        	map.put("Model", Model.getSelectedItem().toString());
        	map.put("UnitPrice", UnitPrice.getText());
        	map.put("Quantity", Quantity.getText());
        	map.put("Factory", Factory.getText());
        	map.put("BatchLot", BatchLot.getText());
        	map.put("OrderTime", OrderTime.getText());
        	map.put("Company", Company.getSelectedItem().toString());
        	map.put("PickingAddress", PickingAddress.getText());
        	map.put("Distrabution", Distrabution.getSelectedItem().toString());
        	map.put("Car", Car.getText());
        	map.put("TansCost", TansCost.getText());
        }
    }
}
		