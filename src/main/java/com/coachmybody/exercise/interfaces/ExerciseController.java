package com.coachmybody.exercise.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coachmybody.common.dto.PageResponse;
import com.coachmybody.common.dto.ProblemResponse;
import com.coachmybody.exercise.application.ExerciseService;
import com.coachmybody.exercise.domain.Exercise;
import com.coachmybody.exercise.interfaces.dto.ExerciseDetailResponse;
import com.coachmybody.exercise.interfaces.dto.ExerciseFilterRequest;
import com.coachmybody.exercise.interfaces.dto.ExerciseSimpleResponse;
import com.coachmybody.exercise.type.BodyPartType;
import com.coachmybody.exercise.type.ExerciseCategoryType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {"Exercise"})
@RequiredArgsConstructor
@RequestMapping("/api/v1/exercises")
@RestController
public class ExerciseController {

	private final ExerciseService exerciseService;

	@ApiOperation("운동 리스트 조회")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "운동 리스트 조회 성공")
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public PageResponse<ExerciseSimpleResponse> findExercise(@RequestParam ExerciseCategoryType category,
		@RequestParam(required = false, defaultValue = "NONE") BodyPartType bodyPart,
		@RequestParam(required = false, defaultValue = "0") int page,
		@RequestParam(required = false, defaultValue = "20") int size) {

		PageRequest pageRequest = PageRequest.of(page, size);

		ExerciseFilterRequest filter = new ExerciseFilterRequest(category, bodyPart);

		Page<Exercise> exercises = exerciseService.findExercises(filter, pageRequest);

		return PageResponse.of(exercises, ExerciseSimpleResponse::of);

	}

	@ApiOperation("운동 상세 정보 조회")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "운동 상세 정보 조회 성공"),
		@ApiResponse(code = 404, message = "존재하지 않는 운동", response = ProblemResponse.class)
	})
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{exerciseId}")
	public ExerciseDetailResponse findExerciseById(@PathVariable Long exerciseId) {
		return exerciseService.findExerciseById(exerciseId);
	}
}