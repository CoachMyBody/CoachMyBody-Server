package com.coachmybody.routine.interfaces.dto;

import com.coachmybody.exercise.domain.Exercise;
import com.coachmybody.routine.domain.RoutineExercise;
import com.coachmybody.routine.type.UnitType;

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

	@ApiModelProperty(value = "단위", example = "KG")
	UnitType unit;

	@ApiModelProperty(value = "단위 값", example = "45.5")
	Float unitValue;

	public static RoutineExerciseResponse of(RoutineExercise routineExercise) {
		Exercise exercise = routineExercise.getExercise();

		return RoutineExerciseResponse.builder()
			.id(routineExercise.getId())
			.name(exercise.getName())
			.imageUri(exercise.getImageUri())
			.exerciseLab(routineExercise.getExerciseLab())
			.exerciseSet(routineExercise.getExerciseSet())
			.unit(routineExercise.getUnit())
			.unitValue(routineExercise.getUnitValue())
			.build();
	}
}
