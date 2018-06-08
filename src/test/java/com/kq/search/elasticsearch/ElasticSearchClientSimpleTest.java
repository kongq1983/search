package com.kq.search.elasticsearch;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kq.search.ConstantTest;

public class ElasticSearchClientSimpleTest {
	
	
	static RestClient restClient;
	
	@BeforeClass
	public static void init() {
		
		RestClientBuilder builder = RestClient.builder(
				ConstantTest.ELASTIC_SEARCH_SERVERS);
		
		builder.setMaxRetryTimeoutMillis(10000);
		
		restClient = builder.build();
		
		
		
		        
	}
	
	@Test
	public void execute() throws IOException {
		
//		Response response = restClient.performRequest("GET", "/account");
		Response response = restClient.performRequest("GET", "_cat/segments");
		
		System.out.println(response);
		System.out.println(response.getEntity());
		
		String content = IOUtils.toString(response.getEntity().getContent(),ConstantTest.UTF8);
		System.out.println(content);
		
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
