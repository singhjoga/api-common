package com.technovator.api.common.events;

import org.springframework.context.ApplicationEvent;

import com.technovator.api.common.domain.AppObect;

public class BaseEvent<T extends AppObect> extends ApplicationEvent{

	private static final long serialVersionUID = 1L;
	private T data;
	private String resourceType;
	private String action;

	public BaseEvent(Object source, T data, String resourceType, String action) {
		super(source);
		this.data = data;
		this.resourceType = resourceType;
		this.action = action;
	}

	public T getData() {
		return data;
	}

	public String getResourceType() {
		return resourceType;
	}

	public String getAction() {
		return action;
	}
	
}
