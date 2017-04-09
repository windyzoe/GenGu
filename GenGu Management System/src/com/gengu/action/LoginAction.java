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
 * 登录请求处理类
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
			JOptionPane.showMessageDialog(loginFrame, "用户名密码不为空！", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (JdbcUtil.isUserTrue(strUser, strPassword))
		{
			System.out.println("成功登录!");
			System.setProperty("genguuser", strUser);
			System.setProperty("gengupassword", strPassword);
			loginFrame.dispose();
			MainFrame.getInstance().frame.setVisible(true);
			//当登录成功后,读取出入库信息,显示在界面上
			new TableController().refreshControl();
		}else{
			JOptionPane.showMessageDialog(loginFrame, "用户名密码不正确！", "WARNING", JOptionPane.WARNING_MESSAGE);
		}
	}
}
