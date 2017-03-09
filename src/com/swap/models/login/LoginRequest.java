package com.swap.models.login;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginRequest {
	 private String accessToken;

	    @NotNull
	    public String getAccessToken() {
	        return accessToken;
	    }

	    public void setAccessToken(String accessToken) {
	        this.accessToken = accessToken;
	    }

}
