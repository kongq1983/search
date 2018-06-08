package com.kq.search.elasticsearch;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kq.search.ConstantTest;

public class DeleteProductTest extends BaseTest{
	
	static RestClient restClient;

	@BeforeClass
	public static void init() {

		RestClientBuilder builder = RestClient.builder(ConstantTest.ELASTIC_SEARCH_SERVERS);

		builder.setMaxRetryTimeoutMillis(10000);

		restClient = builder.build();

	}
	
	
	/**
	 * 根据id删除
	 * @throws IOException
	 */
	@Test
	public void exexute() throws IOException {
		
		Map<String,String> params = this.getParams();
		
		Response response = restClient.performRequest(ConstantTest.DELETE, "/student/_doc/1", params);
		
		this.printResponse(response);
		
	}
	
	
	/**
	 * 根据条件删除
	 * @throws IOException
	 */
	@Test
	public void exexuteByQuery() throws IOException {
		
		Map<String,String> params = this.getParams();
		
		HttpEntity entity = this.getMatchQueryHttpEntity("name", "张");
		
		Response response = restClient.performRequest(ConstantTest.POST, "/student/_doc/_delete_by_query",params, entity);
		
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
