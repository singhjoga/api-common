package com.technovator.api.common.webclient;

import com.thetechnovator.common.java.exceptions.TechnicalException;

public class ServerErrorException extends TechnicalException{
	private static final long serialVersionUID = -7927914993500248566L;

	public ServerErrorException(String message) {
		super(message);
	}

}
