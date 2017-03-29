package com.swap.service.login;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Service;

import com.swap.models.login.LoginRequest;
import com.swap.validator.login.LoginValidator;

@Service
public class LoginServiceImpl implements LoginService {

	@Inject
	private LoginValidator loginValidator;

	@Inject
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	private UsersConnectionRepository usersConnectionRepository;

	@Override
	public UserProfile connectUser(LoginRequest inputRequest, String providerId) {

		// validate request
		loginValidator.validateProviderId(providerId);
		loginValidator.validateInputRequest(inputRequest);

		// get the facebook connection factory, since providerId = facebook.
		// config for connection factory defined in context.xml
		OAuth2ConnectionFactory<?> connectionFactory = getOAuth2ConnectionFactory(providerId);

		// create connection using access token
		Connection<?> connection = connectionFactory.createConnection(new AccessGrant(inputRequest.getAccessToken()));

		// fetch user profile
		UserProfile userProfile = connection.fetchUserProfile();

		// persist user information
		ConnectionRepository repository = usersConnectionRepository.createConnectionRepository(userProfile.getId());

		Connection<Facebook> fbConnection = repository.findPrimaryConnection(Facebook.class);

		if (fbConnection == null)
			usersConnectionRepository.createConnectionRepository(userProfile.getId()).addConnection(connection);
		else
			usersConnectionRepository.createConnectionRepository(userProfile.getId()).updateConnection(connection);

		return userProfile;
	}

	@Override
	public boolean test(String providerId, String accessToken) {
		// get the facebook connection factory, since providerId = facebook.
		// config for connection factory defined in context.xml
		OAuth2ConnectionFactory<?> connectionFactory = getOAuth2ConnectionFactory(providerId);

		// create connection using access token
		Connection<?> connection = connectionFactory.createConnection(new AccessGrant(accessToken));

		return connection.test();
	}

	@Override
	public boolean refresh(String providerId, String accessToken) {
		
		boolean result = false;
		
		// get the facebook connection factory, since providerId = facebook.
		// config for connection factory defined in context.xml
		OAuth2ConnectionFactory<?> connectionFactory = getOAuth2ConnectionFactory(providerId);

		// create connection using access token
		Connection<?> connection = connectionFactory.createConnection(new AccessGrant(accessToken));

		// perform refresh
		connection.refresh();
		
		result = true;
		return result;
	}

	/**
	 * 
	 * @param providerId
	 * @return
	 */
	private OAuth2ConnectionFactory<?> getOAuth2ConnectionFactory(String providerId) {
		return (OAuth2ConnectionFactory<?>) connectionFactoryLocator.getConnectionFactory(providerId);
	}

	@Override
	public boolean hasExpired(String providerId, String accessToken) {
		// get the facebook connection factory, since providerId = facebook.
		// config for connection factory defined in context.xml
		OAuth2ConnectionFactory<?> connectionFactory = getOAuth2ConnectionFactory(providerId);

		// create connection using access token
		Connection<?> connection = connectionFactory.createConnection(new AccessGrant(accessToken));

		return connection.hasExpired();
	}

}
