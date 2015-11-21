package com.intellitech.spider.html;

public class Link {
	public Link(String href, String title) {
		super();
		this.href = href;
		this.title = title;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	String href;
	String title;
}
