package com.it.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ImportExcelUtil {
	private ImportExcelUtil() {
		
	}
	private final static String Excel2003 = ".xls";
	private final static String Excel2007 = ".xlsx";
   /**
    * 获取流的数据
    * @param book
    * @return
    * @throws Exception
    */
	public static List<List<Object>> getBankListByExcel(Workbook book) throws Exception {
		int sheetNum = book.getNumberOfSheets();  //workbook
		Sheet sheet = null;
		List<List<Object>> list = new ArrayList<>();
		for (int i = 0; i < sheetNum; i++) {
			sheet = book.getSheetAt(i);              //遍历sheet页
			Iterator<Row> iterator = sheet.iterator();//遍历行
			while (iterator.hasNext()) {
				Row row = iterator.next();
				int rownum = row.getRowNum();
				if (rownum == 0) {
					continue;
				}
				Iterator<Cell> cellIterator = row.cellIterator();//由每一行遍历每个单元格的内容，
				List<Object> listobject = new ArrayList<>();
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					cell.setCellType(CellType.STRING);//numberformat异常，不加
					listobject.add(cell.getStringCellValue());
				}
				list.add(listobject);
			}
		}
		return list;
	
	}	public static List<List<String>> getBankStringListByExcel(Workbook book) throws Exception {
		int sheetNum = book.getNumberOfSheets();  //workbook
		Sheet sheet = null;
		List<List<String>> list = new ArrayList<>();
		for (int i = 0; i < sheetNum; i++) {
			sheet = book.getSheetAt(i);              //遍历sheet页
			Iterator<Row> iterator = sheet.iterator();//遍历行
			while (iterator.hasNext()) {
				Row row = iterator.next();
				int rownum = row.getRowNum();
				if (rownum == 0) {
					continue;
				}
				Iterator<Cell> cellIterator = row.cellIterator();//由每一行遍历每个单元格的内容，
				List<String> listobject = new ArrayList<>();
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					cell.setCellType(CellType.STRING);//numberformat异常，不加
					listobject.add(cell.getStringCellValue());
				}
				list.add(listobject);
			}
		}
		return list;

	}
	/**
	 * 根据文件后缀找到适应的版本
	 * @param in
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static Workbook getWorkBook(InputStream in,String filename) throws Exception{
		Workbook wb=null;
		String filetype = filename.substring(filename.indexOf("."));
		if(Excel2003.equals(filetype)) {
			wb=new XSSFWorkbook(in);
		}else if(Excel2007.equals(filetype)){
			wb=new HSSFWorkbook(in);
		}else {
			throw new Exception();
		}
		return wb;
	}
	
	/**
	 * 自适应上传文件的版本
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static Workbook getWorkBook(InputStream in) throws Exception{
		Workbook wb=WorkbookFactory.create(in);
		return wb;
	}
	/**
	 * 对表中数值格式化
	 * @param cell
	 * @return
	 */
	public Object getCellValue(Cell cell) {
		return cell;
	}
}
