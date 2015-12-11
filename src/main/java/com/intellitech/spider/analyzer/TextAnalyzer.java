package com.intellitech.spider.analyzer;

import java.util.List;

public interface TextAnalyzer {
	public String analyze(String text);
	public List<String> analyzeToList(String text);
}
