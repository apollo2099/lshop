package com.lshop.common.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.gv.core.util.ObjectUtils;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [gv_hmall_web] 
 * @Package:      [com.lshop.common.util.POIExcelUtil.java]  
 * @ClassName:    [POIExcelUtil]   
 * @Description:  [POI实现EXCEL表格的读取和创建]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2013-7-5 下午03:55:20]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2013-7-5 下午03:55:20]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class POIExcelUtil {

		

	
	/**
	 * 解析excel表格兼容2003，2007
	 * @Method: parseExcelXlsx 
	 * @param filePath 解析文件路径
	 * @return List<List>
	 */
	public static List<String []> parseExcel(String filePath) {
		String extention=StringUtil.getFileExtention(filePath);//获取文件扩展名
		List<String []> list=null;
		try {
			if(extention.equals("xlsx")||extention.equals("xlsm")){
			    // 创建对Excel工作簿文件的引用
				XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(filePath));
				// 获取默认第一个sheet
				XSSFSheet sheet = xwb.getSheetAt(0);
				// 解析sheet中的内容
				list=parseSheet(sheet);
			}else if(extention.equals("xls")){
				// 创建对Excel工作簿文件的引用
				HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
				// 获取默认第一个sheet
				HSSFSheet sheet = workbook.getSheetAt(0);
				// 解析sheet中的内容
				list=parseSheet(sheet);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
    /**
     * 数据解析组装
     * @Method: parseSheet 
     * @param sheet
     * @return List<List>
     */
	public static List<String []> parseSheet(Sheet sheet) {
		List<String []> list = new ArrayList<String []>();
		for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row= sheet.getRow(i);
			if(row!=null){
				String [] cellStr=new String[row.getPhysicalNumberOfCells()];
				String result="";
				for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
					if(ObjectUtils.isNotEmpty(row.getCell(j))){
						Cell cell= row.getCell(j);
						if(cell.getCellType()==cell.CELL_TYPE_FORMULA){
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							Double temp =cell.getNumericCellValue();
							result=temp.toString();
							
						}else{
							result = row.getCell(j).toString();
						}
						cellStr[j]=result;
					}else{
						cellStr[j]=result;
					}
				}
				list.add(cellStr);
			}
		}
		return list;
	}
	
    /**
     * 根据知道excel模版指定对应的目录生成excel文件内容填充(兼容2003，2007),默认只操作一个工作簿
     * @Method: createExcel 
     * @param modelPath 源文件目录
     * @param destPath  输出文件目录
     * @param startIndex 输入开始行位置
     * @param excelSheet 输入参数集合
     * @return boolean
     */
	public static boolean createExcel(String modelPath,String destPath,Integer startRowNum, List... excelSheet) {
		try {
			Workbook work=null;
			String extention=StringUtil.getFileExtention(modelPath);//获取文件扩展名
			InputStream in = new FileInputStream(modelPath);
			if(extention.equals("xlsx")||extention.equals("xlsm")){
			   work = new XSSFWorkbook(in);
			}else if(extention.equals("xls")){
			   work = new HSSFWorkbook(in);
			}
			// 得到excel的第0张表
			Sheet sheet = work.getSheetAt(0);
			// 得到第1行的第一个单元格的样式
			Row rowCellStyle = sheet.getRow(0);
			CellStyle columnOne = rowCellStyle.getCell(0).getCellStyle();

			for (List sheetList : excelSheet) {
				int rowNum = sheetList.size();
				if (rowNum < 1)
					continue;
				int colNum = ((String[]) sheetList.get(0)).length;
				System.out.println("rowNum:" + rowNum + " colNum:" + colNum );
				for (int i = startRowNum; i < rowNum+startRowNum; i++) {
					Row row = sheet.createRow(i);// 得到行
					String[] excelRow = (String[]) sheetList.get(i-startRowNum);
					for (int j = 0; j < excelRow.length; j++) {						
						Cell cell= row.createCell(j);// 得到单元格
						String cellValue = excelRow[j];
//						if(j==0){
//							if(cellValue.indexOf(".")!=-1){
//								cellValue = cellValue.substring(0, cellValue.indexOf("."));
//							}
//						}
						cell.setCellValue(cellValue);// 填充值
						cell.setCellStyle(columnOne);// 填充样式
					}
				}	
			}
		
			FileOutputStream os = new FileOutputStream(destPath);
			work.write(os);
			os.close();
		} catch (FileNotFoundException e) {
			System.out.println("文件路径错误");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件输入流错误");
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	/**
	 * 
	 * @Method: createExcelForXLS 
	 * @Description:  [使用POI生成xls格式的表格文件]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-29 上午11:42:22]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-29 上午11:42:22]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param destPath 输出文件目录
	 * @param startRowNum 开始输出行数
	 * @param excelShee 输出内容
	 * @return boolean  返回结果
	 */
	public static boolean createExcelForXLS(String destPath,Integer startRowNum,List... excelSheet) {
		try {

            // 创建Excel的工作书册 Workbook,对应到一个excel文档
            HSSFWorkbook work = new HSSFWorkbook();
		    // 创建Excel的工作sheet,对应到一个excel文档的tab
            HSSFSheet sheet = work.createSheet("sheet1");

            // 设置excel每列宽度
            sheet.setColumnWidth(0, 4000);
            sheet.setColumnWidth(1, 3500);

            // 创建字体样式
            HSSFFont font =work.createFont();
            font.setFontName("Verdana");
            font.setBoldweight((short) 100);
            font.setFontHeight((short) 300);
            font.setColor(HSSFColor.BLUE.index);

            // 创建单元格样式
            HSSFCellStyle style = work.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            // 设置边框
            style.setBottomBorderColor(HSSFColor.RED.index);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			
			for (List sheetList : excelSheet) {
				int rowNum = sheetList.size();
				if (rowNum < 1)
					continue;
				int colNum = ((String[]) sheetList.get(0)).length;
				System.out.println("rowNum:" + rowNum + " colNum:" + colNum );
				for (int i = startRowNum; i < rowNum+startRowNum; i++) {
					Row row = sheet.createRow(i);// 得到行
					String[] excelRow = (String[]) sheetList.get(i-startRowNum);
					for (int j = 0; j < excelRow.length; j++) {						
						Cell cell= row.createCell(j);// 得到单元格
						String cellValue = excelRow[j];
						cell.setCellValue(cellValue);// 填充值
						cell.setCellStyle(style);// 填充样式
					}
				}	
			}
		
			FileOutputStream os = new FileOutputStream(destPath);
			work.write(os);
			os.close();
		} catch (FileNotFoundException e) {
			System.out.println("文件路径错误");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件输入流错误");
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	

	
	public static void main(String[] args) {  
//		//读取excel测试
//        String fileToBeRead="C:/Documents and Settings/Administrator/桌面/test.xlsm";
//	    String readPath="C:/Documents and Settings/Administrator/桌面/数字中国连锁店B2B网站详细需求20130710/批量导入购买属性模板.xlsx";
//
//		List<String []> list=parseExcel(fileToBeRead);
//		for (String[] strings : list) {
//			System.out.println(strings.length);
//			for (String string : strings) {
//				System.out.print(string+",");
//			}
//			System.out.println("");
//		}
//		System.out.println(list);
//		List<String []> list2=parseExcel(readPath);
//		System.out.println(list2);
		
		
		//创建excel测试
		String destPath="E:/exportTest.xls";
		List list=new ArrayList();
		String[] attr = new String[2];
		attr[0]="201206295644";
		attr[1]="201206295644";
		String [] attr1=new String[2];
		attr1[0]="301206295644";
		attr1[1]="301206295644";
		list.add(attr);
		list.add(attr1);
		
		createExcelForXLS(destPath, 1, list);                           
	}
	


	
}
