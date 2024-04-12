package com.thetechnovator.api.common.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.thetechnovator.api.common.constants.Views;
import com.thetechnovator.api.common.domain.IdentifiableEntity;

import io.swagger.v3.oas.annotations.Operation;

public interface ReadAll<T extends IdentifiableEntity<ID>, ID extends Serializable> extends BaseController<T, ID> {

	@RequestMapping(method = RequestMethod.GET)
	@JsonView(value = Views.View.class)
	@Operation(description = "Get an existing resource by ID. Not Found error is thrown if the resource is not found")
	@ResponseBody
	default ResponseEntity<List<T>> findAll() {
		return ResponseEntity.ok(getService().findAll());
	}


}