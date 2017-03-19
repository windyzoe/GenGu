package com.gengu.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.gengu.action.CarAction;
import com.gengu.action.FactoryAction;
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
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final FactoryInfoDialog single = new FactoryInfoDialog();

	/**
	 * ����ģʽ
	 * 
	 * @return
	 */
	public static FactoryInfoDialog getInstance()
	{
		return single;
	}
	/**
	 * Create the dialog.
	 */
	private FactoryInfoDialog()
	{
		initPanel();
		initLayout();
		addListeners();
		new FactoryAction().refreshAction();
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
		
		list = new JList();
		scrollPane.setViewportView(list);
	}
	private void addListeners()
	{
		jBCreate.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new FactoryAction().createAction();
			}
		});
		jBDelete.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new FactoryAction().deleteAction();
			}
		});
		jBRefresh.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new FactoryAction().refreshAction();
			}
		});
	}
	public JList getJlist()
	{
		return list;
	}
}
