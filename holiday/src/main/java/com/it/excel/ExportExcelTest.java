package com.it.excel;

import com.it.entity.Person;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;


public class ExportExcelTest {
	public static final String[] colunm = {"ID","名字"};
	public static final int pageSize = 500000;
	public static void main(String[] args) throws Exception {
		List<Person> list = new ArrayList<Person>();
		 
		for (int i = 1; i <= 210; i++) {
			Person p =new Person();
			p.setId(i);
			p.setName("张三"+i+"");
			list.add(p);
		}
//		String[][] dataList = new String[list.size()][colunm.length];
		int sheetNum =list.size()%pageSize==0?list.size()/pageSize:list.size()/pageSize+1;
		
		Workbook workbook = ExcelExportUtil.createWorkbook();
		for (int i = 1; i <= sheetNum; i++) {
			int start=(i-1)*pageSize+1;
			int end = start +pageSize-1;
			if(end>list.size())
				end = list.size();
			String[][] dataList = new String[list.subList(start-1,end).size()][colunm.length];
			ExcelDtataConvert.testDataForExcel(dataList, list.subList(start-1,end ));
			
			ExcelExportUtil.createSheet(workbook, "测试一下sheet"+i, colunm, dataList);
		}
		
		ExcelExportUtil.exportToLocal(workbook, " testFile");
		
	}

}
