package com.gengu.common;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

public class Constants
{
	static Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * ��Ļ���
	 */
	public final static int SCREEN_WIDTH = SCREEN.width;
	/**
	 * ��Ļ�߶�
	 */
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
	 * ��¼����
	 */
	public final static String PATH_Login = PATH_Image + File.separator + "GenGuLogin.png";
	/**
	 * �޸�ͼ��
	 */
	public final static String PATH_Modify = PATH_Image + File.separator + "modify.png";
	/**
	 * ɾ��ͼ��
	 */
	public final static String PATH_delete = PATH_Image + File.separator + "delete.png";
	/**
	 * ˢ��ͼ��
	 */
	public final static String PATH_Refresh= PATH_Image + File.separator + "refresh.png";
	/**
	 * ���ͼ��
	 */
	public final static String PATH_StoreIn= PATH_Image + File.separator + "storeIn.png";
	/**
	 * ����ͼ��
	 */
	public final static String PATH_StoreOut= PATH_Image + File.separator + "storeOut.png";
	/**
	 * ����ͼ��
	 */
	public final static String PATH_Sale= PATH_Image + File.separator + "sale.png";
	/**
	 * �ɹ�ͼ��
	 */
	public final static String PATH_Purchase= PATH_Image + File.separator + "purchase.png";
	/**
	 * ������ͼ��
	 */
	public final static String PATH_LeftWindow= PATH_Image + File.separator + "leftwindow.png";
	/**
	 * �����С
	 */
	public final static int APPFONTSIZE = 14;
	/**
	 * �б��е�����(���ڷ�ҳ)
	 */
	public final static int ROWSIZE = 30;
	/**
	 * ͼ���С
	 */
	public final static int ICONSIZE = 17;
}
