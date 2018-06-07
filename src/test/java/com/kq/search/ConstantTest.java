package com.kq.search;

import org.apache.http.HttpHost;

public class ConstantTest {
	
	
	public static final String UTF8="UTF-8";

	/** 索引库路径 */
	public static final String INDEX_PATH = "f:/luceneindex/indextest";
	
	public static final HttpHost[] ELASTIC_SEARCH_SERVERS = {new HttpHost("192.168.6.111",9200,"http")};
	
}
