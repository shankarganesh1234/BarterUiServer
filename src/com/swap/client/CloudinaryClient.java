package com.swap.client;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.swap.common.constants.Constants;

@Component
public class CloudinaryClient {

	private static final Logger logger = Logger.getLogger(CloudinaryClient.class);

    Cloudinary cloudinary = null;

    public Cloudinary getCloudinary() {
    	logger.debug("Processing CloudinaryClient.getCloudinary()...");
        return new Cloudinary(ObjectUtils.asMap(Constants.CLOUD_NAME, Constants.MY_CLOUD_NAME, Constants.API_KEY,
                Constants.MY_API_KEY, Constants.API_SECRET, Constants.MY_API_SECRET));
    }
}
