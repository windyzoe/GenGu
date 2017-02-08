package com.gengu.component;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.gengu.ui.MainFrame;

public class CustomTable extends JTable
{
	/**
	 * 渲染器(改变颜色和居中)
	 */
	DateRenderer dateRenderer = new DateRenderer();
	
	public CustomTable(String[] titles)
	{
		DefaultTableModel warehousemodel = new DefaultTableModel(null, titles)
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		setModel(warehousemodel);
		setLineMiddle();
		setRowSelectionAllowed(rowSelectionAllowed);
	}

	public CustomTable()
	{

	}

	/**表格居中
	 * 
	 */
	private void setLineMiddle()
	{
		dateRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		this.setDefaultRenderer(Object.class, dateRenderer);
	}

	/**
	 * 根据数据内容自动调整列宽。//resize column width automatically
	 *
	 */
	public void fitTableColumns()
	{
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader header = this.getTableHeader();
		int rowCount = this.getRowCount();
		Enumeration<TableColumn> columns = this.getColumnModel().getColumns();
		while (columns.hasMoreElements())
		{
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
			int width = (int) header.getDefaultRenderer().getTableCellRendererComponent(this, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++)
			{
				int preferedWidth = (int) this.getCellRenderer(row, col).getTableCellRendererComponent(this, this.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column); // 此行很重要
			column.setWidth(width + this.getIntercellSpacing().width + 5);
		}
	}

	/**
	 * 批量更新单元格的值
	 * 
	 * @param map
	 *            key 列名 value 值 批量更新选中的行
	 */
	public void modifyTableValues(Map<String, String> map)
	{
		int[] rowIndexs = this.getSelectedRows();
		int columnSize = this.getColumnCount();
		for (int i = 0; i < columnSize; i++)
		{
			String strColumnName = this.getColumnName(i);
			if (!map.containsKey(strColumnName))
				continue;
			String strValue = map.get(strColumnName);
			for (int j : rowIndexs)
			{
				this.setValueAt(strValue, j, i);
			}
		}
	}

	public TableCellRenderer getCellRenderer(int row, int column)
	{
		return dateRenderer;
	}

	/**扩展渲染器内部类
	 * @author XUZH
	 *
	 */
	private class DateRenderer extends DefaultTableCellRenderer
	{
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			if (table==MainFrame.getInstance().warehouseTable)
			{
				if (table.getValueAt(row, 3).toString().equals("出库") ){
					com.setBackground(Color.red);
				}
				else
					com.setBackground(null);
				if (isSelected)
				{
					com.setBackground(selectionBackground);
				}
			}


			return com;
		}
		
		List<Integer> rows=new ArrayList<>();

		public void setColor(List<Integer> rows)
		{
			this.rows = rows;
		}
	}
}
