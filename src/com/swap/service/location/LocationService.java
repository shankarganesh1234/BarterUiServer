package com.swap.service.location;

import com.swap.models.location.LocationRequest;
import com.swap.models.location.LocationResponse;
import com.swap.models.location.LocationsResponse;

public interface LocationService {
	
	void createLocation(LocationRequest request);
	void updateLocation(LocationRequest request);
	void deleteLocation(Long zipCode);
	LocationResponse getLocation(Long zipCode);
	LocationsResponse getLocations();
}
