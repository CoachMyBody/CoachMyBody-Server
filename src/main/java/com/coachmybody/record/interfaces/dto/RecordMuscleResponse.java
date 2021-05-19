package com.coachmybody.record.interfaces.dto;

import com.coachmybody.exercise.type.MuscleType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "운동 근육")
@Builder
@Value
public class RecordMuscleResponse {
	@ApiModelProperty(value = "근육")
	MuscleType muscle;

	@ApiModelProperty(value = "근육 운동 횟수")
	Integer count;
}
