package com.coachmybody.record.interfaces;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.multipart.MultipartFile;

import com.coachmybody.common.dto.HeaderDto;
import com.coachmybody.common.dto.PageResponse;
import com.coachmybody.common.dto.ProblemResponse;
import com.coachmybody.record.application.RecordService;
import com.coachmybody.record.interfaces.dto.InbodyCreateRequest;
import com.coachmybody.record.interfaces.dto.NunbodyCreateRequest;
import com.coachmybody.record.interfaces.dto.NunbodyResponse;
import com.coachmybody.record.interfaces.dto.RecordCreateRequest;
import com.coachmybody.record.interfaces.dto.RecordDailyResponse;
import com.coachmybody.record.interfaces.dto.RecordMonthlyResponse;
import com.coachmybody.record.interfaces.type.NunbodySortType;
import com.coachmybody.record.type.NunbodyCompareType;
import com.coachmybody.user.application.UserService;
import com.coachmybody.user.domain.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {"Record"})
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class RecordController {
	private final RecordService recordService;
	private final UserService userService;

	@ApiOperation("운동 기록")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "운동 기록 성공"),
		@ApiResponse(code = 400, message = "요청 프로퍼티 오류", response = ProblemResponse.class)
	})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/records")
	public void create(@RequestHeader HttpHeaders headers,
		@RequestBody @Valid RecordCreateRequest request) {
		HeaderDto header = HeaderDto.of(headers);

		User user = userService.findByToken(header.getToken());

		recordService.create(request, user);
	}

	@ApiOperation("운동 현황 데일리 조회")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "운동 현황 데일리 조회 성공"),
		@ApiResponse(code = 400, message = "요청 프로퍼티 오류", response = ProblemResponse.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/records/daily")
	public ResponseEntity<RecordDailyResponse> getDailyRecord(@RequestHeader HttpHeaders headers,
		@RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		HeaderDto header = HeaderDto.of(headers);
		User user = userService.findByToken(header.getToken());

		if (date == null) {
			date = LocalDate.now();
		}

		return ResponseEntity.ok(recordService.getDailyRecord(date, user));
	}

	@ApiOperation("운동 현황 먼슬리 조회")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "운동 현황 먼슬리 조회 성공"),
		@ApiResponse(code = 400, message = "요청 프로퍼티 오류", response = ProblemResponse.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/records/monthly")
	public ResponseEntity<RecordMonthlyResponse> getMonthlyRecord(@RequestHeader HttpHeaders headers,
		@RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		HeaderDto header = HeaderDto.of(headers);
		User user = userService.findByToken(header.getToken());

		if (date == null) {
			date = LocalDate.now();
		}

		return ResponseEntity.ok(recordService.getMonthlyRecord(date, user));
	}

	@ApiOperation("인바디 기록")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "인바디 기록 성공"),
		@ApiResponse(code = 400, message = "요청 프로퍼티 오류", response = ProblemResponse.class),
		@ApiResponse(code = 409, message = "이미 존재하는 인바디", response = ProblemResponse.class)
	})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/inbody")
	public void createInbody(@RequestHeader HttpHeaders headers,
		@RequestBody @Valid InbodyCreateRequest request) {
		HeaderDto header = HeaderDto.of(headers);
		User user = userService.findByToken(header.getToken());

		recordService.createInbody(user, request);
	}

	@ApiOperation("눈바디 기록")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "눈바디 기록 성공"),
		@ApiResponse(code = 400, message = "요청 프로퍼티 오류", response = ProblemResponse.class),
		@ApiResponse(code = 409, message = "이미 존재하는 눈바디", response = ProblemResponse.class)
	})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/nunbody")
	public void createNunbody(@RequestHeader HttpHeaders headers,
		@RequestBody @Valid NunbodyCreateRequest request) {
		HeaderDto header = HeaderDto.of(headers);
		User user = userService.findByToken(header.getToken());

		recordService.createNunbody(user, request);
	}

	@ApiOperation("눈바디 이미지 업로드")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "눈바디 이미지 업로드 성공")
	})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/nunbody/image")
	public ResponseEntity<String> uploadNunbodyImage(@RequestParam(name = "image") MultipartFile image) {
		return ResponseEntity.ok(recordService.uploadNunbodyImage(image));
	}

	@ApiOperation("마이 눈바디 조회")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "마이 눈바디 조회 성공")
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/users/nunbody")
	public PageResponse<NunbodyResponse> getNunbody(@RequestHeader HttpHeaders headers,
		@RequestParam(required = false, defaultValue = "0") int page,
		@RequestParam(required = false, defaultValue = "20") int size,
		@RequestParam(required = false, defaultValue = "DATE_DESC") NunbodySortType sort) {

		HeaderDto header = HeaderDto.of(headers);
		User user = userService.findByToken(header.getToken());

		PageRequest pageRequest = PageRequest.of(page, size);

		return recordService.getNunbody(user, pageRequest, sort);
	}

	@ApiOperation("눈바디 비포 애프터 등록 (+ 수정)")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "눈바디 비포 애프터 등록 성공"),
		@ApiResponse(code = 404, message = "존재하지 않는 눈바디")
	})
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("users/nunbody/{nunbodyId}/{type}")
	public void createNunbodyCompare(@RequestHeader HttpHeaders headers,
		@PathVariable("nunbodyId") Long nunbodyId,
		@PathVariable("type") NunbodyCompareType type) {

		HeaderDto header = HeaderDto.of(headers);
		User user = userService.findByToken(header.getToken());

		recordService.createNunbodyCompare(user, nunbodyId, type);
	}

	@ApiOperation("눈바디 비포 애프터 삭제")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "눈바디 비포 애프터 삭제 성공"),
		@ApiResponse(code = 404, message = "존재하지 않는 눈바디 비포 or 애프터")
	})
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/users/nunbody/{type}")
	public void deleteNunbodyCompare(@RequestHeader HttpHeaders headers,
		@PathVariable("type") NunbodyCompareType type) {

		HeaderDto header = HeaderDto.of(headers);
		User user = userService.findByToken(header.getToken());

		recordService.deleteNunbodyCompare(user, type);
	}
}