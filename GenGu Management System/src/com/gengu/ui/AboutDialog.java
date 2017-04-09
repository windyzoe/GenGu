package com.gengu.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gengu.common.Constants;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;

public class AboutDialog extends JDialog
{

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
					AboutDialog dialog = new AboutDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public AboutDialog()
	{
		setBounds(100, 100, 450, 300);
		
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		ImageIcon icon = new ImageIcon(Constants.PATH_GenGuIcon);
		icon.setImage(icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
		JLabel jLabel = new JLabel(icon);
		jLabel.setPreferredSize(new Dimension(20, 20));
		panel.add(BorderLayout.CENTER,jLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		String str = new String(
				"     亘古管理系统\n"
				+ "当前版本 : 1.0\n"
				+ "开源产品,请注明出处.");
		JEditorPane editPane = new JEditorPane("text/plain", str);
		editPane.setOpaque(false);

		editPane.setEditable(false);
		panel_1.add(editPane, BorderLayout.CENTER);
	}

}
