package com.coachmybody.routine.interfaces.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "루틴 운동 순서 편집 요청")
@Getter
public class RoutineExerciseOrderRequest {
	@ApiModelProperty(value = "루틴 운동 id 리스트", example = "[1, 2, 3]", required = true)
	@NotEmpty
	List<Long> routineExerciseIds;
}
