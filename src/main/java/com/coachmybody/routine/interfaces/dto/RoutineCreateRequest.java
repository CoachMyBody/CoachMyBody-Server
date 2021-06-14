package com.coachmybody.routine.interfaces.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(description = "루틴 생성 요청")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoutineCreateRequest {
	@ApiModelProperty(value = "루틴 제목", required = true)
	@NotNull(message = "The title must not be Null")
	String title;
}