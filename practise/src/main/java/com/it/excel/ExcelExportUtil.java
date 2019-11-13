package com.it.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelExportUtil {

//	private static final TsLogger logger = TsLogger.getLogger(ExcelExportUtil.class);

	public static void export(String sheetName, String titleName, String fileName, int columnNumber,
			String[] columnName, String[][] dataList, HttpServletResponse response) throws Exception {
		try{
			Workbook workbook = createWorkbook();
			createSheet(workbook,sheetName,titleName,columnName,dataList);
			export(workbook,fileName,response);
		}catch(Exception e){
//			logger.error(e);
			System.out.println(e);
			throw e ;
		}
	}
	
	public static void export(String sheetName, String fileName, int columnNumber,
			String[] columnName, String[][] dataList, HttpServletResponse response) throws Exception {
		try{
			Workbook workbook = createWorkbook();
			createSheet(workbook,sheetName,columnName,dataList);
			export(workbook,fileName,response);
		}catch(Exception e){
//			logger.error(e);
			System.out.println(e);
			throw e ;
		}
	}
	
	public static SXSSFWorkbook createWorkbook(){
		return new SXSSFWorkbook();// 创建一个workbook
	}
	
	public static Sheet createSheet(Workbook workbook,String sheetName, String titleName, /*int columnNumber,*/
			String[] columnName, String[][] dataList) throws Exception {
		if (dataList != null) {
//			logger.debug("excel文件中sheet页:{}的条目个数:{}",sheetName, dataList.length);
			System.out.println("excel文件中sheet页:{}"+sheetName+"条目个数:{}"+ dataList.length);
		}

		// 第二步,添加一个sheet(报价表单)
		Sheet sheet = workbook.createSheet(sheetName);
		

		// 设置表格默认宽度
		sheet.setDefaultColumnWidth(25);
		sheet.setDefaultRowHeightInPoints(24);

		// 创建第0行,也就是标题(报价单)
		Row row0 = sheet.createRow(0);
		row0.setHeightInPoints(50);

		// 第三步,创建标题的单元格样式style以及自体Font

		// 生成一个表格标题样式
		CellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);
		// 生成一个字体
		Font font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		// 把字体应用到当前样式
		style.setFont(font);

		Cell cell1 = row0.createCell(0);// 创建报价单标题第一列

		// 合并报价单列标题
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
		cell1.setCellValue(titleName);
		cell1.setCellStyle(style);

		// 创建报价单的第一行 表头
		Row row1 = sheet.createRow(1);
		row1.setHeightInPoints(37);

		// 第四步 创建表头单元格样式及表头字体样式
		CellStyle style2 = workbook.createCellStyle();
		style2.setWrapText(true);
		style2.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style2.setBorderBottom(BorderStyle.THIN);
		style2.setBorderLeft(BorderStyle.THIN);
		style2.setBorderRight(BorderStyle.THIN);
		style2.setBorderTop(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);
		// 生成另一个字体
		Font font2 = workbook.createFont();
		font2.setFontName("黑体");
		font2.setFontHeightInPoints((short) 12);
		font2.setBold(true);
		// 把字体应用到当前样式
		style2.setFont(font);

		// 第四.一步,创建表头的列
		for (int i = 0; i < columnName.length; i++) {
			Cell cell = row1.createCell(i);
			cell.setCellValue(columnName[i]);
			cell.setCellStyle(style);
		}

		// 创建单元格,并设置值

		// 为数据内容设置新单元格样式3 自动换行 上下居中 左右居中

		CellStyle style3 = workbook.createCellStyle();
		style3.setWrapText(true);// 设置自动换行
		style3.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style3.setBorderBottom(BorderStyle.THIN);
		style3.setBorderLeft(BorderStyle.THIN);
		style3.setBorderRight(BorderStyle.THIN);
		style3.setBorderTop(BorderStyle.THIN);
		style3.setAlignment(HorizontalAlignment.CENTER);
		style3.setVerticalAlignment(VerticalAlignment.CENTER);

		Cell datacell = null;

		// row1 = sheet.createRow(2);//创建报价单的属性字段行
		// for (int i = 0; i < columnName.length; i++) {
		// datacell= row1.createCell(i);
		// datacell.setCellValue(columnName[i]);
		// datacell.setCellStyle(style3);
		// }
		
		// 写入报价单的数据
		for (int i = 0; i < dataList.length; i++) {
			Row row = sheet.createRow(i + 2);
			for (int j = 0; j < columnName.length; j++) {
				datacell = row.createCell(j);
				datacell.setCellValue(dataList[i][j]);
				datacell.setCellStyle(style3);
			}
		}
		return sheet ;
	}
	public static Sheet createSheet(Workbook workbook,String sheetName,/*int columnNumber,*/
			String[] columnName, String[][] dataList) throws Exception {
		if (dataList != null) {
//			logger.debug("excel文件中sheet页:{}的条目个数:{}",sheetName, dataList.length);
			System.out.println("excel文件中sheet页:"+sheetName+"的条目个数:{}"+ dataList.length);
		}
		
		// 第二步,添加一个sheet(报价表单)
		Sheet sheet = workbook.createSheet(sheetName);
		
		
		// 设置表格默认宽度
		sheet.setDefaultColumnWidth(25);
		sheet.setDefaultRowHeightInPoints(24);
		
		// 创建第0行,也就是标题(报价单)
		Row row0 = sheet.createRow(0);
		row0.setHeightInPoints(50);
		
		// 第三步,创建标题的单元格样式style以及自体Font
		
		// 生成一个表格标题样式
		CellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);
		// 生成一个字体
		Font font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		// 把字体应用到当前样式
		style.setFont(font);
		
//		Cell cell1 = row0.createCell(0);// 创建报价单标题第一列
		
		// 合并报价单列标题
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
//		cell1.setCellValue(titleName);
//		cell1.setCellStyle(style);
		
		// 创建报价单的第一行 表头
		Row row1 = sheet.createRow(0);
		row1.setHeightInPoints(37);
		
		// 第四步 创建表头单元格样式及表头字体样式
		CellStyle style2 = workbook.createCellStyle();
		style2.setWrapText(true);
		style2.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style2.setBorderBottom(BorderStyle.THIN);
		style2.setBorderLeft(BorderStyle.THIN);
		style2.setBorderRight(BorderStyle.THIN);
		style2.setBorderTop(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);
		// 生成另一个字体
		Font font2 = workbook.createFont();
		font2.setFontName("黑体");
		font2.setFontHeightInPoints((short) 12);
		font2.setBold(true);
		// 把字体应用到当前样式
		style2.setFont(font);
		
		// 第四.一步,创建表头的列
		for (int i = 0; i < columnName.length; i++) {
			Cell cell = row1.createCell(i);
			cell.setCellValue(columnName[i]);
			cell.setCellStyle(style);
		}
		
		// 创建单元格,并设置值
		
		// 为数据内容设置新单元格样式3 自动换行 上下居中 左右居中
		
		CellStyle style3 = workbook.createCellStyle();
		style3.setWrapText(true);// 设置自动换行
		style3.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style3.setBorderBottom(BorderStyle.THIN);
		style3.setBorderLeft(BorderStyle.THIN);
		style3.setBorderRight(BorderStyle.THIN);
		style3.setBorderTop(BorderStyle.THIN);
		style3.setAlignment(HorizontalAlignment.CENTER);
		style3.setVerticalAlignment(VerticalAlignment.CENTER);
		
		Cell datacell = null;
		
		// row1 = sheet.createRow(2);//创建报价单的属性字段行
		// for (int i = 0; i < columnName.length; i++) {
		// datacell= row1.createCell(i);
		// datacell.setCellValue(columnName[i]);
		// datacell.setCellStyle(style3);
		// }
		
		// 写入报价单的数据
		for (int i = 0; i < dataList.length; i++) {
			Row row = sheet.createRow(i + 1);
			for (int j = 0; j < columnName.length; j++) {
				datacell = row.createCell(j);
				datacell.setCellValue(dataList[i][j]);
				datacell.setCellStyle(style3);
			}
		}
		return sheet ;
	}
	

	public static void export(Workbook workbook,String fileName,  HttpServletResponse response) throws Exception {

		// 第六步 将文件存到浏览器设置的下载位置
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		fileName = fileName  + ".xlsx";
		
//		fileName = fileName + ".xlsx";
//		fileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
//	    response.setContentType("application/ms-excel;charset=UTF-8");
		response.setContentType("application/x-download");
		response.setHeader("content-Disposition",
				"attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));

		// response.setHeader("Content-disposition", "attachment;filename=" +
		// fileName);
//		FileOutputStream out = new FileOutputStream(new File("D:/aaa") + "/" + fileName);
		OutputStream out = response.getOutputStream();
		try {
			workbook.write(out);
			String str = "导出" + fileName + "成功!";
//			logger.debug("{}", str);
			System.out.println(str);

		} catch (Exception e) {
			e.printStackTrace();
			String str1 = "导出" + fileName + "失败!";
//			logger.debug("{}", str1);
			System.out.println(str1);
		} finally {
			out.close();
			workbook.close();
		}
	}
	
	public  static void exportToLocal(Workbook workbook,String fileName) {
		 // 第六步，将文件存到指定位置  
        try {  
        	fileName = fileName + ".xlsx";
            FileOutputStream fout = new FileOutputStream("D://"+fileName);  
            workbook.write(fout);  
            String str = "导出" + fileName + " 文件成功！";  
            System.out.println(str);  
            fout.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
            String str1 = "导出" + fileName + " 文件失败！";  
            System.out.println(str1);  
        } 
	}
	
	/**
	 * @Description:根据模板文件位置创建新的workbook
	 * author:盛垒
	 * @date:2018年12月18日 下午3:02:09
	 * @param srcFile 模板文件地址
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException 
	 */
	public static XSSFWorkbook createXSSFWorkbook(String srcFile) throws IOException, InvalidFormatException{
		FileInputStream fileInputStream = new FileInputStream(new File(srcFile)); 
		 XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
		 fileInputStream.close();
		 return xssfWorkbook;
	}
	/**
	 * @Description:
	 * author:盛垒
	 * @date:2018年12月19日 下午3:27:30
	 * @param workbook XSSF excel2007之后的版本
	 * @param fileName 导出后的文件名
	 * @param response 
	 * @param fileInputStream 文件输入流
	 * @throws Exception 
	 */
	public static void exportXSSFWorkbook(XSSFWorkbook workbook,String fileName,  HttpServletResponse response) throws Exception {

		// 第六步 将文件存到浏览器设置的下载位置
		fileName = fileName  + ".xlsx";
		response.setContentType("application/x-download");
		response.setHeader("content-Disposition",
				"attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
		OutputStream out = response.getOutputStream();
		try {
			workbook.write(out);
			String str = "导出" + fileName + "成功!";
//			logger.debug("{}", str);
			System.out.println(str);

		} catch (Exception e) {
			e.printStackTrace();
			String str1 = "导出" + fileName + "失败!";
//			logger.debug("{}", str1);
			System.out.println(str1);
		} finally {
			out.close();
			workbook.close();
		}
	}
	
	/**
	 * @Description:
	 * author:盛垒
	 * @date:2018年12月18日 下午3:40:59
	 * @param xssfWorkbook
	 * @param styleType enumType 1:保留两位小数，2:保留四位小数
	 * @return
	 */
	public static XSSFCellStyle FormatDecimalCellStyle(XSSFWorkbook xssfWorkbook,int styleType) {
		XSSFCellStyle keepTwoDecimalsCellStyle = xssfWorkbook.createCellStyle();
		XSSFDataFormat createDataFormat = xssfWorkbook.createDataFormat();
		if(2==styleType){
		keepTwoDecimalsCellStyle.setDataFormat(createDataFormat.getFormat("0.00"));
		}else if(4==styleType){
		keepTwoDecimalsCellStyle.setDataFormat(createDataFormat.getFormat("0.0000"));	
		}
		return keepTwoDecimalsCellStyle;
	}
	
	/**
	 * @Description: 设置下拉框
	 * author:盛垒
	 * @date:2018年12月17日 下午3:47:24
	 * @param sheetAt 
	 * @param list	下拉数据
	 * @param firstRow 第一行
	 * @param lastRow	最后一行
	 * @param firstCol	第一列
	 * @param lastCol	最后一列
	 */
	public static void setDataValidation(SXSSFSheet sheetAt ,String[] list,Integer firstRow,Integer lastRow,Integer firstCol,Integer lastCol){
		DataValidationHelper dataValidationHelper = sheetAt.getDataValidationHelper();
		//设置列的范围:开始行，结束行，开始列，结束列
		CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(firstRow,lastRow,firstCol,lastCol);
		//如果带双引号超过30个，打开excel的时候就会提示错误，而且下来框不会生效。
		//如果不带双引号就没问题
		//设置下拉数据
		DataValidationConstraint constraint = dataValidationHelper.createExplicitListConstraint(list);
		DataValidation dataValidation = dataValidationHelper.createValidation(constraint, cellRangeAddressList);
		dataValidation.createErrorBox("本系统--提醒您", "数据不规范,请选择表格列表中的数据!");
		//处理Excel兼容性问题
		if(dataValidation instanceof XSSFDataValidation){
			dataValidation.setSuppressDropDownArrow(true);
			dataValidation.setShowErrorBox(true);
		}else{
			dataValidation.setSuppressDropDownArrow(false);
		}
		sheetAt.addValidationData(dataValidation);
	}
}
	
