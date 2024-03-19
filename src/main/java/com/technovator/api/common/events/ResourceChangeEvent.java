package com.technovator.api.common.events;

import com.technovator.api.common.domain.AppObect;

public class ResourceChangeEvent<T extends AppObect> extends BaseEvent<T>{
	private static final long serialVersionUID = 1L;

	public ResourceChangeEvent(Object source, T data, String resourceType, String action) {
		super(source, data, resourceType, action);
	}
}
