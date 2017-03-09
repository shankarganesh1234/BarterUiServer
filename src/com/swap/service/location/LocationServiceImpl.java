package com.swap.service.location;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swap.dao.location.LocationDao;
import com.swap.entity.location.LocationEntity;
import com.swap.models.location.LocationRequest;
import com.swap.models.location.LocationResponse;
import com.swap.models.location.LocationsResponse;

@Service
public class LocationServiceImpl implements LocationService {

	@Inject
	private LocationDao locationDao;

	@Override
	@Transactional
	public void createLocation(LocationRequest request) {
		LocationEntity entity = new LocationEntity();
		BeanUtils.copyProperties(request, entity);
		locationDao.createLocation(entity);

	}

	@Override
	@Transactional
	public void updateLocation(LocationRequest request) {
		LocationEntity entity = new LocationEntity();
		BeanUtils.copyProperties(request, entity);
		locationDao.updateLocation(entity);
	}

	@Override
	@Transactional
	public void deleteLocation(Long zipCode) {
		locationDao.deleteLocation(zipCode);
	}

	@Override
	@Transactional
	public LocationResponse getLocation(Long zipCode) {
		LocationEntity entity = locationDao.getLocation(zipCode);
		LocationResponse response = new LocationResponse();
		BeanUtils.copyProperties(entity, response);
		return response;
	}

	@Override
	@Transactional
	public LocationsResponse getLocations() {
		LocationsResponse response = new LocationsResponse();
		List<LocationEntity> locations = locationDao.getLocations();
		List<LocationResponse> reponseList = new LinkedList<>();
		for (LocationEntity entity : locations) {
			LocationResponse locationResponse = new LocationResponse();
			BeanUtils.copyProperties(entity, locationResponse);
			reponseList.add(locationResponse);
		}
		response.setLocations(reponseList);
		return response;
	}

}
