package com.intellitech.spider.similar;

import java.util.List;

import com.intellitech.spider.analyzer.TextAnalyzer;
import com.intellitech.spider.model.PendingLink;
import org.springframework.beans.factory.annotation.Autowired;

import com.intellitech.spider.dao.LinkMapper;
import com.intellitech.spider.model.Link;
import com.intellitech.spider.model.LinkExample;

public class SimilarityCounter {
	
	@Autowired
	private Similarity similarity;

	@Autowired
	private TextAnalyzer analyzer;

	public float maxSimilarScore(List<Link>existLinks,String url,String text) {
		// TODO Auto-generated method stub

		float maxScore = 0;
		for(Link link:existLinks)
		{
			if (url.equals(link.getUrl())||link.getText().equals(text))
			{
				return 1.0f;
			}
			String terms = analyzer.analyze(text);
			float currentScore = similarity.similar(link.getTerms(),terms);
			if(maxScore<currentScore)
			{
				maxScore = currentScore;
			}
		}
		return maxScore;
	}


}
