package com.intellitech.spider;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
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

import com.intellitech.spider.html.Link;

public class Spider {
	public Spider()
	{
		System.out.println("..............");
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

        for (Element link : links) {
            //print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
            System.out.println(link);
        }
        
        return null;
    }

}
