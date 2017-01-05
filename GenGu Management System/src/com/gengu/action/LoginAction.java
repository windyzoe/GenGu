package com.gengu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.gengu.ui.LoginFrame;

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
			JOptionPane.showMessageDialog(loginFrame, "用户名密码不为空！", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
	}
}
