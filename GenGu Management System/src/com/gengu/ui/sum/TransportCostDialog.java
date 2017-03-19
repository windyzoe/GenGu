package com.gengu.ui.sum;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.gengu.action.FactoryAction;
import com.gengu.action.MaterialInfoAction;
import com.gengu.action.sum.TransportCostAction;
import com.gengu.common.Constants;
import com.gengu.component.CustomTable;
import com.gengu.util.DateUtil;

public class TransportCostDialog extends JDialog
{
	private JTextField beforeDate;
	private JTextField afterDate;
	private JComboBox materialBox;
	private JComboBox modelBox;
	private CustomTable table;
	private JButton okButton;

	public TransportCostDialog()
	{
		init();
		initTable();
		initLayout();
		addListeners();
		initInfos();
	}
	private void init()
	{
		setModal(true);
		setBounds(100, 100, 768, 460);
		getContentPane().setLayout(new BorderLayout());
		// 将界面放在最中央
		this.setLocation((Constants.SCREEN_WIDTH - this.getWidth()) / 2, (Constants.SCREEN_HEIGHT - this.getHeight()) / 2);
		this.setTitle("运费统计");
	}
	private void initLayout()
	{
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7EDF\u8BA1\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		panel_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JLabel chckbxNewCheckBox = new JLabel("    \u65F6\u95F4\uFF1A");
		panel_1.add(chckbxNewCheckBox);

		beforeDate = new JTextField();
		panel_1.add(beforeDate);
		beforeDate.setColumns(10);

		JLabel label_1 = new JLabel("\u2014");
		panel_1.add(label_1);

		afterDate = new JTextField();
		panel_1.add(afterDate);
		afterDate.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		JCheckBox checkBox = new JCheckBox("\u6750\u6599\uFF1A");
		panel_2.add(checkBox);

		materialBox = new JComboBox();
		panel_2.add(materialBox);

		JCheckBox lblNewLabel = new JCheckBox("\u7C7B\u522B\uFF1A");
		panel_2.add(lblNewLabel);

		modelBox = new JComboBox();
		panel_2.add(modelBox);

		Component horizontalGlue = Box.createHorizontalGlue();
		panel_2.add(horizontalGlue);

		okButton = new JButton("OK");
		okButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_2.add(okButton);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);
	}
	private void addListeners()
	{
		TransportCostDialog dialog=this;
		materialBox.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					System.out.println("now combo Changed");
					new MaterialInfoAction().refreshModel(materialBox.getSelectedItem().toString(), modelBox);
				}
			}
		});
		okButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String strBefore = dialog.beforeDate.getText();
				String strafter = dialog.afterDate.getText();
				new TransportCostAction().getTansportCost(table, strBefore, strafter);
			}
		});
		
	}
	private void initInfos()
	{
		new MaterialInfoAction().refreshClass(materialBox); 
		afterDate.setText(DateUtil.getInstance().getNowDate());
		beforeDate.setText(DateUtil.getInstance().getDateBefore(new Date(), 30));
	}
	private void initTable()
	{
		String[] titles={"名称","花费"};
		table = new CustomTable(titles);
	}
}
