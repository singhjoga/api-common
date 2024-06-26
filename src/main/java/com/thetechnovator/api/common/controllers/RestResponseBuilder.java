package com.thetechnovator.api.common.controllers;

import java.util.List;

import com.thetechnovator.api.common.controllers.RestResponse.AddResponse;
import com.thetechnovator.api.common.controllers.RestResponse.ErrorResponse;
import com.thetechnovator.api.common.controllers.RestResponse.ValidationError;

public class RestResponseBuilder{
	public static AddResponse addResponse(String id, String location) {
		AddResponse result = new AddResponse(id, location);
		return result;
	}

	public static ErrorResponse errorResponse(String message, String errorCode) {
		return errorResponse(message, errorCode,null);
	}	
	public static ErrorResponse errorResponse(String message, String errorCode,  List<ValidationError> errors) {
		return new ErrorResponse(message, errorCode, errors);
	}

}
