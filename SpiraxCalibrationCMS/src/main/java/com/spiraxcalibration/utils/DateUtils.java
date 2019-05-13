package com.spiraxcalibration.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	// yyyy-mm-dd date formate...
	private static SimpleDateFormat yyyymmddDateSdf =  new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat ddmmmyyyyDateSdf = new SimpleDateFormat("dd-MMM-yyyy");
	private static SimpleDateFormat trackDateTime = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static SimpleDateFormat ddmmyyyyDateSdf =  new SimpleDateFormat("dd-MM-yyyy");
	
	public static String gettrackDate(Date todayDate) {
		String convertedDate =  trackDateTime.format(todayDate);
		return convertedDate;
	}
	
	
	public static String convertToYYYYMMDD(Date todayDate) {
		String convertedDate =  yyyymmddDateSdf.format(todayDate);
		return convertedDate;
	}
	
	public static String convertToYYYYMMDDParseToDate(Date todayDate) {
		String convertedDate =  yyyymmddDateSdf.format(todayDate);
		return convertedDate;
	}
	
	//convert String dd-mm-yyyy date formate....
	public static String convertToDDMMYYYY(Date todayDate) {
		String convertedDate =  ddmmyyyyDateSdf.format(todayDate);
		return convertedDate;
	}

	//parse dd-mm-yyyy
	public static Date convertToDDMMYYYYParseToDate(String todayDate) throws ParseException {
		return ddmmyyyyDateSdf.parse(todayDate);
	}
	
	
	//convert string dd-mmm-yyyy
	public static String convertToDDMMMYYYY(Date todayDate) {
		String convertedDate =  ddmmmyyyyDateSdf.format(todayDate);
		return convertedDate;
	}
	
	//parse dd-mmm-yyyy
	public static Date convertToDDMMMYYYYParseToDate(String todayDate) throws ParseException {
		return ddmmmyyyyDateSdf.parse(todayDate);
	}
}
