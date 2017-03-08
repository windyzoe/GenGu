package com.gengu.component;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.gengu.common.Constants;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * 过滤面板组件
 * 
 * @author XUZH
 *
 */
public class FilterPanel extends JPanel
{
	private JLabel lblNewLabel;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;
	private boolean visible;
	private JLabel lblNewLabel_2;

	/**
	 * Create the panel.
	 */
	public FilterPanel()
	{
		visible=true;
		init();
		addListeners();
	}

	public void init()
	{
		this.setBorder(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 119, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0, 25, 25, 25, 25, 0, 0, 0, 0, 0, 40, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		
		lblNewLabel_2 = new JLabel("----------\u7B5B\u9009---------");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 3;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		add(lblNewLabel_2, gbc_lblNewLabel_2);

		lblNewLabel_1 = new JLabel("\u65F6\u95F4:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		add(textField, gbc_textField);
		textField.setColumns(10);

		lblNewLabel = new JLabel("\u6750\u6599:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		add(lblNewLabel, gbc_lblNewLabel);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 4;
		add(comboBox, gbc_comboBox);

		comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.gridwidth = 2;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.BOTH;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 5;
		add(comboBox_1, gbc_comboBox_1);

		btnNewButton = new JButton("");
		btnNewButton.setContentAreaFilled(false); //设置按钮透明
		btnNewButton.setBorderPainted(false);
		btnNewButton.setPreferredSize(new Dimension(20, 40));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setIcon(new CustomIcon(Constants.PATH_LeftWindow));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 11;
		add(btnNewButton, gbc_btnNewButton);

	}
	public void addListeners()
	{
		FilterPanel filterPanel=this;
		btnNewButton.addMouseListener(new MouseListener()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				if (visible)
				{
					System.out.println(filterPanel.getSize().toString());
					
					filterPanel.setPreferredSize(new Dimension(20, 500));
					visible=false;
					lblNewLabel.setVisible(false);
					comboBox.setVisible(false);
					comboBox_1.setVisible(false);
					lblNewLabel_1.setVisible(false);
					textField.setVisible(false);
					textField_1.setVisible(false);
				}else{
					filterPanel.setPreferredSize(new Dimension(165, 500));
					visible=true;
					lblNewLabel.setVisible(true);
					comboBox.setVisible(true);
					comboBox_1.setVisible(true);
					lblNewLabel_1.setVisible(true);
					textField.setVisible(true);
					textField_1.setVisible(true);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
			}
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
			}
		});
	}

}
