package com.swap.client;

import org.springframework.stereotype.Component;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.swap.common.constants.Constants;

@Component
public class CloudinaryClient {
    Cloudinary cloudinary = null;

    public Cloudinary getCloudinary() {
        return new Cloudinary(ObjectUtils.asMap(Constants.CLOUD_NAME, Constants.MY_CLOUD_NAME, Constants.API_KEY,
                Constants.MY_API_KEY, Constants.API_SECRET, Constants.MY_API_SECRET));
    }
}
