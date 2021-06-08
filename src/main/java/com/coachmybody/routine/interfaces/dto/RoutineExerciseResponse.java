package com.coachmybody.routine.interfaces.dto;

import com.coachmybody.exercise.domain.Exercise;
import com.coachmybody.routine.domain.RoutineExercise;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "루틴 운동 ")
@Builder
@Value
public class RoutineExerciseResponse {
	@ApiModelProperty(value = "루틴 운동 id", required = true)
	Long id;

	@ApiModelProperty(value = "운동 이름", example = "데드리프트", required = true)
	String name;

	@ApiModelProperty(value = "운동 이미지", required = true)
	String imageUri;

	@ApiModelProperty(value = "횟수", required = true)
	Integer exerciseLab;

	@ApiModelProperty(value = "세트", required = true)
	Integer exerciseSet;

	@JsonIgnore
	Integer priority;

	public static RoutineExerciseResponse of(RoutineExercise routineExercise) {
		Exercise exercise = routineExercise.getExercise();
		String imageUri = exercise.getImageUri();

		return RoutineExerciseResponse.builder()
			.id(exercise.getId())
			.name(exercise.getName())
			.imageUri(imageUri == null ? "" : imageUri)
			.exerciseLab(routineExercise.getExerciseLab())
			.exerciseSet(routineExercise.getExerciseSet())
			.priority(routineExercise.getPriority())
			.build();
	}
}
