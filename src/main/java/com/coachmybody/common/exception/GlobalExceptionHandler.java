package com.coachmybody.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.coachmybody.common.dto.ProblemResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidAccessTokenException.class)
	private ResponseEntity<ProblemResponse> invalidAccessTokenExceptionHandler() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			.body(new ProblemResponse("Unauthorized", 401, "INVALID_ACCESS_TOKEN"));
	}

	@ExceptionHandler(InvalidRefreshTokenException.class)
	private ResponseEntity<ProblemResponse> invalidRefreshTokenExceptionHandler() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			.body(new ProblemResponse("Invalid Refresh Token", 400, "REFRESH_TOKEN"));
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

	@ExceptionHandler(NotAcceptableException.class)
	private ResponseEntity<ProblemResponse> notAcceptableExceptionHandler() {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
			.body(new ProblemResponse("Not Acceptable", 406, "NOT_ACCEPTABLE"));
	}
}
