package com.kq.search.util;

public class NumberUtil {

	public static Double stringToDouble(String value) {
		if (value == null || value.trim().equals("")) {
			return null;
		}

		try {
			return Double.valueOf(value);
		} catch (Exception e) {
			return null;
		}
	}

}
