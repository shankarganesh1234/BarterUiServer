package com.swap.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.swap.models.location.LocationRequest;
import com.swap.models.location.LocationResponse;
import com.swap.models.location.LocationsResponse;
import com.swap.service.location.LocationService;

@Path("/location")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LocationResource {

	@Inject
	private LocationService locationService;

	@POST
	public void createLocation(LocationRequest request) {
		locationService.createLocation(request);
	}

	@PUT
	public void updateLocation(LocationRequest request) {
		locationService.updateLocation(request);
	}

	@DELETE
	@Path("/{zipCode}")
	public void deleteLocation(@PathParam("zipCode") Long zipCode) {
		locationService.deleteLocation(zipCode);
	}

	@GET
	@Path("/{zipCode}")
	public LocationResponse getLocation(@PathParam("zipCode") Long zipCode) {
		return locationService.getLocation(zipCode);
	}

	@GET
	public LocationsResponse getAllLocations() {
		return locationService.getLocations();
	}
}
