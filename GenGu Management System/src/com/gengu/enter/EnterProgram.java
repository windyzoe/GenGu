package com.gengu.enter;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel;

import com.gengu.common.Constants;
import com.gengu.ui.LoginFrame;
import com.gengu.ui.MainFrame;
import com.gengu.util.FontUtil;

/**
 * 主程序入口
 * @author XUZH
 *
 */
public class EnterProgram
{
	static LoginFrame loginFrame;
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// EDT中做UI的创建和更新
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
					loginFrame=new LoginFrame();
					loginFrame.setVisible(true);//登陆界面是去窗口边界的,在登录界面显示以后再设置setDefaultLookAndFeelDecorated
					JDialog.setDefaultLookAndFeelDecorated(true);
					JFrame.setDefaultLookAndFeelDecorated(true);
					MainFrame window = MainFrame.getInstance();
					window.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		init();
	}
	/**
	 * 基于swingWorker做了后台处理
	 * doInBackground是用来做数据处理的
	 * 处理完之后才会在done里面做EDT线程的UI更新
	 */
	protected static void init()
	{
		new SwingWorker<Void, Void>()
		{
			@Override
			protected Void doInBackground()
			{
				initGlobalFont(FontUtil.getBaseFont(Constants.APPFONTSIZE));
				return null;
			}
			@Override
			protected void done()
			{
			}
		}.execute();
	}

	/**
	 * 统一设置字体，父界面设置之后，所有由父界面进入的子界面都不需要再次设置字体
	 */
	private static void initGlobalFont(Font font)
	{
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();)
		{
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource)
			{
				UIManager.put(key, fontRes);
			}
		}
	}
}
