package com.intellitech.spider;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:/Users/llh/git/RealtimeSpider/src/main/resources/applicationContext.xml"})  
public class SpiderTest {
	
	@Autowired
	Spider spider;
	
	
@Test
public void testCrawl() throws ClientProtocolException, IOException
{
	Assert.assertNotNull(spider.crawl("http://www.sina.com.cn"));
}

@Test
public void testCrawlPage() throws ClientProtocolException, IOException
{
	Assert.assertTrue(spider.storeLinks(spider.parsePage("http://finance.sina.com.cn/stock/"))>0);
}
}
