package com.em.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.em.dto.GlobalUserManagementException;

/**
 * This class plays as a global exception handler for exception from all
 * services. custom http response code should be implemented here
 * 
 * @author Thang Le
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { GlobalUserManagementException.class })
    // For GlobalUserManagementException
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {

	if (ex instanceof GlobalUserManagementException) {
	    String bodyOfResponse = ((GlobalUserManagementException) ex).getMessage();

	    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
		    request);
	}

	return new ResponseEntity<>(new GlobalResponse("other exceptions", 1000), HttpStatus.NOT_IMPLEMENTED);

    }
}
