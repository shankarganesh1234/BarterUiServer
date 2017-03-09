package com.swap.models.search;

import java.util.List;

import com.swap.models.elasticsearch.ItemDocument;

public class SearchResponse {
	
	private List<ItemDocument> items;
	private long start;
	private long limit = 10;
	private long total;
	
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
	
}
