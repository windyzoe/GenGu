package com.gengu.ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.gengu.common.Constants;

public class CarInfoDialog extends JDialog
{

	private JButton jBCreate;
	private JButton jBDelete;
	private JButton jBRefresh;
	private JList list;

	/**
	 * Create the dialog.
	 */
	public CarInfoDialog()
	{
		initPanel();
		initLayout();
		addListeners();
	}
	private void initPanel()
	{
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		// 将界面放在最中央
		this.setLocation((Constants.SCREEN_WIDTH - this.getWidth()) / 2, (Constants.SCREEN_HEIGHT - this.getHeight()) / 2);
		this.setTitle("物流/车辆信息");
	}
	private void initLayout()
	{
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		jBCreate = new JButton("新建");
		panel.add(jBCreate);
		
		jBDelete = new JButton("删除");
		panel.add(jBDelete);
		
		jBRefresh = new JButton("刷新");
		panel.add(jBRefresh);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
	}
	private void addListeners()
	{
		
	}

}
