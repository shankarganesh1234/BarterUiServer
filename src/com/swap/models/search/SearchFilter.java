package com.swap.models.search;

public class SearchFilter {

	private String filterName;
	private SearchOperatorEnum operator;
	private String filterValue;
	
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public SearchOperatorEnum getOperator() {
		return operator;
	}
	public void setOperator(SearchOperatorEnum operator) {
		this.operator = operator;
	}
	public String getFilterValue() {
		return filterValue;
	}
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	
	
}
