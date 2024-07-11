package com.thetechnovator.api.common.controllers;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.thetechnovator.api.common.constants.Views;
import com.thetechnovator.api.common.controllers.RestResponse.AddResponse;
import com.thetechnovator.api.common.domain.IdentifiableEntity;

import io.swagger.v3.oas.annotations.Operation;

public interface Add<T extends IdentifiableEntity<ID>, ID extends Serializable> extends BaseController<T, ID> {

	@RequestMapping(method = RequestMethod.POST)
	@Operation(description = "Add a new resource")
	@ResponseBody
	default ResponseEntity<AddResponse> add(@JsonView(value = Views.Add.class) @RequestBody T obj) {
		T saved = getService().add(obj);
		return addRestResponse(saved.getId().toString());
	}
}