package com.coachmybody.routine.interfaces.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "루틴 수정 요청")
@Getter
public class RoutineUpdateRequest {
	@ApiModelProperty()
	String title;

	

}