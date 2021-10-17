package br.com.tt.projecaopopulacional.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.tt.projecaopopulacional.exception.RestModelHandler;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler
	public ResponseEntity<List<RestModelHandler>> constraintHandle(final ConstraintViolationException ex) {
		List<RestModelHandler> restHandler = new ArrayList<>();
		ex.getConstraintViolations().forEach(e -> {
			restHandler.add(RestModelHandler.builder() //
					.field(e.getConstraintDescriptor().toString()) //
					.message(e.getMessage()) //
					.build());
		});

		return new ResponseEntity<>(restHandler, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RuntimeException.class)
	public RestModelHandler exceptionHandler(final RuntimeException exception) {
		return RestModelHandler.builder().message(exception.getMessage()).build();
	}

}
