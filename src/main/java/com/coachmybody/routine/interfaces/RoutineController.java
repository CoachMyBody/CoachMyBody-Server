package com.coachmybody.routine.interfaces;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coachmybody.common.dto.HeaderDto;
import com.coachmybody.common.dto.ProblemResponse;
import com.coachmybody.routine.application.RoutineService;
import com.coachmybody.routine.interfaces.dto.RoutineCreateRequest;
import com.coachmybody.routine.interfaces.dto.RoutineDetailResponse;
import com.coachmybody.routine.interfaces.dto.RoutineSimpleResponse;
import com.coachmybody.user.application.UserService;
import com.coachmybody.user.domain.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {"Routine"})
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class RoutineController {

	private final RoutineService routineService;
	private final UserService userService;

	@ApiOperation("루틴 생성")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "루틴 생성 성공"),
		@ApiResponse(code = 400, message = "요청 프로퍼티 오류", response = ProblemResponse.class)
	})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/routine")
	public void create(@RequestHeader HttpHeaders headers,
		@RequestBody @Valid RoutineCreateRequest request) {

		HeaderDto headerDto = HeaderDto.of(headers);

		User user = userService.findByToken(headerDto.getToken());

		routineService.create(request.getTitle(), user);
	}

	@ApiOperation("나의 루틴 리스트 조회")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "나의 루틴 리스트 조회 성공")
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/users/routine")
	public List<RoutineSimpleResponse> myRoutine(@RequestHeader HttpHeaders headers) {
		HeaderDto headerDto = HeaderDto.of(headers);

		User user = userService.findByToken(headerDto.getToken());

		return routineService.findMyRoutine(user);
	}

	@ApiOperation("루틴 상세 조회")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "루틴 상세 조회 성공"),
		@ApiResponse(code = 404, message = "존재하지 않는 루틴")
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/routine/{routineId}")
	public ResponseEntity<RoutineDetailResponse> findRoutineById(@PathVariable("routineId") Long routineId) {
		return ResponseEntity.ok(routineService.findRoutineById(routineId));
	}

	// @ApiOperation("내 루틴 편집")
	// @ApiResponses(value = {
	// 	@ApiResponse(code = 200, message = "루틴 편집 성공"),
	// 	@ApiResponse(code = 400, message = "요청 프로퍼티 오류", response = ProblemResponse.class)
	// })
	// @ResponseStatus(HttpStatus.OK)
	// @PostMapping("/routine/{routineId}")
	// public void update(@RequestHeader HttpHeaders headers,
	// 	@PathVariable("routineId") Long routineId,
	// 	@RequestBody @Valid RoutineUpdateRequest request) {
	//
	// }

	@ApiOperation("나의 루틴 삭제")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "루틴 삭제 성공"),
		@ApiResponse(code = 404, message = "존재하지 않는 루틴"),
		@ApiResponse(code = 406, message = "접근할 수 없는 루틴")
	})
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/routine")
	public void delete(@RequestHeader HttpHeaders headers,
		@RequestParam(value = "routineIds") List<Long> routineIds) {
		HeaderDto headerDto = HeaderDto.of(headers);

		User user = userService.findByToken(headerDto.getToken());

		routineService.deleteByIds(routineIds, user.getId());
	}
}