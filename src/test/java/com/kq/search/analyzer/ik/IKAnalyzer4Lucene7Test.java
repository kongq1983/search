package com.kq.search.analyzer.ik;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;


public class IKAnalyzer4Lucene7Test {
	
	
	private void doToken(final TokenStream ts) throws IOException {
		ts.reset();
		CharTermAttribute cta = ts.getAttribute(CharTermAttribute.class);
		while (ts.incrementToken()) {
			System.out.print(cta.toString() + "|");
		}
		System.out.println();
		ts.end();
		ts.close();
	}

	@Test
	public void test() throws IOException {
		String etext = "Analysis is one of the main causes of slow indexing. Simply put, ";
		// String chineseText = "张三说的确实在理。";
		String chineseText = "厉害了我的国一经播出，受到各方好评，强烈激发了国人的爱国之情、自豪感！";
		// IKAnalyzer 细粒度切分
		try (final Analyzer ik = new IKAnalyzer4Lucene7();) {
			final TokenStream ts = ik.tokenStream("content", etext);
			System.out.println("IKAnalyzer中文分词器 细粒度切分，英文分词效果：");
			doToken(ts);
			final TokenStream ts1 = ik.tokenStream("content", chineseText);
			System.out.println("IKAnalyzer中文分词器 细粒度切分，中文分词效果：");
			doToken(ts1);
		}
		
		System.out.println();

		// IKAnalyzer 智能切分
		try (final Analyzer ik = new IKAnalyzer4Lucene7(true);) {
			final TokenStream ts = ik.tokenStream("content", etext);
			System.out.println("IKAnalyzer中文分词器 智能切分，英文分词效果：");
			doToken(ts);
			final TokenStream ts1 = ik.tokenStream("content", chineseText);
			System.out.println("IKAnalyzer中文分词器 智能切分，中文分词效果：");
			doToken(ts1);
		}
	}

}
