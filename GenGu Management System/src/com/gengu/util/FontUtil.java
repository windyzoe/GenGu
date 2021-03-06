package com.gengu.util;

import java.awt.Font;
import java.io.File;

public class FontUtil
{
	/**
	 * 根据字体文件获取字体
	 * 
	 * @param filePath
	 * @param fontSize
	 *            字体大小
	 * @return
	 */
	public static Font getFontByFile(String filePath, int fontStyle, int fontSize)
	{
		Font font = null;
		try
		{
			// 推荐用这种，原因请详细看http://www.cnblogs.com/zcy_soft/p/3503656.html
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
	 * 获取默认字体
	 * 
	 * @param fontSize
	 * @return
	 */
	public static Font getBaseFont(int fontSize)
	{
		return new Font("黑体", Font.PLAIN, fontSize);
	}

}
