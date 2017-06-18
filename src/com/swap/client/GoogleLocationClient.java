package com.swap.client;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.swap.common.constants.Constants;
import com.swap.models.common.GeoLocation;

@Component
public class GoogleLocationClient {

	/**
	 * Get location from google maps api
	 * @return
	 */
	public GeoLocation getLocation(Long zip) {
	
		GeoLocation geoLocation = null;
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(Constants.GOOGLE_LOCATION_API_URL + zip);
		
		try {
			CloseableHttpResponse apiResponse = httpclient.execute(httpGet);
			
			if(apiResponse.getStatusLine().getStatusCode() != 200)
				return null;
			
		    HttpEntity entity = apiResponse.getEntity();
            String content = EntityUtils.toString(entity);
            JSONObject jsonResponse = new JSONObject(content);

            geoLocation = new GeoLocation();
            geoLocation.setZip(String.valueOf(zip));
            geoLocation.setLatitude(String.valueOf(jsonResponse.getJSONArray(Constants.RESULTS).getJSONObject(0).getJSONObject(Constants.GEOMETRY).getJSONObject(Constants.LOCATION).getBigDecimal(Constants.LAT)));
            geoLocation.setLongitude(String.valueOf(jsonResponse.getJSONArray(Constants.RESULTS).getJSONObject(0).getJSONObject(Constants.GEOMETRY).getJSONObject(Constants.LOCATION).getBigDecimal(Constants.LNG)));
            
            EntityUtils.consume(entity);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return geoLocation;
	}
	
}
