package com.intellitech.spider.similar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.intellitech.spider.dao.LinkMapper;
import com.intellitech.spider.model.Link;
import com.intellitech.spider.model.LinkExample;

public class SimilarityCounter {
	
	@Autowired
	private Similarity similarity;
	@Autowired
	private LinkMapper linkMapper;
	private List<Link>existLinks;
	


	public float maxSimilarScore(String text) {
		// TODO Auto-generated method stub
		if(existLinks == null)
		{
			existLinks = linkMapper.selectByExample(new LinkExample());
		}
		float maxScore = 0;
		for(Link link:existLinks)
		{
			float currentScore = similarity.similar(link.getTerms(),text);
			if(maxScore<currentScore)
			{
				maxScore = currentScore;
			}
		}
		return maxScore;
	}

}
