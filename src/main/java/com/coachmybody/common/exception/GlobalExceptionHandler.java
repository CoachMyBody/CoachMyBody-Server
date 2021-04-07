package com.coachmybody.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidAccessTokenException.class)
	private ResponseEntity<ProblemResponse> invalidAccessTokenExceptionHandler() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			.body(new ProblemResponse("Unauthorized", 401, "INVALID_ACCESS_TOKEN"));
	}

	@ExceptionHandler(DuplicatedEntityException.class)
	private ResponseEntity<ProblemResponse> duplicatedEntityExceptionHandler() {
		return ResponseEntity.status(HttpStatus.CONFLICT)
			.body(new ProblemResponse("Duplicated Entity", 409, "DUPLICATED_ENTITY"));
	}

	@ExceptionHandler(NotFoundEntityException.class)
	private ResponseEntity<ProblemResponse> notFoundEntityExceptionHandler() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(new ProblemResponse("Not Found Entity", 404, "NOT_FOUND_ENTITY"));
	}
}
