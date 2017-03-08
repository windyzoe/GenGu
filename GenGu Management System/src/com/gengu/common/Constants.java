package com.gengu.common;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

public class Constants
{
	static Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * 屏幕宽度
	 */
	public final static int SCREEN_WIDTH = SCREEN.width;
	/**
	 * 屏幕高度
	 */
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
	 * 登录界面
	 */
	public final static String PATH_Login = PATH_Image + File.separator + "GenGuLogin.png";
	/**
	 * 修改图标
	 */
	public final static String PATH_Modify = PATH_Image + File.separator + "modify.png";
	/**
	 * 删除图标
	 */
	public final static String PATH_delete = PATH_Image + File.separator + "delete.png";
	/**
	 * 刷新图标
	 */
	public final static String PATH_Refresh= PATH_Image + File.separator + "refresh.png";
	/**
	 * 入库图标
	 */
	public final static String PATH_StoreIn= PATH_Image + File.separator + "storeIn.png";
	/**
	 * 出库图标
	 */
	public final static String PATH_StoreOut= PATH_Image + File.separator + "storeOut.png";
	/**
	 * 销售图标
	 */
	public final static String PATH_Sale= PATH_Image + File.separator + "sale.png";
	/**
	 * 采购图标
	 */
	public final static String PATH_Purchase= PATH_Image + File.separator + "purchase.png";
	/**
	 * 侧拉框图标
	 */
	public final static String PATH_LeftWindow= PATH_Image + File.separator + "leftwindow.png";
	/**
	 * 字体大小
	 */
	public final static int APPFONTSIZE = 14;
	/**
	 * 列表中的行数(用于分页)
	 */
	public final static int ROWSIZE = 30;
	/**
	 * 图标大小
	 */
	public final static int ICONSIZE = 17;
}
