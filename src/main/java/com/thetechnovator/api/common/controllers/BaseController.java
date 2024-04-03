package com.thetechnovator.api.common.controllers;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thetechnovator.api.common.controllers.RestResponse.AddResponse;
import com.thetechnovator.api.common.controllers.RestResponse.BulkOperationResponse;
import com.thetechnovator.api.common.exception.BadRequestException;
import com.thetechnovator.api.common.utils.DateUtils;

public abstract class BaseController {
	public BaseController() {
		super();
	}

	protected URI getCurrentURI() {
		return ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
	}

	protected URI getCurrentURI(Serializable id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().pathSegment(id.toString()). build().toUri();
	}
	protected Date toDateParam(String dateStr, String paramName) {
		if (dateStr==null) return null;
		//first convert using json format
		Date dt = DateUtils.toDateFromJsonFormat(dateStr);

		if (dt == null) {
			String msg = String.format("Parameter '%s' value '%s' is not in '%s' format", paramName,dateStr,DateUtils.JSON_DATE);
			throw new BadRequestException(msg);
		}
		
		return dt;
	}
	protected Date toDateTimeParam(String dateStr, String paramName) {
		if (dateStr==null) return null;
		//first convert using json format
		Date dt = DateUtils.toDateTimeFromJsonFormat(dateStr);

		if (dt == null) {
			String msg = String.format("Parameter '%s' value '%s' is not in '%s' format", paramName,dateStr,DateUtils.JSON_DATE_TIME);
			throw new BadRequestException(msg);
		}
		
		return dt;
	}

	protected ResponseEntity<AddResponse> addRestResponse(Long id) {
		return addRestResponse(id.toString());
	}
	protected ResponseEntity<AddResponse> addRestResponse(String id) {
		URI resUrl = getCurrentURI(id);
		return ResponseEntity.created(resUrl).body(RestResponseBuilder.addResponse(id, resUrl.toString()));
	}
	protected ResponseEntity<Void> okResponse() {
		return ResponseEntity.ok().build();
	}
	protected ResponseEntity<BulkOperationResponse> bulkOperationResponse(int affectedItems) {
		return ResponseEntity.ok(new BulkOperationResponse(affectedItems));
	}
	protected ResponseEntity<RestResponse.BulkAddUpdateResponse> bulkAddUpdateResponse(List<?> ids) {
		List<String> strIds = new ArrayList<>();
		ids.forEach(e-> strIds.add(e.toString()));
		return ResponseEntity.ok(new RestResponse.BulkAddUpdateResponse(strIds));
	}
}