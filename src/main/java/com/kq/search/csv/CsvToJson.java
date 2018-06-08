package com.kq.search.csv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.csvreader.CsvReader;
import com.google.common.collect.Lists;
import com.kq.search.entity.Product;
import com.kq.search.util.DateUtil;
import com.kq.search.util.NumberUtil;

public class CsvToJson {

	public static void main(String[] args) throws IOException {

		// String filePath = "C:\\Users\\happy\\Desktop\\Domo.csv";

		List<Product> list = Lists.newArrayList();
		File directory = new File(""); // 参数为空
		String courseFile = directory.getCanonicalPath();

		try (InputStream in = ReadCSV.class.getResourceAsStream("/product.csv");
				OutputStream out = new FileOutputStream(new File(courseFile,"product.json"));) {
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

				Product p = new Product();
				p.setId(reader.get("id".toUpperCase()));
				p.setName(reader.get("name".toUpperCase()));
				p.setCategoryId(reader.get("categoryId".toUpperCase()));
				p.setCode(reader.get("code".toUpperCase()));
				p.setPrice(NumberUtil.stringToDouble(reader.get("price".toUpperCase())));
				p.setCategoryName(reader.get("categoryName".toUpperCase()));
				p.setOrgId(reader.get("orgId".toUpperCase()));
				p.setOrgName(reader.get("orgName".toUpperCase()));
				p.setTenantId(reader.get("tenantId".toUpperCase()));
				p.setTenantName(reader.get("tenantName".toUpperCase()));
				p.setCreateUserId(reader.get("createUserId".toUpperCase()));
				p.setCreateUserName(reader.get("createUserName".toUpperCase()));
				p.setCreateTime(DateUtil.stringToDate(reader.get("createTime".toUpperCase())));

				list.add(p);

				System.out.println("--------");
			}

			String json = JSON.toJSON(list).toString();

			System.out.println(json);

			IOUtils.write(json, out, Charset.forName("UTF-8"));

		}

	}

}
