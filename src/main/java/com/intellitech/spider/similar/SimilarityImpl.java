package com.intellitech.spider.similar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.intellitech.spider.dao.LinkMapper;
import com.intellitech.spider.model.Link;

public class SimilarityImpl implements Similarity{
	
	@Autowired
	private LinkMapper linkMapper;
	private List<Link>existLinks;
	
	public float similar(String source, String target) {
		// TODO Auto-generated method stub
		return 0;
	}

	public float maxSimilarScore(String text) {
		// TODO Auto-generated method stub
		return 0;
	}

}
