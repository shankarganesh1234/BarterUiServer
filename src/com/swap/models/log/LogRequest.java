package com.swap.models.log;

import java.util.Date;

public class LogRequest {

	private String method;
	private String uri;
	private String clientIp;
	private Date requestDate;
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public LogRequest(String method, String uri, String clientIp, Date requestDate) {
		super();
		this.method = method;
		this.uri = uri;
		this.clientIp = clientIp;
		this.requestDate  = requestDate;
	}
	
}
