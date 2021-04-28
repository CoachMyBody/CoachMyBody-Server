package com.coachmybody.routine.interfaces.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "루틴 삭제 요청")
@Getter
public class RoutineDeleteRequest {
	@ApiModelProperty(value = "루틴 id 리스트")
	@NotEmpty
	List<Long> routineIds;
}
