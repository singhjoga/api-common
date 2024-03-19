package com.technovator.api.common.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.technovator.api.common.constants.Views;
import com.technovator.api.common.controllers.RestResponse.AddResponse;
import com.technovator.api.common.controllers.RestResponse.BulkAddUpdateResponse;
import com.technovator.api.common.domain.IdentifiableEntity;
import com.technovator.api.common.services.BaseCrudService;

import io.swagger.v3.oas.annotations.Operation;

public class BaseCudController<T extends IdentifiableEntity<ID>, ID extends Serializable> extends BaseController {

	private BaseCrudService<T, ID> service;
	public BaseCudController(BaseCrudService<T, ID> service) {
		super();
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.POST)
	@Operation(description = "Add a new resource")
	@ResponseBody
	public ResponseEntity<AddResponse> add(@JsonView(value = Views.Add.class) @RequestBody T obj) {
		T saved = service.add(obj);
		return addRestResponse(saved.getId().toString());
	}
	@RequestMapping(method = RequestMethod.PUT,value = "/bulk")
	@Operation(description = "Add or overwite a list of resources")
	@ResponseBody
	public ResponseEntity<BulkAddUpdateResponse> addOverwriteList(@JsonView(value = {Views.Update.class}) @RequestBody List<T> objList) {
		List<ID> ids = service.addOrOverwriteAll(objList);
		return bulkAddUpdateResponse(ids);
	}
	@RequestMapping(method = RequestMethod.PATCH,value = "/bulk")
	@Operation(description = "Add or patch a list of resources")
	@ResponseBody
	public ResponseEntity<BulkAddUpdateResponse> addUpdateList(@JsonView(value = Views.Update.class) @RequestBody List<T> objList) {
		List<ID> ids = service.addOrUpdateAll(objList);
		return bulkAddUpdateResponse(ids);
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
	@Operation(description = "Partial update an existing resource")
	@ResponseBody
	public ResponseEntity<Void> update(@PathVariable ID id, @JsonView(value = Views.Update.class) @RequestBody T obj) {
		service.update(id, obj);
		return okResponse();
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@Operation(description = "Overwrite an existing resource")
	@ResponseBody
	public ResponseEntity<Void> overwite(@PathVariable ID id, @JsonView(value = Views.Update.class) @RequestBody T obj) {
		service.overwrite(id, obj);
		return okResponse();
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@Operation(description = "Delete an existing resource. Validation error is returned if it is referenced in other resources")
	@ResponseBody
	public ResponseEntity<Void> delete(@PathVariable ID id) {
		service.delete(id);
		return okResponse();
	}

}