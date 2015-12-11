package com.intellitech.spider;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.intellitech.spider.analyzer.TextAnalyzer;
import com.intellitech.spider.dao.LinkMapper;
import com.intellitech.spider.dao.RootPageMapper;
import com.intellitech.spider.filter.Filter;
import com.intellitech.spider.model.RootPage;
import com.intellitech.spider.model.RootPageExample;
import com.intellitech.spider.similar.SimilarityCounter;

public class Brood {
	private static int SPIDER_COUNT=4;
	
	@Autowired
	Filter filter;
	
	@Autowired
	private TextAnalyzer analyzer; 
	
	@Autowired
	private SimilarityCounter similarityCounter;
	@Autowired
	private RootPageMapper rootPageMapper;
	
	private List<RootPage>rootPages;
	
	@Autowired
	private LinkMapper linkMapper;
	
    public static void main(String[] args) {
	// write your code here
       System.out.println(Math.ceil(2));
        System.out.println(111);
    }
    public void execute(){  
        //需要做的事情  
    	System.out.println("start...");
    	rootPages = rootPageMapper.selectByExample(new RootPageExample());
    	int pagesPerSpider = (int)Math.ceil(rootPages.size()/SPIDER_COUNT);
    	
    	for(int i=0;i<rootPages.size();)
    	{
    		List<RootPage> pages = rootPages.subList(i, i+pagesPerSpider);
    		i +=pagesPerSpider;
    		Spider spider = new Spider(pages,filter, analyzer, similarityCounter, linkMapper, rootPageMapper);
    		spider.start();
    	}
    	
    } 
}
