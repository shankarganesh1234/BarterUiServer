package com.swap.dao.location;

import java.util.List;

import com.swap.entity.location.LocationEntity;

public interface LocationDao {

	void createLocation(LocationEntity entity);
	void updateLocation(LocationEntity entity);
	void deleteLocation(Long zipCode);
	LocationEntity getLocation(Long zipCode);
	List<LocationEntity> getLocations();
}
