package com.coachmybody.routine.interfaces.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.coachmybody.exercise.interfaces.dto.ExerciseSimpleResponse;
import com.coachmybody.routine.domain.Routine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "루틴 상세 정보 응답")
@Builder
@Value
public class RoutineDetailResponse {
	@ApiModelProperty(value = "루틴 id")
	Long id;

	@ApiModelProperty(value = "루틴 제목")
	String title;

	@ApiModelProperty(value = "운동 리스트")
	List<ExerciseSimpleResponse> exercises;

	public static RoutineDetailResponse of(Routine routine) {
		return RoutineDetailResponse.builder()
			.id(routine.getId())
			.title(routine.getTitle())
			.exercises(routine.getExercises().stream()
				.map(routineExercise -> ExerciseSimpleResponse.of(routineExercise.getExercise()))
				.collect(Collectors.toList()))
			.build();
	}
}
