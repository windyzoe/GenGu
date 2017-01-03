package com.gengu.util;

import java.awt.Font;
import java.io.File;

public class FontUtil
{
	/**
	 * ���������ļ���ȡ����
	 * 
	 * @param filePath
	 * @param fontSize
	 *            �����С
	 * @return
	 */
	public static Font getFontByFile(String filePath, int fontStyle, int fontSize)
	{
		Font font = null;
		try
		{
			// �Ƽ������֣�ԭ������ϸ��http://www.cnblogs.com/zcy_soft/p/3503656.html
			font = Font.createFont(Font.TRUETYPE_FONT, new File(filePath));
			font = font.deriveFont(fontStyle, fontSize);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		if (font == null)
		{
			font = getBaseFont(fontSize);
		}

		return font;
	}

	/**
	 * ��ȡĬ������
	 * 
	 * @param fontSize
	 * @return
	 */
	public static Font getBaseFont(int fontSize)
	{
		return new Font("΢���ź�", Font.PLAIN, fontSize);
	}

}
