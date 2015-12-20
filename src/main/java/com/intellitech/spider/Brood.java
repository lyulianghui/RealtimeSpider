package com.intellitech.spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import com.intellitech.spider.common.LinkUtils;
import com.intellitech.spider.dao.PendingLinkMapper;
import com.intellitech.spider.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.intellitech.spider.analyzer.TextAnalyzer;
import com.intellitech.spider.dao.LinkMapper;
import com.intellitech.spider.dao.RootPageMapper;
import com.intellitech.spider.filter.Filter;
import com.intellitech.spider.similar.SimilarityCounter;
import org.springframework.stereotype.Component;

@Component
public class Brood {
	private static int SPIDER_COUNT=4;
	static int threadNum=0;
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

	public List<Link> getExistLinks()
	{
		return existLinks;
	}
	public void refreshExistLinks()
	{
		existLinks = linkMapper.selectByExample(new LinkExample());
	}
	CountDownLatch countDownLatch;
	List<Spider>spiders = new ArrayList<Spider>();
	@Autowired
	private LinkMapper linkMapper;

	private AtomicInteger runningBroods = new AtomicInteger();

    public static void main(String[] args) {
	// write your code here
       System.out.println((int)Math.ceil(2/4));
        System.out.println(111);
    }
    public void execute(){  
        //需要做的事情
		try {
			/*
			最多2个调度
			 */
			if (runningBroods.getAndIncrement()>=2)
			{
				return;
			}
			//System.out.println("brood start....");
			rootPages = rootPageMapper.selectByExample(new RootPageExample());
			existLinks = linkMapper.selectByExample(new LinkExample());
			List<PendingLink> pendingLinks = pendingLinkMapper.selectByExample(new PendingLinkExample());

			int pagesPerSpider = (int) Math.ceil(rootPages.size() / (float)SPIDER_COUNT);

			for (Spider spider : spiders) {
				spider.stop = true;
			}
			spiders.clear();


			if (countDownLatch!=null)
				countDownLatch.await();

			existLinks.addAll(LinkUtils.translateToLinks(pendingLinks,null));

			int spiderCount = 0;
			for (int i = 0; i < rootPages.size(); ) {
				List<RootPage> pages = rootPages.subList(i, Math.min(i + pagesPerSpider, rootPages.size()));
				i += pagesPerSpider;
				Spider spider = new Spider(this,pages);
				spiders.add(spider);
				spider.setName("spider"+threadNum++);
				//spider.start();
				spiderCount++;
			}
			countDownLatch = new CountDownLatch(spiderCount);
			for (Spider spider:spiders)
			{
				spider.setCountDownLatch(countDownLatch);
				spider.start();
			}
			//countDownLatch.await();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			runningBroods.decrementAndGet();
		}

    }

	
	public void setExistLinks(List<Link> existLinks) {
		this.existLinks = existLinks;
	}
}

