package com.swap.models.sendbird;

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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SendbirdWebhookRequest [channel=").append(channel).append(", sender=").append(sender)
				.append(", payload=").append(payload).append("]");
		return builder.toString();
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
