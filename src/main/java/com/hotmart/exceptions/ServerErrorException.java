package com.hotmart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends RuntimeException {

	public ServerErrorException(Exception e) {
		super(e);
	}

	private static final long serialVersionUID = 212697209758981840L;
	
}
