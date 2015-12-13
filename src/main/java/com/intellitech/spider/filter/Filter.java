package com.intellitech.spider.filter;

import org.jsoup.nodes.Element;


public interface Filter {
	/*
	true 丢弃
	false 保留
	 */
	public boolean filter(Element link);
}
