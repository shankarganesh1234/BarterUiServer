package com.swap.models.sendbird;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {

	private String channel_url;
	private String name;
	public String getChannel_url() {
		return channel_url;
	}
	public void setChannel_url(String channel_url) {
		this.channel_url = channel_url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Channel [channel_url=").append(channel_url).append(", name=").append(name).append("]");
		return builder.toString();
	}
	
	
	
}
