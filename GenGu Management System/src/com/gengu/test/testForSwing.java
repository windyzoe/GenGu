package com.gengu.test;

import javax.swing.*;

import javax.swing.table.*;

import java.awt.*;

import java.awt.event.*;

import java.util.*;

public class testForSwing
{

	public testForSwing()
	{

		JFrame f = new JFrame();

		Object[][] p =
		{
				{ "阿呆", new Integer(66), new Integer(32), new Integer(98), new Boolean(false), new Boolean(false) },

				{ "阿呆", new Integer(82), new Integer(69), new Integer(128), new Boolean(true), new Boolean(false) }, };

		String[] n =
		{ "姓名", "语文", "数学", "总分", "及格", "作弊" };

		JTable table = new JTable(p, n);

		table.setPreferredScrollableViewportSize(new Dimension(550, 30));

		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		JScrollPane scrollPane = new JScrollPane(table);

		f.getContentPane().add(scrollPane, BorderLayout.CENTER);

		f.setTitle("Simple Table");
		f.pack();
		f.show();
		f.setVisible(true);

		f.addWindowListener(

				new WindowAdapter()
				{

					public void windowClosing(WindowEvent e)
					{

						System.exit(0);
					}
				});

	}

	public static void main(String[] args)
	{

		new testForSwing();
	}

}
