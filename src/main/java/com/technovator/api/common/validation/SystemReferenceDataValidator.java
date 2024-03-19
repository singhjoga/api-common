package com.technovator.api.common.validation;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.technovator.api.common.annotations.SystemReferenceData;
import com.technovator.api.common.db.EntityManagerProvider;
import com.technovator.api.common.refdata.RefData;

public class SystemReferenceDataValidator implements ConstraintValidator<SystemReferenceData, Object>{

	private SystemReferenceData annotation;
	
	@Override
	public void initialize(SystemReferenceData constraintAnnotation) {
		this.annotation=constraintAnnotation;
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value==null) return true;
		String type = annotation.value();
	    EntityManager em = EntityManagerProvider.getEntityManager();
	    RefData result=em.find(RefData.class, value);
	    if (result==null) {
	    	return false;
	    }
	    return result.getTypeId().equals(type);
	}

}
