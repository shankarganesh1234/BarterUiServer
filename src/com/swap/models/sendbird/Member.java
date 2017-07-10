package com.swap.models.sendbird;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {

	private String user_id;
	private boolean is_online;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public boolean isIs_online() {
		return is_online;
	}
	public void setIs_online(boolean is_online) {
		this.is_online = is_online;
	}
	@Override
	public String toString() {
		return "Member [user_id=" + user_id + ", is_online=" + is_online + "]";
	}
}
