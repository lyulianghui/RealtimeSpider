package com.intellitech.spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.intellitech.spider.dao.PendingLinkMapper;
import com.intellitech.spider.model.Link;
import com.intellitech.spider.model.LinkExample;
import org.springframework.beans.factory.annotation.Autowired;

import com.intellitech.spider.analyzer.TextAnalyzer;
import com.intellitech.spider.dao.LinkMapper;
import com.intellitech.spider.dao.RootPageMapper;
import com.intellitech.spider.filter.Filter;
import com.intellitech.spider.model.RootPage;
import com.intellitech.spider.model.RootPageExample;
import com.intellitech.spider.similar.SimilarityCounter;
import org.springframework.stereotype.Component;

@Component
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

	public RootPageMapper getRootPageMapper() {
		return rootPageMapper;
	}

	public void setRootPageMapper(RootPageMapper rootPageMapper) {
		this.rootPageMapper = rootPageMapper;
	}

	private List<RootPage>rootPages;

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public TextAnalyzer getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(TextAnalyzer analyzer) {
		this.analyzer = analyzer;
	}

	public SimilarityCounter getSimilarityCounter() {
		return similarityCounter;
	}

	public void setSimilarityCounter(SimilarityCounter similarityCounter) {
		this.similarityCounter = similarityCounter;
	}

	public List<RootPage> getRootPages() {
		return rootPages;
	}

	public void setRootPages(List<RootPage> rootPages) {
		this.rootPages = rootPages;
	}

	public LinkMapper getLinkMapper() {
		return linkMapper;
	}

	public void setLinkMapper(LinkMapper linkMapper) {
		this.linkMapper = linkMapper;
	}

	public PendingLinkMapper getPendingLinkMapper() {
		return pendingLinkMapper;
	}

	public void setPendingLinkMapper(PendingLinkMapper pendingLinkMapper) {
		this.pendingLinkMapper = pendingLinkMapper;
	}

	@Autowired

	private PendingLinkMapper pendingLinkMapper;

	private List<Link>existLinks;

	public synchronized List<Link> getExistLinks()
	{
		if (existLinks == null)
		{
			existLinks = linkMapper.selectByExample(new LinkExample());
		}
		return existLinks;
	}

	List<Spider>spiders = new ArrayList<Spider>();
	@Autowired
	private LinkMapper linkMapper;
	
    public static void main(String[] args) {
	// write your code here
       System.out.println((int)Math.ceil(2/4));
        System.out.println(111);
    }
    public void execute(){  
        //需要做的事情
		try {
			System.out.println("start..." + new Date());
			int j = 0;
			rootPages = rootPageMapper.selectByExample(new RootPageExample());
			int pagesPerSpider = (int) Math.ceil(rootPages.size() / (float)SPIDER_COUNT);
			System.out.println(spiders.size()+"-------start..." + new Date());
			for (Spider spider : spiders) {
				spider.stop = true;
			}
			spiders.clear();
			for (int i = 0; i < rootPages.size(); ) {
				List<RootPage> pages = rootPages.subList(i, Math.min(i + pagesPerSpider, rootPages.size()));
				i += pagesPerSpider;
				Spider spider = new Spider(pages, filter, analyzer, similarityCounter, linkMapper, rootPageMapper);
				spiders.add(spider);
				spider.setName("spider"+j++);
				spider.start();
			}
		}catch (Exception e)
		{
			System.out.println(e);
		}
    	
    } 
}
