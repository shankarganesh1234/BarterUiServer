package com.swap.models.login;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AutheticatedUserToken {
	private String userId;
    private String token;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

    
}
