package com.kq.search.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static final String DateFormat = "yyyy-MM-dd HH:mm:ss";
	
	public static Date stringToDate(String date) {
		
		if(date==null||date.trim().equals("")) {
			return null;
		}
		
		SimpleDateFormat f = new SimpleDateFormat(DateFormat);
		try {
			return f.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
