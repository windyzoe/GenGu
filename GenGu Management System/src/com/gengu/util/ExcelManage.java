package com.gengu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * ��excel��ȡ����/��excel��д�� excel�б�ͷ����ͷÿ�е����ݶ�Ӧʵ���������
 * 
 * @author nagsh
 * 
 */
public class ExcelManage
{
	private HSSFWorkbook workbook = null;

	/**
	 * �ж��ļ��Ƿ����.
	 * 
	 * @param fileDir
	 *            �ļ�·��
	 * @return
	 */
	public boolean fileExist(String fileDir)
	{
		boolean flag = false;
		File file = new File(fileDir);
		flag = file.exists();
		return flag;
	}

	/**
	 * �ж��ļ���sheet�Ƿ����.
	 * 
	 * @param fileDir
	 *            �ļ�·��
	 * @param sheetName
	 *            ���������
	 * @return
	 */
	public boolean sheetExist(String fileDir, String sheetName)
	{
		boolean flag = false;
		File file = new File(fileDir);
		if (file.exists())
		{ // �ļ�����
			// ����workbook
			try
			{
				workbook = new HSSFWorkbook(new FileInputStream(file));
				// ���Worksheet�������sheetʱ���ɵ�xls�ļ���ʱ�ᱨ��)
				HSSFSheet sheet = workbook.getSheet(sheetName);
				if (sheet != null)
					flag = true;
			} catch (Exception e)
			{
				e.printStackTrace();
			}

		} else
		{ // �ļ�������
			flag = false;
		}

		return flag;
	}

	/**
	 * ������excel.
	 * 
	 * @param fileDir
	 *            excel��·��
	 * @param sheetName
	 *            Ҫ�����ı������
	 * @param titleRow
	 *            excel�ĵ�һ�м����ͷ
	 * @throws Exception 
	 */
	public void createExcel(String fileDir, String sheetName, String titleRow[]) throws Exception
	{
		// ����workbook
		workbook = new HSSFWorkbook();
		// ���Worksheet�������sheetʱ���ɵ�xls�ļ���ʱ�ᱨ��)
		Sheet sheet1 = workbook.createSheet(sheetName);
		// �½��ļ�
		FileOutputStream out = null;
		try
		{
			// ��ӱ�ͷ
			Row row = workbook.getSheet(sheetName).createRow(0); // ������һ��
			for (int i = 0; i < titleRow.length; i++)
			{
				Cell cell = row.createCell(i);
				cell.setCellValue(titleRow[i]);
			}

			out = new FileOutputStream(fileDir);
			workbook.write(out);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			try
			{
				out.close();
			} catch (IOException e)
			{
				throw e;
			}
		}

	}

	/**
	 * ɾ���ļ�.
	 * 
	 * @param fileDir
	 *            �ļ�·��
	 */
	public boolean deleteExcel(String fileDir)
	{
		boolean flag = false;
		File file = new File(fileDir);
		// �ж�Ŀ¼���ļ��Ƿ����
		if (!file.exists())
		{ // �����ڷ��� false
			return flag;
		} else
		{
			// �ж��Ƿ�Ϊ�ļ�
			if (file.isFile())
			{ // Ϊ�ļ�ʱ����ɾ���ļ�����
				file.delete();
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * ��excel��д��(�Ѵ��ڵ������޷�д��).
	 * 
	 * @param fileDir
	 *            �ļ�·��
	 * @param sheetName
	 *            �������
	 * @param object
	 */
	public void writeToExcel(String fileDir, String sheetName, Object object)
	{
		// ����workbook
		File file = new File(fileDir);
		try
		{
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		// ��
		FileOutputStream out = null;
		HSSFSheet sheet = workbook.getSheet(sheetName);
		// ��ȡ����������
		int rowCount = sheet.getLastRowNum() + 1; // ��Ҫ��һ
		// ��ȡ��ͷ������
		int columnCount = sheet.getRow(0).getLastCellNum();
		try
		{
			Row row = sheet.createRow(rowCount); // ����Ҫ��ӵ�һ��
			// ͨ��������object���ֶ�,��Ӧ��ͷ����
			// ��ȡ�ö����class����
			Class class_ = object.getClass();
			// ��ñ�ͷ�ж���
			HSSFRow titleRow = sheet.getRow(0);
			if (titleRow != null)
			{
				for (int columnIndex = 0; columnIndex < columnCount; columnIndex++)
				{ // ������ͷ
					String title = titleRow.getCell(columnIndex).toString().trim().toString().trim();
					String UTitle = Character.toUpperCase(title.charAt(0)) + title.substring(1, title.length()); // ʹ������ĸ��д;
					String methodName = "get" + UTitle;
					Method method = class_.getDeclaredMethod(methodName); // ����Ҫִ�еķ���
					String data = method.invoke(object).toString(); // ִ�и�get����,��Ҫ���������
					Cell cell = row.createCell(columnIndex);
					cell.setCellValue(data);
				}
			}

			out = new FileOutputStream(fileDir);
			workbook.write(out);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				out.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * ��excel��д��һ��(�Ѵ��ڵ������޷�д��).
	 * 
	 * @param fileDir
	 *            �ļ�·��
	 * @param sheetName
	 *            �������
	 * @param mapInfo
	 *            key:title value:cellValue
	 */
	public void writeToExcel(String fileDir, String sheetName, Map<String, String> mapInfo)
	{
		// ����workbook
		File file = new File(fileDir);
		try
		{
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		// ��
		FileOutputStream out = null;
		HSSFSheet sheet = workbook.getSheet(sheetName);
		// ��ȡ����������
		int rowCount = sheet.getLastRowNum() + 1; // ��Ҫ��һ
		// ��ȡ��ͷ������
		int columnCount = sheet.getRow(0).getLastCellNum();
		try
		{
			Row row = sheet.createRow(rowCount); // ����Ҫ��ӵ�һ��
			// ��ñ�ͷ�ж��� ,map�е�keyֵҪ�ͱ�ͷ��һ�ж�Ӧ
			HSSFRow titleRow = sheet.getRow(0);
			if (titleRow != null)
			{
				for (int columnIndex = 0; columnIndex < columnCount; columnIndex++)
				{
					String title = titleRow.getCell(columnIndex).toString().trim().toUpperCase();
					String data = mapInfo.get(title);

					Cell cell = row.createCell(columnIndex);
					cell.setCellValue(data);
				}
			}
			out = new FileOutputStream(fileDir);
			workbook.write(out);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				out.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * ��excel��д��ܶ���(�Ѵ��ڵ������޷�д��).
	 * 
	 * @param fileDir
	 *            �ļ�·��
	 * @param sheetName
	 *            �������
	 * @param mapInfo
	 *            key:title value:cellValue
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void writeToExcel(String fileDir, String sheetName, List<Map<String, Object>> mapInfos) throws FileNotFoundException, IOException
	{
		// ����workbook
		File file = new File(fileDir);

		workbook = new HSSFWorkbook(new FileInputStream(file));

		// ��
		FileOutputStream out = null;
		HSSFSheet sheet = workbook.getSheet(sheetName);
		// ��ȡ����������
		int rowCount = sheet.getLastRowNum() + 1; // ��Ҫ��һ
		// ��ȡ��ͷ������
		int columnCount = sheet.getRow(0).getLastCellNum();
		try
		{
			Row row = sheet.createRow(rowCount); // ����Ҫ��ӵ�һ��
			// ��ñ�ͷ�ж��� ,map�е�keyֵҪ�ͱ�ͷ��һ�ж�Ӧ
			HSSFRow titleRow = sheet.getRow(0);
			if (titleRow != null)
			{
				for (Map<String, Object> mapInfo : mapInfos)
				{
					for (int columnIndex = 0; columnIndex < columnCount; columnIndex++)
					{
						String title = titleRow.getCell(columnIndex).toString().trim().toUpperCase();
						String data = mapInfo.get(title).toString();

						Cell cell = row.createCell(columnIndex);
						cell.setCellValue(data);
					}
					rowCount++;
					row = sheet.createRow(rowCount); // ����Ҫ��ӵ�һ��
				}

			}
			out = new FileOutputStream(fileDir);
			workbook.write(out);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			try
			{
				out.close();
			} catch (IOException e)
			{
				throw e;
			}
		}
	}

	/**
	 * ��ȡexcel���е�����.
	 * 
	 * @param fileDir
	 *            �ļ�·��
	 * @param sheetName
	 *            �������(EXCEL �Ƕ���ĵ�,������Ҫ����������ţ���sheet1)
	 * @param object
	 *            object
	 */
	public List readFromExcel(String fileDir, String sheetName, Object object)
	{
		// ����workbook
		File file = new File(fileDir);
		try
		{
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		List result = new ArrayList();
		// ��ȡ�ö����class����
		Class class_ = object.getClass();
		// ��ø������������
		Field[] fields = class_.getDeclaredFields();

		// ��ȡexcel����
		// ���ָ����excel��
		HSSFSheet sheet = workbook.getSheet(sheetName);
		// ��ȡ����������
		int rowCount = sheet.getLastRowNum() + 1; // ��Ҫ��һ
		System.out.println("rowCount:" + rowCount);
		if (rowCount < 1)
		{
			return result;
		}
		// ��ȡ��ͷ������
		int columnCount = sheet.getRow(0).getLastCellNum();
		// ��ȡ��ͷ��Ϣ,ȷ����Ҫ�õķ�����---set����
		// ���ڴ洢������
		String[] methodNames = new String[columnCount]; // ��ͷ������Ϊ��Ҫ��set��������
		// ���ڴ洢��������
		String[] fieldTypes = new String[columnCount];
		// ��ñ�ͷ�ж���
		HSSFRow titleRow = sheet.getRow(0);
		// ����
		for (int columnIndex = 0; columnIndex < columnCount; columnIndex++)
		{ // ������ͷ��
			String data = titleRow.getCell(columnIndex).toString(); // ĳһ�е�����
			String Udata = Character.toUpperCase(data.charAt(0)) + data.substring(1, data.length()); // ʹ������ĸ��д
			methodNames[columnIndex] = "set" + Udata;
			for (int i = 0; i < fields.length; i++)
			{ // ������������
				if (data.equals(fields[i].getName()))
				{ // �������ͷ���
					fieldTypes[columnIndex] = fields[i].getType().getName(); // ���������ͷŵ�������
				}
			}
		}
		// ���ж�ȡ���� ��1��ʼ ���Ա�ͷ
		for (int rowIndex = 1; rowIndex < rowCount; rowIndex++)
		{
			// ����ж���
			HSSFRow row = sheet.getRow(rowIndex);
			if (row != null)
			{
				Object obj = null;
				// ʵ�����÷�����Ķ���һ������
				try
				{
					obj = class_.newInstance();
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}

				// ��ñ����и���Ԫ���е�����
				for (int columnIndex = 0; columnIndex < columnCount; columnIndex++)
				{
					String data = row.getCell(columnIndex).toString();
					// ��ȡҪ���÷����ķ�����
					String methodName = methodNames[columnIndex];
					Method method = null;
					try
					{
						// �ⲿ�ֿ��Լ���չ
						if (fieldTypes[columnIndex].equals("java.lang.String"))
						{
							method = class_.getDeclaredMethod(methodName, String.class); // ����Ҫִ�еķ���--set��������ΪString
							method.invoke(obj, data); // ִ�и÷���
						} else if (fieldTypes[columnIndex].equals("int"))
						{
							method = class_.getDeclaredMethod(methodName, int.class); // ����Ҫִ�еķ���--set��������Ϊint
							double data_double = Double.parseDouble(data);
							int data_int = (int) data_double;
							method.invoke(obj, data_int); // ִ�и÷���
						}
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				result.add(obj);
			}
		}
		return result;
	}

	// public static void main(String[] args) {
	// ExcelManage em = new ExcelManage();
	// //�ж��ļ��Ƿ����
	// System.out.println(em.fileExist("E:/test2.xls"));
	// //�����ļ�
	// String title[] = {"id","name","password"};
	// em.createExcel("E:/test2.xls","sheet1",title);
	// //�ж�sheet�Ƿ����
	// System.out.println(em.sheetExist("E:/test2.xls","sheet1"));
	// //д�뵽excel
	// User user = new User();
	// user.setId(5);
	// user.setName("qwer");
	// user.setPassword("zxcv");
	// User user3 = new User();
	// user3.setId(6);
	// user3.setName("qwerwww");
	// user3.setPassword("zxcvwww");
	// em.writeToExcel("E:/test2.xls","sheet1",user);
	// em.writeToExcel("E:/test2.xls","sheet1",user3);
	// //��ȡexcel
	// User user2 = new User();
	// List list = em.readFromExcel("E:/test2.xls","sheet1", user2);
	// for (int i = 0; i < list.size(); i++) {
	// User newUser = (User) list.get(i);
	// System.out.println(newUser.getId() + " " + newUser.getName() + " "
	// + newUser.getPassword());
	// }
	// //ɾ���ļ�
	// //System.out.println(em.deleteExcel("E:/test2.xls"));
	// }

}
