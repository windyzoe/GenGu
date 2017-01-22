package com.gengu.util;

import java.util.Enumeration;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.gengu.ui.MainFrame;

public class JTableUtil
{
	/**
	 * 根据数据内容自动调整列宽。//resize column width automatically
	 *
	 */
	public static void fitTableColumns(JTable myTable)
	{
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration<TableColumn> columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements())
		{
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
			int width = (int) header.getDefaultRenderer().getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++)
			{
				int preferedWidth = (int) myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column); // 此行很重要
			column.setWidth(width + myTable.getIntercellSpacing().width+5);
		}
	}
	public static void modifyTableValues(Map<String, String> map)
	{
		int[] rowIndexs = MainFrame.getInstance().getCurrentTable().getSelectedRows();
		JTable jTable = MainFrame.getInstance().getCurrentTable();
		int columnSize = jTable.getColumnCount();
		for (int i = 0; i < columnSize; i++)
		{
			String strColumnName = jTable.getColumnName(i);
			if (!map.containsKey(strColumnName))
				continue;
			String strValue = map.get(strColumnName);
			for (int j : rowIndexs)
			{
				jTable.setValueAt(strValue, j, i);
			}
		}
	}
}
