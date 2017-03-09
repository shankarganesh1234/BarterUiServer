package com.swap.transformer.login;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Service;

import com.swap.common.security.SocialConfig;
import com.swap.models.login.LoginRequest;

@Service
public class LoginTransformerImpl implements LoginTransformer {
	@Inject
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	private SocialConfig socialConfig;

	@Autowired
	private UsersConnectionRepository usersConnectionRepository;

	@Override
	public UserProfile connectToProvider(LoginRequest inputRequest, String providerId) {
		OAuth2ConnectionFactory<?> connectionFactory = (OAuth2ConnectionFactory<?>) connectionFactoryLocator
				.getConnectionFactory(providerId);
		Connection<?> connection = connectionFactory.createConnection(new AccessGrant(inputRequest.getAccessToken()));
		// Facebook facebook = (Facebook) connection.getApi();
		UserProfile userProfile = connection.fetchUserProfile();

		ConnectionRepository repository = usersConnectionRepository.createConnectionRepository(userProfile.getId());

		Connection<Facebook> fbConnection = repository.findPrimaryConnection(Facebook.class);

		if (fbConnection == null)

			usersConnectionRepository.createConnectionRepository(userProfile.getId()).addConnection(connection);

		else
			usersConnectionRepository.createConnectionRepository(userProfile.getId()).updateConnection(connection);

		return userProfile;
	}

}
