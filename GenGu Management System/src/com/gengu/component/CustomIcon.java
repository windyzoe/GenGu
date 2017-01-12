package com.gengu.component;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.gengu.common.Constants;

public class CustomIcon extends ImageIcon
{
	public CustomIcon(String path)
	{
		super(path);
		setImage(getImage().getScaledInstance(Constants.ICONSIZE, Constants.ICONSIZE, Image.SCALE_SMOOTH));
	}
}
