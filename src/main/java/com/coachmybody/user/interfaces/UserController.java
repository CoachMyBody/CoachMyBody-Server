package com.coachmybody.user.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coachmybody.common.dto.HeaderDto;
import com.coachmybody.common.dto.PageResponse;
import com.coachmybody.routine.domain.Routine;
import com.coachmybody.routine.interfaces.dto.RoutineSimpleResponse;
import com.coachmybody.user.application.UserService;
import com.coachmybody.user.domain.User;
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

	@ApiOperation("북마크한 루틴 조회")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "북마크한 루틴 조회 성공")
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/routines/bookmark")
	public PageResponse<RoutineSimpleResponse> bookmarkRoutines(@RequestHeader HttpHeaders headers,
		@RequestParam(required = false, defaultValue = "0") int page,
		@RequestParam(required = false, defaultValue = "20") int size) {
		HeaderDto header = HeaderDto.of(headers);
		User user = userService.findByToken(header.getToken());

		PageRequest pageRequest = PageRequest.of(page, size);

		Page<Routine> routines = userService.findBookmarkRoutines(user, pageRequest);

		return PageResponse.of(routines, RoutineSimpleResponse::of);
	}
}
