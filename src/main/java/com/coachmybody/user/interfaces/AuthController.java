package com.coachmybody.user.interfaces;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coachmybody.common.exception.ProblemResponse;
import com.coachmybody.user.interfaces.dto.AuthResponse;
import com.coachmybody.user.interfaces.dto.LoginRequest;
import com.coachmybody.user.interfaces.dto.RefreshRequest;
import com.coachmybody.user.interfaces.dto.RegisterRequest;
import com.coachmybody.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {"Auth"})
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

	private final UserService userService;

	@ApiOperation("회원 가입")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "회원 가입 성공"),
		@ApiResponse(code = 400, message = "요청 프로퍼티 오류", response = ProblemResponse.class),
		@ApiResponse(code = 409, message = "이미 존재하는 회원", response = ProblemResponse.class)
	})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/register")
	public void register(@RequestBody @Valid RegisterRequest registerRequest) {
		userService.register(registerRequest);
	}


	@ApiOperation("로그인")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "로그인 성공"),
		@ApiResponse(code = 400, message = "존재하지 않는 회원", response = ProblemResponse.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/login")
	public AuthResponse login(@RequestBody @Valid LoginRequest loginRequest) {
		return userService.login(loginRequest.getSocialId());
	}

	@ApiOperation("토큰 갱신")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "토큰 갱신 성공"),
		@ApiResponse(code = 400, message = "올바르지 않은 리프레시 토큰", response = ProblemResponse.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/refresh")
	public AuthResponse refresh(@RequestBody @Valid RefreshRequest refreshRequest) {
		return userService.refresh(refreshRequest.getRefreshToken());
	}
}
