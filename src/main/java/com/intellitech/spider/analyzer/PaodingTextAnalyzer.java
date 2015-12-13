package com.intellitech.spider.analyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.beans.factory.annotation.Autowired;

public class PaodingTextAnalyzer implements TextAnalyzer{

	@Autowired
	Analyzer analyzer;
	
	@Override
	public String analyze(String text) {
		// TODO Auto-generated method stub
		StringBuilder terms = new StringBuilder();
		try {
			 TokenStream tokens = analyzer.tokenStream("", text);
			 tokens.reset();
			 CharTermAttribute offAtt  = (CharTermAttribute)tokens.addAttribute(CharTermAttribute.class);
			 String spliter  = null;
			 while(tokens.incrementToken())
			 {
				 if(spliter!=null)
					 terms.append(spliter);
				 else
					 spliter = ",";
				 terms.append(offAtt.toString());
				 //System.out.println(offAtt.toString());
			 }
			 tokens.end();
			 tokens.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return terms.toString();
	}

	@Override
	public List<String> analyzeToList(String text) {
		// TODO Auto-generated method stub
		List<String>terms = new ArrayList<String>();
		
		 try {
			 TokenStream tokens = analyzer.tokenStream("", text);
			 tokens.reset();
			 CharTermAttribute offAtt  = (CharTermAttribute)tokens.getAttribute(CharTermAttribute.class);
			 while(tokens.incrementToken())
			 {
				 terms.add(offAtt.toString());
				 System.out.println(tokens.toString());
			 }
			 tokens.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return terms;
	}
	
	public static void main(String[] args) {
		
	}

}

