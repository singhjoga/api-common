package com.technovator.api.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author JogaSingh
 * 
 * Audit field marker. Values of Audit fields in entities is set by the framework itself.
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AuditField {
	AuditFieldType value();

	public static enum AuditFieldType {
		CREATE_DATE, //
		CREATE_USER, //
		UPDATE_DATE, //
		UPDATE_USER
	}
}
