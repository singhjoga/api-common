package com.thetechnovator.api.common.controllers;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.thetechnovator.api.common.constants.Views;
import com.thetechnovator.api.common.domain.IdentifiableEntity;

import io.swagger.v3.oas.annotations.Operation;

public interface Overwrite<T extends IdentifiableEntity<ID>, ID extends Serializable> extends BaseController<T, ID> {
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@Operation(description = "Overwrite an existing resource")
	@ResponseBody
	default ResponseEntity<Void> overwite(@PathVariable ID id, @JsonView(value = Views.Update.class) @RequestBody T obj) {
		getService().overwrite(id, obj);
		return okResponse();
	}
}