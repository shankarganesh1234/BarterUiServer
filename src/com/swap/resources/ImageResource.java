package com.swap.resources;

import java.io.InputStream;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.swap.service.image.ImageService;

@Path("image")
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class ImageResource {

	@Inject
	ImageService imageService;

	@POST
	@Path("/upload")
	public void uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("itemId") Long itemId) {
		imageService.createImage(uploadedInputStream, fileDetail, itemId);
	}
}
