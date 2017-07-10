package com.swap.models.sendbird;

import java.util.Arrays;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendbirdWebhookRequest {

	private String category;
	private Channel channel;
	private Sender sender;
	private Payload payload;
	private Member members[];
	
	public Member[] getMembers() {
		return members;
	}
	public void setMembers(Member[] members) {
		this.members = members;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public Sender getSender() {
		return sender;
	}
	public void setSender(Sender sender) {
		this.sender = sender;
	}
	public Payload getPayload() {
		return payload;
	}
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "SendbirdWebhookRequest [category=" + category + ", channel=" + channel + ", sender=" + sender
				+ ", payload=" + payload + ", members=" + Arrays.toString(members) + "]";
	}
	
}
