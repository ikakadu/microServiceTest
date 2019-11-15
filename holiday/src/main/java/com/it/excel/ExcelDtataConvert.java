package com.it.excel;

import com.it.entity.Person;

import java.util.List;

public class ExcelDtataConvert {

	public static void testDataForExcel(String[][] arr,List<Person> list) {
		for (int i = 0; i < list.size(); i++) {
			Person p = list.get(i);
			arr[i][0] = String.valueOf(p.getId());
			arr[i][1] = p.getName();
		}
	}
}
