package com.kq.search.elasticsearch.index;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kq.search.ConstantTest;
import com.kq.search.elasticsearch.BaseTest;
import com.kq.search.util.ReadJsonUtil;

public class ProductIndexTest extends BaseTest{
	
	static RestClient restClient;

	@BeforeClass
	public static void init() {

		RestClientBuilder builder = RestClient.builder(ConstantTest.ELASTIC_SEARCH_SERVERS);

		builder.setMaxRetryTimeoutMillis(10000);

		restClient = builder.build();

	}
	
	
	/**
	 * 批量导入index
	 * @throws IOException
	 */
	@Test
	public void batchIndex() throws IOException {
		
		String json = ReadJsonUtil.getDataContent("product.json");
		
		JSONArray array = JSONArray.parseArray(json);
//		
//		System.out.println(array.size());
//		
		StringBuilder jsonBulk = new StringBuilder();
		for(int i=0;i<array.size();i++) {
			JSONObject obj = array.getJSONObject(i);
			
			jsonBulk.append(obj.toJSONString());
			if(i!=(array.size()-1)) {
				jsonBulk.append("\n");
			}
			
		}
		
		System.out.println(jsonBulk);
		
//		System.out.println(array.getString(0));
		
		HttpEntity entity = new StringEntity(jsonBulk.toString());
//		HttpEntity entity = new StringEntity(json,ContentType.APPLICATION_JSON);
//		HttpEntity entity = new StringEntity(array.getString(0),ContentType.APPLICATION_JSON);
		
		Response response = restClient.performRequest(ConstantTest.POST, "/product1/_doc/_bulk",this.getParams(), entity);
//		Response response = restClient.performRequest(ConstantTest.POST, "/product1/_doc",this.getParams(), entity);
		
		this.printResponse(response);
		
		
		
		
	}
	
	
	
	@AfterClass
	public static void destory() {
		try {
			restClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
