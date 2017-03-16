package com.swap.models.search;

import java.util.List;

import com.swap.models.elasticsearch.ItemDocument;

public class SearchResponse {
	
	private List<ItemDocument> items;
	private long start;
	private long limit = 10;
	private long total;
	private String search;
	private Long zip;
	private int page = 1;
	
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
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getLimit() {
		return limit;
	}
	public void setLimit(long limit) {
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
