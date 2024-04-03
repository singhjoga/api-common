package com.thetechnovator.api.common.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thetechnovator.api.common.cache.EntityReferenceCache;
import com.thetechnovator.api.common.exception.ResourceNotFoundException;
import com.thetechnovator.api.common.repos.BaseRepository;

public class BaseEntityService<T , ID extends Serializable> extends BaseService {

	protected BaseRepository<T, ID> repo;
	protected Class<T> entityClass;
	@Autowired
	protected EntityReferenceCache staticCache;

	public BaseEntityService(BaseRepository<T, ID> repo, Class<T> entityClass, Class<ID> idClass) {
		super();
		this.repo=repo;
		this.entityClass=entityClass;
	}

	public T getById(ID id) {
		return getById(id, false);
	}

	public T getById(ID id, boolean optional) {
		T obj = repo.findById(id).orElse(null);
		if (obj == null && !optional) {
			throw new ResourceNotFoundException(entityClass.getSimpleName(), id.toString());
		}
		return obj;
	}

	public T findById(ID id) {
		T obj = repo.findById(id).orElse(null);
	
		return obj;
	}

	public List<T> findAll() {
		return repo.findAll();
	}
}