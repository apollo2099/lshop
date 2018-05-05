package com.lshop.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {

	private HSSFWorkbook workbook =  new HSSFWorkbook();
	private HSSFSheet sheet = workbook.createSheet();
	private HSSFCellStyle cellStyle = workbook.createCellStyle();
	private int rowIndex = 0;

	/**
	 * 导出简单格式的Excel文件
	 * @param outputPath	--->文件输出路径（包括文件名的完整路径）
	 * @param title			--->标题栏
	 * @param result		--->内容（list中的String数组代表Excel中的一行，数组中的一个元素对应行中的一列）
	 */
	public synchronized static void exportSimpleExcel(String outputPath, String[] title, List<String[]> result) {
		exportSimpleExcel(outputPath, title, result, null);
	}
	
	/**
	 * 导出简单格式的Excel文件
	 * @param outputPath	--->文件输出路径（包括文件名的完整路径）
	 * @param title			--->标题栏
	 * @param result		--->内容（list中的String数组代表Excel中的一行，数组中的一个元素对应行中的一列）
	 * @param cellWidth		--->列的宽度（单位=byte）
	 */
	public synchronized static void exportSimpleExcel(String outputPath, String[] title, List<String[]> result, int[] cellWidth) {
		exportSimpleExcel(outputPath, title, result, cellWidth, 20);
	}
	/**
	 * 导出简单格式的Excel文件
	 * @param outputPath	--->文件输出路径（包括文件名的完整路径）
	 * @param title			--->标题栏
	 * @param result		--->内容（list中的String数组代表Excel中的一行，数组中的一个元素对应行中的一列）
	 * @param cellWidth		--->列的宽度（单位=byte）
	 * @param rowHeight		--->行的高度（单位=px）
	 */
	public synchronized static void exportSimpleExcel(String outputPath, String[] title, List<String[]> result, int[] cellWidth, Integer rowHeight) {
		ExportExcel excel = new ExportExcel();
		HSSFRow headRow = excel.createHeadRow(title);
		headRow.setHeight((short)(30 * 15));
		if (result != null) {
			for (String[] item : result) {
				HSSFRow row = excel.createRow(item);
				if (rowHeight != null) {
					row.setHeight((short)(rowHeight * 15));
				}
			}
		}
		if (cellWidth != null) {
			excel.setCellsWidth(cellWidth);
		}
		excel.outputExcel(outputPath);
	}
	
	
	public void setCellsWidth(int[] widths) {
		for (int i = 0; i < widths.length; i++) {
			sheet.setColumnWidth(i, widths[i] * 256);
		}
	}
	
	public HSSFRow createRow(String[] cells) {
		return createRow(cells, null);
	}
	
	public HSSFRow createRow(String[] cells, HSSFCellStyle cellStyle) {
		HSSFRow row = this.sheet.createRow(rowIndex++);
		for (int i = 0; i < cells.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(cells[i]);
			if (cellStyle != null) {
				cell.setCellStyle(cellStyle);
			} else {
				cell.setCellStyle(this.cellStyle);
			}
		}
		return row;
	}
	
	public HSSFRow createHeadRow(String[] cells) {
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		//cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setWrapText(true);
		cellStyle.setFont(font);
		
		return createRow(cells, cellStyle);
	}
	
	public void outputExcel(String outputPath) {
		OutputStream stream = null;
		File file = new File(outputPath);
		try {
			stream = new FileOutputStream(file);
			this.workbook.write(stream);
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HSSFWorkbook getWorkbook() {
		return this.workbook;
	}

	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public HSSFSheet getSheet() {
		return this.sheet;
	}

	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}

	public HSSFCellStyle getCellStyle() {
		return this.cellStyle;
	}

	public void setCellStyle(HSSFCellStyle cellStyle) {
		this.cellStyle = cellStyle;
	}
	
}
