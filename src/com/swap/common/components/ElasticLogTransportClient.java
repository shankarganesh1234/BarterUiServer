package com.swap.common.components;

import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Component;

import com.swap.common.constants.Constants;

@Component
public class ElasticLogTransportClient {

	private static final Logger logger = Logger.getLogger(CommonBeanUtils.class);
	private TransportClient transportClient = null;

	@Inject
	private PropertiesFactoryBean envProps;

	/**
	 * Ignores null values while copying bean properties from source to
	 * destination
	 * 
	 * @param dest
	 * @param source
	 * @throws UnknownHostException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@PostConstruct
	public void initTransportClient() throws UnknownHostException {
		logger.debug("Entering initTransportClient for initializing log transport client");
		try {
			Settings settings = Settings.builder()
					.put(Constants.CLUSTER_NAME, envProps.getObject().getProperty(Constants.ELASTICSEARCH_LOG_CLUSTERNAME))
					.build();
			transportClient = new PreBuiltTransportClient(settings);
			transportClient.addTransportAddress(new InetSocketTransportAddress(
					InetAddress.getByName(envProps.getObject().getProperty(Constants.ELASTICSEARCH_LOG_HOST)), 9300));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		logger.debug("Exiting initTransportClient for initializing transport client");

	}

	public TransportClient getTransportClient() {
		return transportClient;
	}

	public void setTransportClient(TransportClient transportClient) {
		this.transportClient = transportClient;
	}
}
