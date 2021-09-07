package com.technovator.api.common.annotations.rest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.technovator.api.common.annotations.Authorization;
import com.technovator.api.common.constants.Actions;
import com.technovator.api.common.constants.Views;

import io.swagger.v3.oas.annotations.Operation;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

@RequestMapping(method = RequestMethod.GET, value = "/{id}")
@Authorization(action = Actions.Crud.View)
@JsonView(value = Views.View.class)
@Operation(description = "Get an existing resource by ID. Not Found error is thrown if the resource is not found")
@ResponseBody
public @interface GetOperation {
	@AliasFor(annotation = RequestMapping.class, attribute = "value")
    String path() default "/{id}";
}
