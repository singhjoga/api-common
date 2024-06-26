package com.thetechnovator.api.common.validation;

import java.lang.annotation.Annotation;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.thetechnovator.api.common.db.EntityManagerProvider;
import com.thetechnovator.api.common.domain.IdentifiableEntity;

public abstract class BaseEntityReferenceValidator<T extends Annotation> implements ConstraintValidator<T, Object>{

	private T annotation;
	
	@Override
	public void initialize(T constraintAnnotation) {
		this.annotation=constraintAnnotation;
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value==null) return true;
		Class<? extends IdentifiableEntity<?>> refEntity = getValue(annotation);
	    EntityManager em = EntityManagerProvider.getEntityManager();
	    Object result=em.find(refEntity, value);
	    return result != null;
	}
	protected abstract Class<? extends IdentifiableEntity<?>> getValue( T annotation);
}
