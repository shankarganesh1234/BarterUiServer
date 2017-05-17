package com.swap.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.swap.service.image.ImageService;

@Path("image")
public class ImageResource {

	@Inject
	ImageService imageService;

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void uploadImages(@FormDataParam("file") FormDataBodyPart body, @FormDataParam("itemId") Long itemId) {
		imageService.createImages(body, itemId);
	}
	
	@DELETE
	@Path("{imageId}")
	public void deleteImage(@PathParam("imageId") String imageId) {
		imageService.deleteImageFromDb(imageId);
	}
}
