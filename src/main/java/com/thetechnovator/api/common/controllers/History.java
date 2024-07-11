package com.thetechnovator.api.common.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.thetechnovator.api.common.auditlog.AuditLog;
import com.thetechnovator.api.common.constants.Views;
import com.thetechnovator.api.common.domain.IdentifiableEntity;
import com.thetechnovator.api.common.services.BaseChildEntityService;
import com.thetechnovator.api.common.services.BaseCrudService;

import io.swagger.v3.oas.annotations.Operation;

public interface History<T extends IdentifiableEntity<ID>, ID extends Serializable> extends CrudController<T, ID> {

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/history")
	@JsonView(value = Views.List.class)
	@Operation( description="Returns the change history for given resource id")
	@ResponseBody
	default ResponseEntity<List<AuditLog>> getHistory(@PathVariable ID id) {
		List<AuditLog> body = getService().getHistory(id);
		return ResponseEntity.ok(body);
	}
}
