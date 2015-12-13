package com.intellitech.spider.similar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.intellitech.spider.dao.LinkMapper;
import com.intellitech.spider.model.Link;
import com.intellitech.spider.model.LinkExample;

public class SimilarityCounter {
	
	@Autowired
	private Similarity similarity;



	public float maxSimilarScore(List<Link>existLinks,String text) {
		// TODO Auto-generated method stub

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
