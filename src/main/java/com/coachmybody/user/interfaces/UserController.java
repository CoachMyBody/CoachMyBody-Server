package com.coachmybody.user.interfaces;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coachmybody.user.application.UserService;
import com.coachmybody.user.interfaces.dto.UserResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {"User"})
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

	private final UserService userService;

	@ApiOperation("전체 유저 정보 조회")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "유저 정보 조회 성공")
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<UserResponse> getUsers() {
		return userService.getUsers();
	}
}
