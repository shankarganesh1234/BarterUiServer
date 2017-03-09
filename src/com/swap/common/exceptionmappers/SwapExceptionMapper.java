package com.swap.common.exceptionmappers;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.swap.common.components.CommonRequestComponent;
import com.swap.common.constants.Constants;
import com.swap.common.error.ErrorContainer;
import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;

@Provider
public class SwapExceptionMapper extends Exception implements ExceptionMapper<SwapException> {

	private static final long serialVersionUID = 1L;
	private static final ErrorEnum defaultErrorEnum = ErrorEnum.GEN_SYSTEM_ERROR;
	private static final Logger logger = Logger.getLogger(SwapExceptionMapper.class);
	
	@Inject
	CommonRequestComponent requestComponent;
	
	@Inject
    private ReloadableResourceBundleMessageSource resourceBundle;
	
	@Override
	public Response toResponse(SwapException exception) {
		
		ErrorContainer errorContainer = createErrorContainer(exception);
		
		// if error container is null then return a fall back message
		if(errorContainer == null)
			return Response.status(Constants.INTERNAL_SERVER_ERROR).entity(getFallbackMessage())
                .type(MediaType.APPLICATION_JSON).build();
		
		// return the proper error message and status, reading from the error container
		return Response.status(errorContainer.getStatus()).entity(errorContainer)
                .type(MediaType.APPLICATION_JSON).build();
		
	}
	
	/**
	 * Create the error container by :-
	 * 1. Reading the swap exception
	 * 2. Get the associated error enum
	 * 3. Look up the properties file based on the error enum and locale
	 * 4. Set the status by reading the error enum
	 * @param exception
	 * @return
	 */
	private ErrorContainer createErrorContainer(SwapException exception) {
		
		ErrorEnum errorEnum = exception.getErrorEnum();
		ErrorContainer errorContainer = new ErrorContainer();		
		errorContainer.setMessage(getLocalizedMessage(errorEnum));
		errorContainer.setStatus(getStatus(errorEnum));
		errorContainer.setType(errorEnum);
		return errorContainer;
	}
	
	/**
	 * Returns the localized message corresponding to the error enum
	 * @param errorEnum
	 * @return
	 */
	private String getLocalizedMessage(ErrorEnum errorEnum) {
		
		String localizedMessage = null;
		try {
			
			if(errorEnum == null)
				throw new Exception();
			
			localizedMessage = resourceBundle.getMessage(errorEnum.name().toString(), null, requestComponent.getLocale());
			if(localizedMessage == null || localizedMessage.isEmpty()) {
				logger.debug("Could not find the localized message from the properties file.");
				logger.debug("Fallback to general system failure");
				localizedMessage = defaultErrorEnum.name().toString();
			}
			// double check if localized message is null
			// possible that resource bundle did not find the message
			// and did not throw any error
			if(localizedMessage == null)
				throw new Exception();
			
		} catch (Exception ex) {
			logger.debug("Exception in SwapExceptionMapper.getLocalizedMessage()");
			ex.printStackTrace();
			localizedMessage = "An internal server error occurred";
		}
		return localizedMessage;
	}
	
	/**
	 * Returns the status code corresponding to the error enum
	 * @param errorEnum
	 * @return
	 */
	private int getStatus(ErrorEnum errorEnum) {
		
		int status = 0;
		try {
			if(errorEnum == null)
				throw new Exception();
			
			status = errorEnum.getStatusCode();
			
		} catch (Exception ex) {
			logger.debug("Exception within SwapExceptionMapper.getStatus()");
			ex.printStackTrace();
			status = Constants.INTERNAL_SERVER_ERROR;
		}
		return status;
	}
	
	private String getFallbackMessage() {
		return "An internal server error occurred";
	}

}
