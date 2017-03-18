package com.swap.models.search;

import java.util.List;

import com.swap.common.constants.Constants;
import com.swap.models.elasticsearch.ItemDocument;

public class BarterySearchResponse {
	
	private List<ItemDocument> items;
	private long total;
	private String search;
	private Long zip;
	private int start = Constants.START;
	private int limit = Constants.LIMIT;
	private int page = Constants.PAGE;
	
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
	public List<ItemDocument> getItems() {
		return items;
	}
	public void setItems(List<ItemDocument> items) {
		this.items = items;
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
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
