package com.gengu.common;

import java.io.File;

public class Constants
{
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

}
