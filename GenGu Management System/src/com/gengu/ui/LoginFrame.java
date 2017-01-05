package com.gengu.ui;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gengu.common.Constants;
import com.sun.awt.AWTUtilities;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class LoginFrame extends JFrame
{

	private JPanel contentPane;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JButton jbLogin;
	private JButton jbCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame()
	{
		initFrame();
		initLayout();
		addListeners();
		AWTUtilities.setWindowOpaque(this, false);//关键,使得透明
	}

	/**
	 * 初始化窗口,添加背景图片
	 */
	private void initFrame()
	{
		setUndecorated(true);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel()
		{
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(new ImageIcon(Constants.PATH_Login).getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	}

	/**
	 * 初始布局
	 */
	private void initLayout()
	{
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]
		{ 141, 330, 10, 0 };
		gbl_contentPane.rowHeights = new int[]
		{ 190, 50, 50, 16, 32, 0 };
		gbl_contentPane.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.EAST;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setOpaque(false);

		JLabel lblNewLabel_1 = new JLabel("Name:");
		panel_1.add(lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		contentPane.add(panel_2, gbc_panel_2);

		textField_1 = new JTextField();
		panel_2.add(textField_1);
		textField_1.setPreferredSize(new Dimension(320, 35));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setOpaque(false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		contentPane.add(panel, gbc_panel);

		JLabel lblNewLabel = new JLabel("Password:");
		panel.add(lblNewLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 2;
		contentPane.add(panel_3, gbc_panel_3);

		passwordField = new JPasswordField();
		panel_3.add(passwordField);
		passwordField.setPreferredSize(new Dimension(320, 35));

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 1;
		gbc_panel_4.gridy = 4;
		contentPane.add(panel_4, gbc_panel_4);
		panel_4.setLayout(null);

		jbLogin = new JButton("");
		jbLogin.setForeground(Color.DARK_GRAY);
		jbLogin.setBounds(5, 0, 208, 37);
		jbLogin.setContentAreaFilled(false);
		panel_4.add(jbLogin);

		jbCancel = new JButton("");
		jbCancel.setBounds(245, 0, 80, 37);
		jbCancel.setContentAreaFilled(false);
		panel_4.add(jbCancel);
	}

	private void addListeners()
	{
		this.addMouseListener(moveWindowListener);
		this.addMouseMotionListener(moveWindowListener);
		jbLogin.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
			}
		});

		jbCancel.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
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
				dispose();
			}
		});
	}

	private MouseAdapter moveWindowListener = new MouseAdapter()
	{

		private Point lastPoint = null;

		@Override
		public void mousePressed(MouseEvent e)
		{
			lastPoint = e.getLocationOnScreen();
		}

		@Override
		public void mouseDragged(MouseEvent e)
		{
			Point point = e.getLocationOnScreen();
			int offsetX = point.x - lastPoint.x;
			int offsetY = point.y - lastPoint.y;
			Rectangle bounds = getBounds();
			bounds.x += offsetX;
			bounds.y += offsetY;
			System.out.println(offsetX);
			setBounds(bounds);
			lastPoint = point;
		}
	};
}
