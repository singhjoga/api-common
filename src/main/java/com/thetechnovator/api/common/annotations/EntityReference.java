package com.thetechnovator.api.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import com.thetechnovator.api.common.domain.IdentifiableEntity;
import com.thetechnovator.api.common.validation.EntityReferenceValidator;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy = EntityReferenceValidator.class)
public @interface EntityReference {
  Class<? extends IdentifiableEntity<?>> value();
  String message() default "Referenced entity '${value.simpleName}' not found for '${validatedValue}'";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default {};
}
