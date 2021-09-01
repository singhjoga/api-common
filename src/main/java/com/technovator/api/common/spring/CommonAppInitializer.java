package com.technovator.api.common.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.technovator.api.common.cache.EntityReferenceCache;
import com.technovator.api.common.cache.SystemCache;

@Component
public class CommonAppInitializer {

	@Autowired
	private EntityReferenceCache entityRefCahce;
	
	@Autowired
	private SystemCache systemCache;
	
	public void onStart() {
		entityRefCahce.init();
		systemCache.load();
	}
	public void onStop() {
		
	}
}
