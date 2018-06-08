package com.kq.search.elasticsearch.mapping;

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

import com.kq.search.ConstantTest;
import com.kq.search.elasticsearch.BaseTest;
import com.kq.search.util.ReadJsonUtil;

public class ProductMappingTest extends BaseTest{
	
	static RestClient restClient;

	@BeforeClass
	public static void init() {

		RestClientBuilder builder = RestClient.builder(ConstantTest.ELASTIC_SEARCH_SERVERS);

		builder.setMaxRetryTimeoutMillis(10000);

		restClient = builder.build();

	}
	
	/**
	 * 创建mapping
	 * @throws IOException
	 */
	@Test
	public void createMapping() throws IOException {
		
		Map<String,String> params = this.getParams();
		
		HttpEntity entity = new StringEntity(ReadJsonUtil.getMappingContent("product_mappng.json"),ContentType.APPLICATION_JSON);
		
		Response response = restClient.performRequest(ConstantTest.PUT, "/product1",params, entity);
		
		this.printResponse(response);
		
	}
	
	/**
	 * 先创建mapping
	 * 增加一个价格字段
	 * @throws IOException
	 */
	@Test
	public void addPriceFieldMapping() throws IOException {
		
		Map<String,String> params = this.getParams();
		
		HttpEntity entity = new StringEntity(ReadJsonUtil.getMappingContent("product_mapping_add_price.json"),ContentType.APPLICATION_JSON);
		
		Response response = restClient.performRequest(ConstantTest.PUT, "/product1/_mapping/_doc",params, entity);
		
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
