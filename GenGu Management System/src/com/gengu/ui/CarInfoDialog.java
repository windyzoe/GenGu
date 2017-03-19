package com.gengu.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.gengu.action.CarAction;
import com.gengu.common.Constants;
import com.gengu.services.CustomerService;

public class CarInfoDialog extends JDialog
{

	private JButton jBCreate;
	private JButton jBDelete;
	private JButton jBRefresh;
	private JList list;
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final CarInfoDialog single = new CarInfoDialog();

	/**
	 * ����ģʽ
	 * 
	 * @return
	 */
	public static CarInfoDialog getInstance()
	{
		return single;
	}
	/**
	 * Create the dialog.
	 */
	private CarInfoDialog()
	{
		initPanel();
		initLayout();
		addListeners();
		new CarAction().refreshAction();
	}
	private void initPanel()
	{
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		// ���������������
		this.setLocation((Constants.SCREEN_WIDTH - this.getWidth()) / 2, (Constants.SCREEN_HEIGHT - this.getHeight()) / 2);
		this.setTitle("����/������Ϣ");
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
				new CarAction().createAction();
			}
		});
		jBDelete.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new CarAction().deleteAction();
			}
		});
		jBRefresh.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new CarAction().refreshAction();
			}
		});
	}
	public JList getJlist()
	{
		return list;
	}

}
