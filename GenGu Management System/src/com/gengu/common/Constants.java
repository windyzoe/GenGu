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
	 * 临时目录
	 */
	public final static String PATH_TEMP = "Resources";
	/**
	 * 图片目录
	 */
	public final static String PATH_Image = PATH_TEMP+File.separator+"Image";

	/**
	 * 数据库目录
	 */
	public final static String PATH_JDBCProperty = PATH_TEMP + File.separator + "jdbc.properties";
	/**
	 * 亘古图标
	 */
	public final static String PATH_GenGuIcon = PATH_Image + File.separator + "GenGuIcon.png";
	/**
	 * 字体大小
	 */
	public final static int APPFONTSIZE = 12;
}
