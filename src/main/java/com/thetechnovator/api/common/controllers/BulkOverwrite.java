package com.thetechnovator.api.common.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.thetechnovator.api.common.constants.Views;
import com.thetechnovator.api.common.controllers.RestResponse.BulkAddUpdateResponse;
import com.thetechnovator.api.common.domain.IdentifiableEntity;

import io.swagger.v3.oas.annotations.Operation;

public interface BulkOverwrite<T extends IdentifiableEntity<ID>, ID extends Serializable> extends BaseController<T, ID> {
	@RequestMapping(method = RequestMethod.PUT,value = "/bulk")
	@Operation(description = "Add or overwite a list of resources")
	@ResponseBody
	default ResponseEntity<BulkAddUpdateResponse> addOverwriteList(@JsonView(value = {Views.Update.class}) @RequestBody List<T> objList) {
		List<ID> ids = getService().addOrOverwriteAll(objList);
		return bulkAddUpdateResponse(ids);
	}
}