package com.intellitech.spider.filter;

import org.jsoup.nodes.Element;


public interface Filter {
	public boolean filter(Element link);
}
