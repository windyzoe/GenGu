package com.gengu.ui;

import java.awt.BorderLayout;
import javax.swing.JDialog;

import com.gengu.common.Constants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class FactoryInfoDialog extends JDialog
{
	private JButton jBCreate;
	private JButton jBDelete;
	private JButton jBRefresh;
	private JList list;

	/**
	 * Create the dialog.
	 */
	public FactoryInfoDialog()
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
		// ���������������
		this.setLocation((Constants.SCREEN_WIDTH - this.getWidth()) / 2, (Constants.SCREEN_HEIGHT - this.getHeight()) / 2);
		this.setTitle("������Ϣ");
	}
	private void initLayout()
	{
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		jBCreate = new JButton("�½�");
		panel.add(jBCreate);
		
		jBDelete = new JButton("ɾ��");
		panel.add(jBDelete);
		
		jBRefresh = new JButton("ˢ��");
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
