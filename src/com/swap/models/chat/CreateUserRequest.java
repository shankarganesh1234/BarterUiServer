package com.swap.models.chat;

public class CreateUserRequest {
	
	private String user_id;
	private String nickname;
	private String profile_url;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfile_url() {
		return profile_url;
	}
	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}
	@Override
	public String toString() {
		return "CreateUserRequest [user_id=" + user_id + ", nickname=" + nickname + ", profile_url=" + profile_url
				+ "]";
	}
	
}
