package com.gengu.ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.gengu.action.MaterialInfoAction;
import com.gengu.common.Constants;
import com.gengu.common.ConstantsDB;
import com.gengu.services.MaterialInfoService;

import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import java.awt.Color;

/**
 * 材料和牌号管理界面
 * @author XUZH
 *
 */
public class MaterialInfoPanel extends JDialog
{
	private JComboBox comboBox;
	private JButton jbCreateMaterial;
	private JButton jbCreateModel;
	private JButton jbDeleteMaterial;
	private JButton jbDeleteModel;
	private JList jlist;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			MaterialInfoPanel dialog = new MaterialInfoPanel();
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
	private static final MaterialInfoPanel single = new MaterialInfoPanel();
	private JPanel panel_3;

	/**
	 * 单例模式
	 * 
	 * @return
	 */
	public static MaterialInfoPanel getInstance()
	{
		return single;
	}

	/**
	 * Create the dialog.
	 */
	private MaterialInfoPanel()
	{
		initPanel();
		initLayout();
		initInformation();
		addListeners();
	}

	private void initPanel()
	{
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		// 将界面放在最中央
		this.setLocation((Constants.SCREEN_WIDTH - this.getWidth()) / 2, (Constants.SCREEN_HEIGHT - this.getHeight()) / 2);
		this.setTitle("材料信息");
	}

	private void initLayout()
	{
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7F16\u8F91", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]
		{ 54, 32, 0 };
		gbl_panel.rowHeights = new int[]
		{ 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[]
		{ 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		jbCreateMaterial = new JButton("新建材料");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridwidth = 2;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 0;
		panel.add(jbCreateMaterial, gbc_btnNewButton_2);

		jbDeleteMaterial = new JButton("删除材料");
		GridBagConstraints gbc_jbDeleteMaterial = new GridBagConstraints();
		gbc_jbDeleteMaterial.insets = new Insets(0, 0, 5, 0);
		gbc_jbDeleteMaterial.gridwidth = 2;
		gbc_jbDeleteMaterial.gridx = 0;
		gbc_jbDeleteMaterial.gridy = 1;
		panel.add(jbDeleteMaterial, gbc_jbDeleteMaterial);

		jbCreateModel = new JButton("新建牌号");
		jbCreateModel.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_btnNewButton_1_1 = new GridBagConstraints();
		gbc_btnNewButton_1_1.gridwidth = 2;
		gbc_btnNewButton_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1_1.gridx = 0;
		gbc_btnNewButton_1_1.gridy = 2;
		panel.add(jbCreateModel, gbc_btnNewButton_1_1);

		jbDeleteModel = new JButton("删除牌号");
		GridBagConstraints gbc_jbDeleteModel = new GridBagConstraints();
		gbc_jbDeleteModel.insets = new Insets(0, 0, 5, 0);
		gbc_jbDeleteModel.gridwidth = 2;
		gbc_jbDeleteModel.gridx = 0;
		gbc_jbDeleteModel.gridy = 3;
		panel.add(jbDeleteModel, gbc_jbDeleteModel);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u6750\u6599:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_1, BorderLayout.NORTH);
		
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		comboBox = new JComboBox(model);
		panel_1.add(comboBox);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "\u724C\u53F7:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_3, BorderLayout.CENTER);
				panel_3.setLayout(new BorderLayout(0, 0));
		
				JScrollPane scrollPane = new JScrollPane();
				panel_3.add(scrollPane);
				
						jlist = new JList<String>();
						jlist.setLayoutOrientation(JList.VERTICAL_WRAP);
						scrollPane.setViewportView(jlist);
	}

	private void addListeners()
	{
		jbCreateMaterial.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new MaterialInfoAction().createMaterialAction();
			}
		});
		jbDeleteMaterial.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new MaterialInfoAction().deleteMaterialAction();
			}
		});
		jbCreateModel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new MaterialInfoAction().createModelAction(comboBox.getSelectedItem().toString());
			}
		});
		jbDeleteModel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new MaterialInfoAction().deleteModelAction();
			}
		});
		comboBox.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					System.out.println("now combo Changed");
					new MaterialInfoAction().comboxChangeAction(comboBox.getSelectedItem().toString());
				}
			}
		});
	}
	private void initInformation()
	{
		new MaterialInfoAction().refreshAction(this.comboBox);
	}
	public JComboBox getComboBox()
	{
		return this.comboBox;
	}
	public JList<String> getJList()
	{
		return this.jlist;
	}
}
