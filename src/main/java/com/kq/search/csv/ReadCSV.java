package com.kq.search.csv;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.csvreader.CsvReader;

public class ReadCSV {

	public static void main(String[] args) throws IOException {

		// String filePath = "C:\\Users\\happy\\Desktop\\Domo.csv";

		int index  =0;
		try (InputStream in = ReadCSV.class.getResourceAsStream("/product.csv")) {
			CsvReader reader = new CsvReader(in, ',', Charset.forName("GBK"));
			reader.readHeaders(); // 跳过表头, 如果不需要表头的话，不要写这句。
			while (reader.readRecord()) {
				// 一下获取的行号是从0开始
				System.out.println("当前行号 " + reader.getCurrentRecord());
				// 读取行内容记录
				System.out.println(reader.getRawRecord());
				System.out.println("==========");
				// 按列名读取这条记录的值
				System.out.println(reader.get("id".toUpperCase()));
				System.out.println(reader.get("name".toUpperCase()));
				System.out.println(reader.get("categoryId".toUpperCase()));
				System.out.println(reader.get("code".toUpperCase()));
				System.out.println(reader.get("price".toUpperCase()));
				System.out.println(reader.get("categoryName".toUpperCase()));
				System.out.println(reader.get("orgId".toUpperCase()));
				System.out.println(reader.get("orgName".toUpperCase()));
				System.out.println(reader.get("tenantId".toUpperCase()));
				System.out.println(reader.get("tenantName".toUpperCase()));
				System.out.println(reader.get("createUserId".toUpperCase()));
				System.out.println(reader.get("createUserName".toUpperCase()));
				System.out.println(reader.get("createTime".toUpperCase()));
				System.out.println("--------");
				index++;
				if(index>3)break;
			}
			
		}

	}

}
