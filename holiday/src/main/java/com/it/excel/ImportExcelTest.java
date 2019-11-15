package com.it.excel;

import com.it.entity.Holiday;
import com.it.jdbc.JDBCTemplate;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ImportExcelTest {

	public static void main(String[] args) throws Exception {
		String filePath = "practise\\src\\main\\resources\\节假日记录.xlsx";
		readExcel(filePath);
	}

	public static String readExcel(String filePath)throws Exception{
		/**
		 * 测试导入excel
		 */
		if(StringUtils.isBlank(filePath)){
			System.out.println("文件路径不存在！");
			return null;
		}
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//		InputStream in = new FileInputStream("C:\\Users\\james\\Desktop\\节假日记录.xlsx");//excel文件
		InputStream in = new FileInputStream(filePath);//excel文件
		Workbook book = ImportExcelUtil.getWorkBook(in);
//		List<List<Object>> list = ImportExcelUtil.getBankListByExcel(book);
		List<List<String>> list = ImportExcelUtil.getBankStringListByExcel(book);
		in.close();
		/*if(list!=null&&list.size()>0) {
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
						String formatDate = DateFormatUtils.format(d, "yyyy/MM/dd");
						System.out.print(formatDate+" ");
					}else {
						System.out.print(lo.get(k)+" ");
					}
				}
				System.out.println();
			}
		}*/

		//将数据插入数据库
		List<Holiday> holidays = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				List<String> lo = list.get(i);
				Holiday holiday = new Holiday();

				holiday.setId(Integer.valueOf(lo.get(0)));

				Calendar calendar = new GregorianCalendar(1900, 0, 0);
				Date start = calendar.getTime();
				Date d = DateUtils.addDays(start, Integer.valueOf(lo.get(1)));
//				String formatDate = DateFormatUtils.format(d, "yyyy/MM/dd");

				holiday.setLocalDate(d);
				holiday.setRegion(lo.get(2));
				holiday.setState(lo.get(3));
				holidays.add(holiday);
			}
		}
		//准备插入数据库
		List<Object> params = new ArrayList<Object>();


		StringBuffer insertSql = new StringBuffer("INSERT INTO HOLIDAY (REGION,LOCAL_DATE,STATE) VALUES  ");
		int i=0;
		for(Holiday h:holidays){
			if(i==0){
				insertSql.append("( ?,?,? )");
			}else {
				insertSql.append(",( ?,?,? )");
			}
			params.add(h.getRegion());
			params.add(h.getLocalDate());
			params.add(h.getState());
			i++;
		}
		insertSql.append("  ON DUPLICATE KEY UPDATE REGION=VALUES(REGION),LOCAL_DATE=VALUES(LOCAL_DATE),STATE=VALUES(STATE) ");//如果记录已经存在，则覆盖

		int update = JDBCTemplate.getInstance().update(insertSql.toString(), params.toArray());
		System.out.println("读excel文件结束，插入行数："+update);
		return "sucess!";
	}


}
