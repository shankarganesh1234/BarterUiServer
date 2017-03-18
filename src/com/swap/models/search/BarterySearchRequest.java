package com.swap.models.search;

import com.swap.common.constants.Constants;

public class BarterySearchRequest {

	private String search;
	private Long zip;
	private int start = Constants.START;
	private int limit = Constants.LIMIT;
	private int page = Constants.PAGE;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Long getZip() {
		return zip;
	}

	public void setZip(Long zip) {
		this.zip = zip;
	}
}
