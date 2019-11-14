package com.it.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.ss.usermodel.Workbook;

public class ImportExcelTest {

	public static void main(String[] args) throws Exception{
		/**
		 * 测试导入excel
		 */
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		InputStream in=new FileInputStream("C:\\Users\\james\\Desktop\\节假日记录.xlsx");//excel文件
		Workbook book = ImportExcelUtil.getWorkBook(in);
//		List<List<Object>> list = ImportExcelUtil.getBankListByExcel(book);
		List<List<String>> list = ImportExcelUtil.getBankStringListByExcel(book);
		in.close();
		if(list!=null&&list.size()>0) {
			for(int i=0;i<list.size();i++) {
//				List<Object> lo=list.get(i);
				List<String> lo=list.get(i);
				Object[] s = lo.toArray();//代表着Excel每一行的单元格的内容,s[0]代表第一个单元格的类容
//				System.out.println(s.length);
				for(int k=0;k<lo.size();k++){
					if(k==1){
						Calendar calendar = new GregorianCalendar(1900,0,-1);
						Date start = calendar.getTime();
						Date d = DateUtils.addDays(start,Integer.valueOf( lo.get(k)));
						String formatDate = DateFormatUtils.format(d, "yyyy-MM-dd");
						System.out.print(formatDate+" ");
					}else {
						System.out.print(lo.get(k)+" ");
					}
				}
				System.out.println();
			}
		}

	}

}
