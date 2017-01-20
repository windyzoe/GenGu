package com.gengu.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.gengu.common.Constants;
import com.gengu.common.ConstantsDB;
import com.gengu.util.JTableUtil;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;

public class ModifyTablePanel extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JLabel listNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			ModifyTablePanel dialog = new ModifyTablePanel(null,0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public ModifyTablePanel(String [] columnNames,int selectSize)
	{
		initTable(columnNames);
		initLayout();
		System.out.println("selectSize :"+selectSize);
		listNumber.setText(selectSize+"");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * 布局
	 */
	private void initLayout()
	{
		//设置标题
		String strTitle = MainFrame.getInstance().getTabName();
		setTitle("修改'"+strTitle+"'页面记录");
		setModal(true);
		setBounds(100, 100, 300, 400);
		setLocation((Constants.SCREEN_WIDTH - getWidth()) / 2, (Constants.SCREEN_HEIGHT - getHeight()) / 2);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\"注意:值为空则该值不会修改.\"", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			scrollPane.setViewportView(table);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel label = new JLabel("已选中");
				buttonPane.add(label);
			}
			{
				listNumber = new JLabel("0");
				buttonPane.add(listNumber);
			}
			{
				JLabel lblNewLabel = new JLabel("\u6761\u8BB0\u5F55");
				buttonPane.add(lblNewLabel);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(actionListener);
			}
		}
	}
	private void initTable(String [] columnNames)
	{
		String []  titles={"属性","值"};
		DefaultTableModel model = new DefaultTableModel(null, titles){
			@Override
			public boolean isCellEditable(int row, int column)
			{
				if (column==0)
				{
					return false;
				}else{
					return true;
				}
			}
		};
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table = new JTable(model);
		table.setDefaultRenderer(Object.class, tcr);
		
		int size = columnNames.length;
		for(int i = 1;i<size;i++)
		{
			String [] temps={columnNames[i],""};
			model.addRow(temps);
		}
		table.getColumnModel().getColumn(1).setPreferredWidth(180);//控制列宽
	}
	/**
	 * OK命令后执行的修改
	 */
	private ActionListener actionListener=new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			System.out.println(model.getValueAt(0, 0));
		}
	};
}
