package com.intellitech.spider.filter;

import java.util.List;

import javax.annotation.Resource;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;


public class FilterChain implements Filter{
	
	List<Filter>filters;
	public List<Filter> getFilters() {
		return filters;
	}
	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}
	@Override
	public boolean filter(Element link) {
		// TODO Auto-generated method stub
		for(Filter filter:filters)
		{
			if(filter.filter(link))
			{
				return true;
			}
		}
		return false;
	}

}
