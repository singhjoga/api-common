package com.technovator.api.common.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.technovator.api.common.cache.EntityReferenceCache;

@Component
public class CommonAppInitializer {

	@Autowired
	private EntityReferenceCache entityRefCahce;
	
	public void onStart() {
		entityRefCahce.init();
	}
	public void onStop() {
		
	}
}
