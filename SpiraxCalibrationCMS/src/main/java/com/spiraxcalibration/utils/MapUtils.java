package com.spiraxcalibration.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapUtils {


	public static String getYearsFromMapByValue(String value){
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("0 Year", "0");
		map.put("1 Year", "364");
		map.put("2 Year", "730");
		map.put("3 Year", "1095");
		map.put("4 Year", "1460");
		map.put("5 Year", "1825");
		map.put("6 Year", "2190");
		map.put("7 Year", "2555");
		map.put("8 Year", "2920");
		map.put("9 Year", "3285");
		map.put("10 Year", "3650");
		map.put("11 Year", "4015");
		map.put("12 Year", "4380");
		
		return getKeyFromValue(map,value).toString();
	}

	public static String getMonthsFromMapByValue(String value){
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("0 month", "0");
		map.put("1 month", "30");
		map.put("2 month", "60");
		map.put("3 month", "90");
		map.put("4 month", "120");
		map.put("5 month", "150");
		map.put("6 month", "180");
		map.put("7 month", "210");
		map.put("8 month", "240");
		map.put("9 month", "270");
		map.put("10 month", "300");
		map.put("11 month", "330");
		map.put("12 month", "365");
		
		return getKeyFromValue(map,value).toString();
		
	}

	public static String getDaysFromMapByValue(String value){
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("0 day", "0");
		map.put("1 day", "1");
		map.put("2 day", "2");
		map.put("3 day", "3");
		map.put("4 day", "4");
		map.put("5 day", "5");
		map.put("6 day", "6");
		map.put("7 day", "7");
		map.put("8 day", "8");
		map.put("9 day", "9");
		map.put("10 day", "10");
		map.put("11 day", "11");
		map.put("12 day", "12");
		map.put("13 day", "13");
		map.put("14 day", "14");
		map.put("15 day", "15");
		map.put("16 day", "16");
		map.put("17 day", "17");
		map.put("18 day", "18");
		map.put("19 day", "19");
		map.put("20 day", "20");
		map.put("21 day", "21");
		map.put("22 day", "22");
		map.put("23 day", "23");
		map.put("24 day", "24");
		map.put("25 day", "25");
		map.put("26 day", "26");
		map.put("27 day", "27");
		map.put("28 day", "28");
		map.put("29 day", "29");
		map.put("30 day", "30");
		map.put("31 day", "31");
		
		return getKeyFromValue(map,value).toString();
	}

	public static Object getKeyFromValue(Map hm, Object value) {
		for (Object o : hm.keySet()) {
			if (hm.get(o).equals(value)) {
				return o;
			}
		}
		return null;
	}



}
