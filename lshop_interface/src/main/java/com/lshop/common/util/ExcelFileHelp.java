package com.lshop.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class ExcelFileHelp {

	private static final Log logger = LogFactory.getLog(ExcelFileHelp.class);
	public static List<String[]> parseExcel(File file) {
		List<String[]> list=new LinkedList<String[]>();
		 FileInputStream fin=null;
		try {
			 fin = new FileInputStream(file);
			Workbook workBook = Workbook.getWorkbook(fin);
			int sheetNum = workBook.getNumberOfSheets();
			Sheet sheet = null;
			for (int i = 0; i < sheetNum; ++i) {
				sheet = workBook.getSheet(i);
				list.addAll(parseSheet(sheet));
			}
		} catch (Exception be) {
			logger.error(be.getMessage());
		}finally{
			if (fin!=null) {
				try {
					
					fin.close();
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
			
		}
		return list;
	}

	public static List<String[]> parseSheet(Sheet sheet) {
		int rsRowNum = sheet.getRows();
		int rsColumnNum = sheet.getColumns();
		logger.info(sheet.getName() + ": row:" + rsRowNum + "  colum:" + rsColumnNum);
		Cell cell = null;
		List<String[]> list = new LinkedList<String[]>();
		for (int i = 0; i < rsRowNum; ++i) {
			String[] excelRow = new String[rsColumnNum];
			for (int j = 0; j < rsColumnNum; ++j) {
				cell = sheet.getCell(j, i);
				excelRow[j] = cell.getContents();
			}

			list.add(excelRow);
		}
		return list;
	}
	public static boolean create(String fileName, List... excelSheet) {
		try {
			WritableWorkbook workBook = Workbook.createWorkbook(new File(fileName));
			int index = 1;
			for (List sheetList : excelSheet) {
				WritableSheet sheet = workBook.createSheet("sheet" + index++, 0);
				int rowNum = sheetList.size();
				if (rowNum < 1)
					continue;
				int colNum = ((Object[]) sheetList.get(0)).length;
				for (int i = 0; i < rowNum; i++) {
					Object[] excelRow = (Object[]) sheetList.get(i);
					for (int j = 0; j < excelRow.length; j++) {
						String temp="";
						if (excelRow[j]!=null) {
							if (excelRow[j] instanceof Date) {
								temp=DateUtils.formatDate((Date)excelRow[j], "yyyy-MM-dd HH:mm");
							}else {
								temp=excelRow[j].toString();
							}
						}
						Label l = new Label(j, i,temp);
						sheet.addCell(l);
					}
				}
			}
			workBook.write();
			workBook.close();

		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}
}
