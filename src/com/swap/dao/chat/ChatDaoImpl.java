package com.swap.dao.chat;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.swap.entity.chat.ChatEntity;
import com.swap.entity.user.UserEntity;

@Service
public class ChatDaoImpl implements ChatDao {

	@Inject
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void createChatDetails(ChatEntity chatDetails) {
		sessionFactory.getCurrentSession().save(chatDetails);
	}

	@Override
	public ChatEntity getChatChannelByOriginalUser(String originalUserId) {

		// Create CriteriaBuilder
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		// Create CriteriaQuery
		CriteriaQuery<ChatEntity> criteriaQuery = builder.createQuery(ChatEntity.class);

		// Add conditions
		Root<ChatEntity> chatEntityRoot = criteriaQuery.from(ChatEntity.class);
		criteriaQuery.select(chatEntityRoot);
		UserEntity user = new UserEntity();
		user.setUserId(originalUserId);
		criteriaQuery.where(builder.equal(chatEntityRoot.get("originalUserId"), user));

		// execute
		List<ChatEntity> interests = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
		return interests.get(0);
	}

	@Override
	public ChatEntity getChatChannelByInterestedUser(String interestedUserId) {
		// Create CriteriaBuilder
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		// Create CriteriaQuery
		CriteriaQuery<ChatEntity> criteriaQuery = builder.createQuery(ChatEntity.class);

		// Add conditions
		Root<ChatEntity> chatEntityRoot = criteriaQuery.from(ChatEntity.class);
		criteriaQuery.select(chatEntityRoot);
		UserEntity user = new UserEntity();
		user.setUserId(interestedUserId);
		criteriaQuery.where(builder.equal(chatEntityRoot.get("interestedUserId"), user));

		// execute
		List<ChatEntity> interests = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
		return interests.get(0);
	}

	@Override
	public void deleteChatChannel(String channelId) {
		Query query = sessionFactory.getCurrentSession().createQuery("delete ChatEntity where channelId = :channelId");
		query.setParameter("channelId", channelId);
		query.executeUpdate();
	}
}
