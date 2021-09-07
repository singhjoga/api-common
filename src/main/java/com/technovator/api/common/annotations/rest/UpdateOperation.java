package com.technovator.api.common.annotations.rest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.technovator.api.common.annotations.Authorization;
import com.technovator.api.common.constants.Actions;

import io.swagger.v3.oas.annotations.Operation;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
@Authorization(action = Actions.Crud.Update)
@Operation(description = "Update an existing resource")
@ResponseBody
public @interface UpdateOperation {
	@AliasFor(annotation = RequestMapping.class, attribute = "value")
    String path() default "/{id}";
}
