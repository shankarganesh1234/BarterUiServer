package com.swap.models.chat;

public class UserResponse {

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
		return "UserResponse [user_id=" + user_id + ", is_online=" + is_online + "]";
	}
	
	
	
}
