package com.coachmybody.routine.interfaces.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "루틴 운동 편집 요청")
@Getter
public class RoutineExerciseUpdateRequest {
	@ApiModelProperty(value = "중량")
	private Float unitValue;

	@ApiModelProperty(value = "횟수")
	private Integer exerciseLab;

	@ApiModelProperty(value = "세트")
	private Integer exerciseSet;
}