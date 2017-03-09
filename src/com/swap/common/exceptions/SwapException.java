package com.swap.common.exceptions;

import javax.ws.rs.WebApplicationException;

import com.swap.common.error.ErrorEnum;

public class SwapException extends WebApplicationException {

	private static final long serialVersionUID = 1L;
	private ErrorEnum errorEnum;
	
	public SwapException(ErrorEnum errorEnum) {
        this.setErrorEnum(errorEnum);
    }

	public ErrorEnum getErrorEnum() {
		return errorEnum;
	}

	public void setErrorEnum(ErrorEnum errorEnum) {
		this.errorEnum = errorEnum;
	}
}
