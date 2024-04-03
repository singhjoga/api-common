package com.thetechnovator.api.common.validation;

import java.lang.annotation.Annotation;

import com.thetechnovator.api.common.db.EntityManagerProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public abstract class SqlValidator<T extends Annotation, V> implements ConstraintValidator<T, V>{
	private String sql;
	public SqlValidator(String sql) {
		super();
		this.sql = sql;
	}

	@Override
	public void initialize(T constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(V value, ConstraintValidatorContext context) {
		if (value==null) return true;
	    EntityManager em = EntityManagerProvider.getEntityManager();
	    Query q = em.createNativeQuery(sql);
	    q.setParameter(1, value);
	    if (q.getResultList().isEmpty()) {
	    	return false;
	    }
	    return true;
	}

}
