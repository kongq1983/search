package com.kq.search.elasticsearch;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.elasticsearch.client.Response;

import com.kq.search.ConstantTest;

public class BaseTest {
	
	public void printResponse(Response response) throws IOException {
		String content = IOUtils.toString(response.getEntity().getContent(),ConstantTest.UTF8);
		System.out.println(content);
	}

	
	public Map<String,String> getParams(){
		Map<String,String> map = new HashMap<>();
		
		return map;
	}
	
//	POST twitter/_delete_by_query { "query": {  "match": { "message": "some message" } } }
	public String getMatchQueryCondition(String filed,String value) {
		StringBuilder json = new StringBuilder();
		json.append("{\"query\":");
		json.append("{\"match\": {");
		
		json.append("\"").append(filed).append("\"");
		json.append(":");
		json.append("\"");
		json.append(value);
		json.append("\"");
		
		json.append("} } }");
		
		return json.toString();
		
	}
	
	public HttpEntity getMatchQueryHttpEntity(String filed,String value) {
		String json = this.getMatchQueryCondition(filed, value);
		HttpEntity entity = new StringEntity(json,ContentType.APPLICATION_JSON);
		return entity;
	}
	
//	public static void main(String[] args) {
//		String json = new BaseTest().getMatchQueryCondition("name", "小");
//		System.out.println(json);
//	}
	
}
