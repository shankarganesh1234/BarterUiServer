package com.swap.common.httpclient;

import javax.annotation.PostConstruct;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

@Component
public class SwapHttpClientFactory {
	private CloseableHttpClient httpClient = null;

	@PostConstruct
	public void init() {
		int timeout = 2;
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(timeout * 1000)
				.setConnectionRequestTimeout(timeout * 1000)
				.setSocketTimeout(timeout * 1000).build();

		setHttpClient(HttpClientBuilder.create().setDefaultRequestConfig(config).build());
	}

	public CloseableHttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(CloseableHttpClient httpClient) {
		this.httpClient = httpClient;
	}
}
