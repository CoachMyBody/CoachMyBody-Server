package com.coachmybody.record.interfaces.dto;

import java.util.List;

import com.coachmybody.routine.interfaces.dto.RoutineSimpleResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@ApiModel(description = "데일리 운동 현황 조회 응답")
@Value
public class RecordMonthlyResponse {
	@ApiModelProperty(value = "루틴 리스트", example = "[a, b]")
	List<RoutineSimpleResponse> routines;

}
