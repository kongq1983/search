package com.kq.search.elasticsearch;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.kq.search.ConstantTest;

public class PutProductTest extends BaseTest{

	static RestClient restClient;

	@BeforeClass
	public static void init() {

		RestClientBuilder builder = RestClient.builder(ConstantTest.ELASTIC_SEARCH_SERVERS);

		builder.setMaxRetryTimeoutMillis(10000);

		restClient = builder.build();

	}
	
	@Test
	public void exexute() throws IOException {
		
		JSONObject json = new JSONObject();
		json.put("id", 1);
		json.put("name", "小王");
		json.put("account", "wang");
		json.put("address", "海创园998号");
		
		HttpEntity entity = new StringEntity(json.toString(),ContentType.APPLICATION_JSON);
		
		Map<String,String> params = this.getParams();
		
		Response response = restClient.performRequest(ConstantTest.PUT, "/student/_doc/1", params,entity);
		
		this.printResponse(response);
		
	}
	

	@AfterClass
	public static void destory() {
		try {
			restClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
