package com.swap.common.error;

import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorContainer{

	private int status;
	private ErrorEnum type;
	private String message;
	private String debugMessage;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ErrorEnum getType() {
		return type;
	}
	public void setType(ErrorEnum type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@XmlElement(nillable = true)
	public String getDebugMessage() {
		return debugMessage;
	}
	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}
	
}
