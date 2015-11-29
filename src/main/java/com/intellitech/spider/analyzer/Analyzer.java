package com.intellitech.spider.analyzer;

import java.util.List;

public interface Analyzer {
	public String analyze(String text);
	public List<String> analyzeToList(String text);
}
