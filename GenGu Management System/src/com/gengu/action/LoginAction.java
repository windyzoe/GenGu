package com.gengu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.gengu.controller.TableController;
import com.gengu.ui.LoginFrame;
import com.gengu.ui.MainFrame;
import com.gengu.util.JdbcUtil;

/**
 * ��¼��������
 * @author XUZH
 *
 */
public class LoginAction implements ActionListener
{
	LoginFrame loginFrame;
	public  LoginAction(LoginFrame parentFrame)
	{
		this.loginFrame=parentFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String strPassword = loginFrame.getPassword();
		String strUser = loginFrame.getUserString();
		if (strPassword.equals("")||strUser.equals(""))
		{
			JOptionPane.showMessageDialog(loginFrame, "�û������벻Ϊ�գ�", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (JdbcUtil.isUserTrue(strUser, strPassword))
		{
			System.out.println("�ɹ���¼!");
			System.setProperty("genguuser", strUser);
			System.setProperty("gengupassword", strPassword);
			loginFrame.dispose();
			MainFrame.getInstance().frame.setVisible(true);
			//����¼�ɹ���,��ȡ�������Ϣ,��ʾ�ڽ�����
			new TableController().refreshControl();
		}else{
			JOptionPane.showMessageDialog(loginFrame, "�û������벻��ȷ��", "WARNING", JOptionPane.WARNING_MESSAGE);
		}
	}
}
