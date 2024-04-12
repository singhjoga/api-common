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
import com.thetechnovator.api.common.domain.IdentifiableEntity;
import com.thetechnovator.api.common.exception.BadRequestException;
import com.thetechnovator.api.common.services.BaseCrudService;
import com.thetechnovator.api.common.utils.DateUtils;

public interface BaseController<T extends IdentifiableEntity<ID>, ID extends Serializable> {

	default URI getCurrentURI() {
		return ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
	}

	default URI getCurrentURI(Serializable id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().pathSegment(id.toString()). build().toUri();
	}
	default Date toDateParam(String dateStr, String paramName) {
		if (dateStr==null) return null;
		//first convert using json format
		Date dt = DateUtils.toDateFromJsonFormat(dateStr);

		if (dt == null) {
			String msg = String.format("Parameter '%s' value '%s' is not in '%s' format", paramName,dateStr,DateUtils.JSON_DATE);
			throw new BadRequestException(msg);
		}
		
		return dt;
	}
	default Date toDateTimeParam(String dateStr, String paramName) {
		if (dateStr==null) return null;
		//first convert using json format
		Date dt = DateUtils.toDateTimeFromJsonFormat(dateStr);

		if (dt == null) {
			String msg = String.format("Parameter '%s' value '%s' is not in '%s' format", paramName,dateStr,DateUtils.JSON_DATE_TIME);
			throw new BadRequestException(msg);
		}
		
		return dt;
	}

	default ResponseEntity<AddResponse> addRestResponse(Long id) {
		return addRestResponse(id.toString());
	}
	default ResponseEntity<AddResponse> addRestResponse(String id) {
		URI resUrl = getCurrentURI(id);
		return ResponseEntity.created(resUrl).body(RestResponseBuilder.addResponse(id, resUrl.toString()));
	}
	default ResponseEntity<Void> okResponse() {
		return ResponseEntity.ok().build();
	}
	default ResponseEntity<BulkOperationResponse> bulkOperationResponse(int affectedItems) {
		return ResponseEntity.ok(new BulkOperationResponse(affectedItems));
	}
	default ResponseEntity<RestResponse.BulkAddUpdateResponse> bulkAddUpdateResponse(List<?> ids) {
		List<String> strIds = new ArrayList<>();
		ids.forEach(e-> strIds.add(e.toString()));
		return ResponseEntity.ok(new RestResponse.BulkAddUpdateResponse(strIds));
	}
	BaseCrudService<T, ID> getService();
}