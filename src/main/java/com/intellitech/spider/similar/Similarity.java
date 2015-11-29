package com.intellitech.spider.similar;

import java.util.List;


public interface Similarity {
	public float similar(String source,String target);
	public float maxSimilarScore(String text);
}
