package com.swap.resources;

import java.util.List;

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

import com.swap.models.listing.Item;
import com.swap.models.listing.ListingRequest;
import com.swap.service.listing.ListingService;

@Path("listing")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ListingResource {

	@Inject
	ListingService listingService;

	@Path("{itemId}")
	@GET
	public Item getListingByItemId(@PathParam("itemId") Long itemId) {
		return listingService.getListingByItemId(itemId);
	}

	@Path("/user/{userId}")
	@GET
	public List<Item> getListingsByUserId(@PathParam("userId") Long userId) {
		return listingService.getListingsByUserId(userId);
	}

	@POST
	public void listing(ListingRequest listingRequest) {
		listingService.listing(listingRequest);
	}

	@PUT
	public void updateListing(ListingRequest listingRequest) {
		listingService.updateListing(listingRequest);
	}

	@DELETE
	@Path("{itemId}")
	public void deleteListing(@PathParam("itemId") Long itemId) {
		listingService.deleteListing(itemId);
	}
}