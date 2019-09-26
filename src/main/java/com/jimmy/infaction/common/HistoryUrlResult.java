package com.jimmy.infaction.common;


/**
 * @author jimmy on 2019/9/26 16:29
 */
public class HistoryUrlResult {
	private String url ;
	private String title;
	private Integer visit_count ;

	public String getWebsite() {
		return url;
	}

	public void setWebsite(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getVisit() {
		return visit_count;
	}

	public void setVisit(Integer visit_count) {
		this.visit_count = visit_count;
	}
}
