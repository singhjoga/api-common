package com.technovator.api.common.exception;

public class ResourceNotFoundException extends TechnicalException{

	private static final long serialVersionUID = -12069870686023303L;

	public ResourceNotFoundException(String resourceName, Long id) {
		this(resourceName, id.toString());
	}
	public ResourceNotFoundException(String resourceName, String id) {
		super(String.format("Resource '%s' not found for id '%s'", resourceName, id));
	}
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
