package com.swap.common.security;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.security.AuthenticationNameUserIdSource;

@Configuration
public class SocialConfig extends SocialConfigurerAdapter {
	@Inject
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Inject
	private DataSource dataSource;

	@Inject
	TextEncryptor textEncryptor;

//	@Bean
//	@Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
//	public ConnectionFactoryLocator connectionFactoryLocator() {
//		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
//		 registry.addConnectionFactory(new FacebookConnectionFactory(
//		 environment.getProperty("facebook.clientId"),
//		 environment.getProperty("facebook.clientSecret")));
////		registry.addConnectionFactory(
////				new FacebookConnectionFactory("1349995671730953", "52e714a35dc167259b385e5d8a62c0a0"));
//
//		return registry;
//	}

	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, textEncryptor);
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
		}
		return usersConnectionRepository().createConnectionRepository(authentication.getName());
	}

	@Bean
	public ConnectController connectController() {
		ConnectController controller = new ConnectController(connectionFactoryLocator, connectionRepository());
		// controller.setApplicationUrl(environment.getProperty("application.url"));
		return controller;
	}
	
	@Bean
	public UserIdSource userIdSource(){
		
		return new AuthenticationNameUserIdSource();
	}

}