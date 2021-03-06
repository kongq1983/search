package com.kq.search.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class ReadJsonUtil {

	/**
	 * mapping
	 * @param mappingFileName
	 * @return
	 * @throws IOException
	 */
	public static String getMappingContent(String mappingFileName) throws IOException {
		
		String content = null;
		try (InputStream in = ReadJsonUtil.class.getResourceAsStream("/mapping/" + mappingFileName);) {
//			System.out.println(in);
			content = IOUtils.toString(in, "utf-8");
		}
		
		return content;
	}
	
	/**
	 * data
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String getDataContent(String fileName) throws IOException {

		String content = null;
		try (InputStream in = ReadJsonUtil.class.getResourceAsStream("/data/" + fileName);) {
//			System.out.println(in);
			content = IOUtils.toString(in, "utf-8");
		}

		return content;
	}
	
	
	public static void main(String[] args) throws IOException {
		System.out.println(getMappingContent("product_mappng.json"));
	}

}
