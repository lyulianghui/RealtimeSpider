package com.intellitech.spider;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;

public class SpiderTest {
	Spider spider = new Spider();
	
	
@Test
public void testCrawl() throws ClientProtocolException, IOException
{
	Assert.assertNotNull(spider.crawl("http://www.sina.com.cn"));
	Assert.assertNull(spider.parsePage("http://finance.sina.com.cn/stock/"));
}
}
