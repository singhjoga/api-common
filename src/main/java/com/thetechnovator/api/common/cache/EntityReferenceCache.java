package com.thetechnovator.api.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EntityReferenceCache {
	private static final Logger LOG = LoggerFactory.getLogger(EntityReferenceCache.class);

	private EntityCacheReference entityReferenceCache;
	public void init() {
		entityReferenceCache = new EntityCacheReference();
		entityReferenceCache.init();
	}

	public EntityCacheReference getEntityReferenceCache() {
		return entityReferenceCache;
	}

}
