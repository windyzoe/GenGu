package com.gengu.action;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.gengu.common.ConstantsDB;
import com.gengu.services.PurchaseService;
import com.gengu.services.SaleService;
import com.gengu.services.WarehouseService;
import com.gengu.ui.MainFrame;
import com.gengu.util.ExcelManage;

public class ExportAction
{
	private String strPath;
	private String strStyle;
	public ExportAction(String strPath,String strStyle)
	{
		this.strPath = strPath;
		this.strStyle = strStyle;
	}
	public void exportAction()
	{
		new SwingWorker<String, Void>()
		{
			@Override
			protected String doInBackground() throws Exception
			{
				List<Map<String, Object>> allInfo =null;
				switch (strStyle)
				{
				case "出入库":
				{
					allInfo = WarehouseService.getInstance().getAllList();
					break;
				}
				case "采购":
				{
					PurchaseService purchaseSev = PurchaseService.getInstance();
					allInfo =purchaseSev.getPurchaseList();
					break;
				}
				case "销售":
				{
					allInfo = SaleService.getInstance().getAllList();
					break;
				}

				default:
					break;
				}
				String strResult =exportExcel(allInfo);
				return strResult;
			}

			@Override
			protected void done()
			{
				try
				{
					String strResult =get();
					if (strResult.equals("OK"))
					{
						JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "导出成功", "Information", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(MainFrame.getInstance().frame, strResult, "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} catch (InterruptedException | ExecutionException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.execute();
	}
	private String[] getTitles()
	{
		switch (strStyle)
		{
		case "出入库":
		{
			return ConstantsDB.WareHouseHeadDB;
		}
		case "采购":
		{
			return ConstantsDB.PurchaseHeadDB;
		}
		case "销售":
		{
			return ConstantsDB.SaleHeadDB;
		}
		default:
			return null;
		}
	}
	private String exportExcel(List<Map<String, Object>> allInfo)
	{
		ExcelManage excelManage = new ExcelManage();
		String excelName = strStyle+System.currentTimeMillis();
		String exportPath = strPath+"\\"+excelName+".xls";
		try
		{
			excelManage.createExcel(exportPath, strStyle, this.getTitles());
			excelManage.writeToExcel(exportPath, strStyle, allInfo);
		} catch (Exception e)
		{
			e.printStackTrace();
			return  e.getMessage();
		}
		return "OK";
	}
}
