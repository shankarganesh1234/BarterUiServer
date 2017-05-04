package com.swap.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.config.PropertiesFactoryBean;

import com.swap.models.search.BarterySearchRequest;
import com.swap.models.search.BarterySearchResponse;
import com.swap.service.search.SearchService;

@Path("/search")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

	@Inject
	private SearchService searchService;
	
	@Inject
	private PropertiesFactoryBean envProps;
	
	@Path("/item")
	@POST
	public BarterySearchResponse searchItems(BarterySearchRequest searchRequest) {
		
		try {
			System.out.println("Elasticsearch base url = " + envProps.getObject().getProperty("elasticsearch.baseurl"));
			System.out.println("Elasticsearch cluster name = " + envProps.getObject().getProperty("elasticsearch.clustername"));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return searchService.searchItems(searchRequest);
	}
	
	@Path("/autocomplete/{term}")
	@GET
	public List<String> autoComplete(@PathParam("term") String term) {
		return searchService.autoComplete(term);
	}
	
	
}
