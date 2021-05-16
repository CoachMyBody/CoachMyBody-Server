package com.coachmybody.routine.interfaces.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "루틴 운동 편집 요청")
@Getter
public class RoutineExerciseUpdateRequest {
	@ApiModelProperty(value = "횟수")
	private Integer exerciseLab;

	@ApiModelProperty(value = "세트")
	private Integer exerciseSet;

	@ApiModelProperty(value = "운동 시간(분)")
	private Integer exerciseMinutes;

	@ApiModelProperty(value = "운동 시간(초)")
	private Integer exerciseSeconds;

	@ApiModelProperty(value = "중량")
	private Float exerciseWeight;

	@ApiModelProperty(value = "거리")
	private Float exerciseDistance;
}