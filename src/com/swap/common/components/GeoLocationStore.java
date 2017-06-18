package com.swap.common.components;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.swap.client.GoogleLocationClient;
import com.swap.common.constants.Constants;
import com.swap.models.common.GeoLocation;

@Component
public class GeoLocationStore {

	@Inject
	private GoogleLocationClient googleLocationClient;
	
	private static Logger logger = Logger.getLogger(GeoLocationStore.class);
	private Map<String, GeoLocation> geoLocationMap;

	public Map<String, GeoLocation> getGeoLocationMap() {
		return geoLocationMap;
	}

	public void setGeoLocationMap(Map<String, GeoLocation> geoLocationMap) {
		this.geoLocationMap = geoLocationMap;
	}

	@PostConstruct
	public void init() {
		geoLocationMap = new HashMap<>();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(Constants.GEO_FILE_NAME).getFile());

		try {

			List<String> lines = FileUtils.readLines(file, Constants.ENCODING_UTF);
			for (String line : lines) {
				GeoLocation location = new GeoLocation();
				String[] geoLocationStr = line.split(",");
				
				location.setZip(geoLocationStr[0]);
				location.setLatitude(geoLocationStr[1]);
				location.setLongitude(geoLocationStr[2]);
				geoLocationMap.put(location.getZip(), location);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Unable to load geo location store");
		}
	}
	
	/**
	 * When zip code is not present in store, then invoke google location client 
	 * and get it from google maps api
	 * @return
	 */
	public GeoLocation getLocationFromGoogle(Long zipCode) {
		GeoLocation geoLocation = googleLocationClient.getLocation(zipCode);
		
		if(geoLocation == null)
			return null;
		
		// add to map
    	this.geoLocationMap.put(geoLocation.getZip(), geoLocation);
	    
		return geoLocation;
	}
	
}
