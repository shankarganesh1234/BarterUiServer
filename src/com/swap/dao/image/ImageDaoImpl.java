package com.swap.dao.image;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.swap.entity.listing.ImageEntity;

@Service
public class ImageDaoImpl implements ImageDao {

	@Inject
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ImageEntity createImage(ImageEntity imageEntity) {
		sessionFactory.getCurrentSession().save(imageEntity);
		return imageEntity;
	}

	@Override
	public void updateImage(ImageEntity imageEntity) {
		sessionFactory.getCurrentSession().update(imageEntity);
	}

	@Override
	public void deleteImage(ImageEntity imageEntity) {
		sessionFactory.getCurrentSession().delete(imageEntity);
	}

	@Override
	public ImageEntity getImageByItemId(ImageEntity imageEntity) {
		return sessionFactory.getCurrentSession().get(ImageEntity.class, imageEntity.getPublic_id());
	}

	@Override
	public List<ImageEntity> getImagesByItemId(ImageEntity imageEntity) {
		// TODO Auto-generated method stub
		return null;
	}
}
