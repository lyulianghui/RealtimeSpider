package com.intellitech.spider;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
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

import com.intellitech.spider.analyzer.Analyzer;
import com.intellitech.spider.dao.LinkMapper;
import com.intellitech.spider.model.Link;
import com.intellitech.spider.model.LinkExample;
import com.intellitech.spider.similar.Similarity;

public class Spider {
	static float THRESHOLD_SCORE = 0.8f;
	
	@Autowired
	private Analyzer analyzer; 
	
	
	
	@Autowired
	private Similarity similarity;
	
	@Autowired
	private LinkMapper linkMapper;
	public LinkMapper getLinkMapper() {
		return linkMapper;
	}

	public void setLinkMapper(LinkMapper linkMapper) {
		this.linkMapper = linkMapper;
	}

	public Spider()
	{
	}
	private List<Link> getExistLinks() {
		// TODO Auto-generated method stub
		return linkMapper.selectByExample(new LinkExample());
	}
	private Closeable response;

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
	
	public List<Link>parsePage(String url) throws IOException
	{
		Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        List<Link> linkList = new ArrayList();
        for (Element link : links) {
            //print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
            System.out.println(link);
            linkList.add(new Link(null,link.attr("abs:href"),link.text(),url,""));
        }
        
        return linkList;
    }

	public List<Link> filterLinks(List<Link>links)
	{
		List<Link>filterLinks = new ArrayList<Link>();
		for(Link link:links)
		{
			float score= similarity.maxSimilarScore(link.getTerms());
			if(score>=THRESHOLD_SCORE)
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
