package com.intellitech.spider;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.intellitech.spider.analyzer.TextAnalyzer;
import com.intellitech.spider.common.Constants;
import com.intellitech.spider.dao.LinkMapper;
import com.intellitech.spider.dao.RootPageMapper;
import com.intellitech.spider.filter.Filter;
import com.intellitech.spider.model.Link;
import com.intellitech.spider.model.LinkExample;
import com.intellitech.spider.model.RootPage;
import com.intellitech.spider.similar.SimilarityCounter;

public class Spider extends Thread{
	static float THRESHOLD_SCORE = 0.8f;
	
	
	private Filter filter;
	
	private TextAnalyzer analyzer; 
	private RootPageMapper rootPageMapper;
	
	
	private SimilarityCounter similarityCounter;
	
	private LinkMapper linkMapper;

	List<RootPage> pages;
	
	volatile boolean stop =false;

	public Spider(List<RootPage> pages, Filter filter, TextAnalyzer analyzer, SimilarityCounter similarityCounter, LinkMapper linkMapper,RootPageMapper rootPageMapper) {
		super();
		this.filter = filter;
		this.analyzer = analyzer;
		this.similarityCounter = similarityCounter;
		this.linkMapper = linkMapper;
		this.pages = pages;
		this.rootPageMapper = rootPageMapper;
	}

	public void run()
	{
		for(RootPage page:pages)
		{
			if(stop)
				return;
			try {
				List<Link> links = parsePage(page);
				
				//旧首页，爬出来的链接需要过滤，只保存新增的link。
				if(page.getIsNew() == Constants.OLD_PAGE)
				{
					links = filterLinks(links);
				}
				else
				{
					page.setIsNew(Constants.OLD_PAGE);
					rootPageMapper.updateByPrimaryKey(page);
				}
				int newLink = storeLinks(links);
				System.out.println(newLink);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String crawl(String url) throws ClientProtocolException, IOException {

		// 构造HttpClient的实例
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建GET方法的实例
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		// The underlying HTTP connection is still held by the response object
		// to allow the response content to be streamed directly from the
		// network socket.
		// In order to ensure correct deallocation of system resources
		// the user MUST call CloseableHttpResponse#close() from a finally
		// clause.
		// Please note that if response content is not fully consumed the
		// underlying
		// connection cannot be safely re-used and will be shut down and
		// discarded
		// by the connection manager.
		
		try {
			System.out.println(response.getStatusLine());
			HttpEntity entity= response.getEntity();
			ContentType contentType = ContentType.getOrDefault(entity);
	        Charset charset = contentType.getCharset();
	        Reader reader = new InputStreamReader(entity.getContent());
	        BufferedReader bufferedReader = new BufferedReader(reader);
	        System.out.println(bufferedReader.readLine());
	        System.out.println(bufferedReader.readLine());
	        System.out.println(bufferedReader.readLine());
	        // do something useful with the response body
			// and ensure it is fully consumed
			EntityUtils.consume(entity);
			return entity.toString();
		} finally {
			response.close();
		}
	}
	
	public List<Link>parsePage(RootPage page) throws IOException
	{
		Document doc = Jsoup.connect(page.getUrl()).get();
        Elements links = doc.select("a[href]");
        List<Link> linkList = new ArrayList();
        for (Element link : links) {
            //print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
            //System.out.println(link);
        	int status = Constants.NEW_PAGE;
        	//新首页
        	if(page.getIsNew()==Constants.NEW_PAGE)
        	{
        		//新增的首页，爬出来的网页不需要发送，都当成旧网页存起来
        		status = Constants.OLD_PAGE;
        	}
        	if(!filter.filter(link))
        		linkList.add(new Link(null,link.attr("abs:href"),link.text(),page.getUrl(),analyzer.analyze(link.text()),status,new Date()));
        	
        }
        
        return linkList;
    }

	public List<Link> filterLinks(List<Link>links)
	{
		List<Link>filterLinks = new ArrayList<Link>();
		for(Link link:links)
		{
			float score= similarityCounter.maxSimilarScore(link.getTerms());
			if(score<THRESHOLD_SCORE)
			{
				filterLinks.add(link);
			}
		}
		return filterLinks;
	}
	
	public int storeLinks(List<Link>links)
	{
		int count=0;
		for(Link link:links)
		{
			count = count+linkMapper.insert(link);
		}
		return count;
	}
}
