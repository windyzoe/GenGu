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
 * ���������
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
		// EDT����UI�Ĵ����͸���
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
					loginFrame=new LoginFrame();
					loginFrame.setVisible(true);//��½������ȥ���ڱ߽��,�ڵ�¼������ʾ�Ժ�������setDefaultLookAndFeelDecorated
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
	 * ����swingWorker���˺�̨����
	 * doInBackground�����������ݴ����
	 * ������֮��Ż���done������EDT�̵߳�UI����
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
	 * ͳһ�������壬����������֮�������ɸ����������ӽ��涼����Ҫ�ٴ���������
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
