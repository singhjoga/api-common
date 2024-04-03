package com.thetechnovator.api.common.controllers;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.thetechnovator.api.common.constants.Views;
import com.thetechnovator.api.common.domain.IdentifiableEntity;
import com.thetechnovator.api.common.services.BaseCrudService;

import io.swagger.v3.oas.annotations.Operation;

public class BaseCrudController<T extends IdentifiableEntity<ID>, ID extends Serializable> extends BaseCudController<T, ID> {

	private BaseCrudService<T, ID> service;
	public BaseCrudController(BaseCrudService<T, ID> service) {
		super(service);
		this.service = service;
	}
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@JsonView(value = Views.View.class)
	@Operation(description = "Get an existing resource by ID. Not Found error is thrown if the resource is not found")
	@ResponseBody
	public ResponseEntity<T> getOne(@PathVariable ID id) {
		return ResponseEntity.ok(service.getById(id));
	}


}