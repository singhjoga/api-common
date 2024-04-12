package com.thetechnovator.api.common.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.thetechnovator.api.common.constants.Views;
import com.thetechnovator.api.common.domain.IdentifiableEntity;
import com.thetechnovator.api.common.services.BaseChildEntityService;

import io.swagger.v3.oas.annotations.Parameter;


public interface ReadAllChild<T extends IdentifiableEntity<ID>, ID extends Serializable, PARENT_ID extends Serializable> extends BaseController<T, ID> {

	@RequestMapping(method = RequestMethod.GET)
	@JsonView(value=Views.List.class) 
	default @ResponseBody ResponseEntity<List<T>> findAll(//
			@Parameter(description="ID of the parent object",example = "1", required = true) @RequestParam(value = "parentId", required = true) PARENT_ID parentId) {
		BaseChildEntityService<T,ID, PARENT_ID> service = (BaseChildEntityService<T,ID, PARENT_ID>)getService();
		List<T> body = service.findAll(parentId);
		return ResponseEntity.ok(body);
	}
	
}
