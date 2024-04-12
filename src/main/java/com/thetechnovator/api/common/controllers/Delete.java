package com.thetechnovator.api.common.controllers;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thetechnovator.api.common.domain.IdentifiableEntity;

import io.swagger.v3.oas.annotations.Operation;

public interface Delete<T extends IdentifiableEntity<ID>, ID extends Serializable> extends BaseController<T, ID> {

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@Operation(description = "Delete an existing resource. Validation error is returned if it is referenced in other resources")
	@ResponseBody
	default ResponseEntity<Void> delete(@PathVariable ID id) {
		getService().delete(id);
		return okResponse();
	}

}