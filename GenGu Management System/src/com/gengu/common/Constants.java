package com.gengu.common;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

public class Constants
{
	static Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
	public final static int SCREEN_WIDTH = SCREEN.width;
	public final static int SCREEN_HEIGHT = SCREEN.height;
	/**
	 * ��ʱĿ¼
	 */
	public final static String PATH_TEMP = "Resources";
	/**
	 * ͼƬĿ¼
	 */
	public final static String PATH_Image = PATH_TEMP+File.separator+"Image";

	/**
	 * ���ݿ�Ŀ¼
	 */
	public final static String PATH_JDBCProperty = PATH_TEMP + File.separator + "jdbc.properties";
	/**
	 * ب��ͼ��
	 */
	public final static String PATH_GenGuIcon = PATH_Image + File.separator + "GenGuIcon.png";
	/**
	 * �����С
	 */
	public final static int APPFONTSIZE = 12;
}
