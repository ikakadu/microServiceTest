package com.it.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Testpoi {
    public static void main(String[] args) {
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String,String>> list = null;
        String cellData = null;
//        String filePath = "C:\\Users\\james\\Desktop\\节假日记录.xlsx";
        String filePath = "src\\main\\resources\\节假日记录.xlsx";
        String columns[] = {"id","localDate","region","state"};
        wb = readExcel(filePath);
        list = getMapList(wb, list, columns);
        //遍历解析出来的list
        for (Map<String,String> map : list) {
            for (Entry<String,String> entry : map.entrySet()) {
                if("localDate".equals(entry.getKey())){
                    Calendar calendar = new GregorianCalendar(1900,0,-1);
                    Date start = calendar.getTime();
                    Date d = DateUtils.addDays(start,Integer.valueOf(entry.getValue()));
                    String formatDate = DateFormatUtils.format(d, "yyyy/MM/dd");
                    System.out.print(entry.getKey()+":"+formatDate+",");
                }else{
                    System.out.print(entry.getKey()+":"+entry.getValue()+",");
                }
            }
            System.out.println();
        }

    }

    private static List<Map<String, String>> getMapList(Workbook wb, List<Map<String, String>> list, String[] columns) {
        Sheet sheet;
        Row row;
        String cellData;
        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<Map<String,String>>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                }else{
                    break;
                }
                list.add(map);
            }
        }
        return list;
    }

    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            CellType cellType = cell.getCellType();
            switch(cell.getCellType()){
                case NUMERIC:{
//                    cellValue = String.valueOf(cell.getNumericCellValue());
                    cellValue = new BigDecimal(cell.getNumericCellValue()).stripTrailingZeros().toPlainString();
                    break;
                }
                case FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }
}
