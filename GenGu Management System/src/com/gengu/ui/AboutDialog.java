package com.gengu.ui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gengu.common.Constants;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JEditorPane;

/**���� ҳ��
 * @author XUZH
 *
 */
public class AboutDialog extends JDialog
{
	/**
	 * Create the dialog.
	 */
	public AboutDialog()
	{
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		// ���������������
		this.setLocation((Constants.SCREEN_WIDTH - this.getWidth()) / 2, (Constants.SCREEN_HEIGHT - this.getHeight()) / 2);
		setTitle("����");
		
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
				"      ب�Ź���ϵͳ\n\n\n\n"
				+ "��ǰ�汾 : 1.0\n"
				+ "��Դ��Ʒ,��ע������.");
		JEditorPane editPane = new JEditorPane("text/plain", str);
		editPane.setOpaque(false);

		editPane.setEditable(false);
		panel_1.add(editPane, BorderLayout.CENTER);
	}

}
