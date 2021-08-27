package com.technovator.api.common.exception;

import com.thetechnovator.common.java.exceptions.TechnicalException;

public class ResourceNotFoundException extends TechnicalException{

	private static final long serialVersionUID = -12069870686023303L;

	public ResourceNotFoundException(String resourceName, Long id) {
		this(resourceName, id.toString());
	}
	public ResourceNotFoundException(String resourceName, String id) {
		super("Resource not found "+resourceName+" for id "+id);
	}
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
