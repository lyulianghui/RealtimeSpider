package com.intellitech.spider.filter;

import org.jsoup.nodes.Element;

import com.intellitech.spider.model.Link;

public class ShortTextFilter implements Filter{
final static int MIN_LENGTH = 7; 
	@Override
	public boolean filter(Element link) {
		// TODO Auto-generated method stub
		return link.text().length()<=MIN_LENGTH;
	}

}
