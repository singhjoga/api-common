package com.thetechnovator.api.common.cache;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.Entity;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.data.util.AnnotatedTypeScanner;

import com.thetechnovator.api.common.annotations.EntityReference;
import com.thetechnovator.api.common.constants.Constants;

public class EntityCacheReference {
	private Map<Class<?>, List<EntityReferenceCacheEntry>> entityReferences=new HashMap<>();
	
	public void init() {
		AnnotatedTypeScanner scanner = new AnnotatedTypeScanner(Entity.class);
		Set<Class<?>> classes = scanner.findTypes(Constants.BASE_PACKAGE);
		for(Class<?> cls: classes) {
			//General references
			List<Field> fields = FieldUtils.getFieldsListWithAnnotation(cls, EntityReference.class);
			for (Field field: fields) {
				Class<?> refEntity = field.getAnnotation(EntityReference.class).value();
				addEntityRefCache(refEntity, cls, field);
			}
		}
	}
	
	private void addEntityRefCache(Class<?> entity, Class<?> referencedInEntity, Field referencedInField) {
		EntityReferenceCacheEntry ref = new EntityReferenceCacheEntry(entity, referencedInField, referencedInEntity);
		List<EntityReferenceCacheEntry> list = entityReferences.get(entity);
		if (list ==null) {
			list = new ArrayList<>();
			entityReferences.put(entity, list);
		}
		list.add(ref);
	}

	public Map<Class<?>, List<EntityReferenceCacheEntry>> getEntityReferences() {
		return entityReferences;
	}

	public static class EntityReferenceCacheEntry {
		private Class<?> entity;
		private Field referencedInField;
		private Class<?> referencedInEntity;
		public EntityReferenceCacheEntry(Class<?> entity, Field referencedInField, Class<?> referencedInEntity) {
			super();
			this.entity = entity;
			this.referencedInField = referencedInField;
			this.referencedInEntity = referencedInEntity;
		}
		public Class<?> getEntity() {
			return entity;
		}
		public Field getReferencedInField() {
			return referencedInField;
		}
		public Class<?> getReferencedInEntity() {
			return referencedInEntity;
		}
		
	}
}
