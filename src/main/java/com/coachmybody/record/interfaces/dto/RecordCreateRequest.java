package com.coachmybody.record.interfaces.dto;

import javax.validation.constraints.NotNull;

import com.coachmybody.routine.domain.Routine;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "운동 기록 요청")
@Getter
public class RecordCreateRequest {
	@ApiModelProperty(value = "루틴 id", required = true)
	@NotNull(message = "The routineId must be not Null")
	Long routineId;

	@ApiModelProperty(value = "셀프 피드백")
	String feedbackBySelf;

	@ApiModelProperty(value = "운동 시간 (시간)", required = true)
	@NotNull(message = "The hours must be not Null")
	Integer hours;

	@ApiModelProperty(value = "운동 시간 (분)", required = true)
	@NotNull(message = "The minutes must be not Null")
	Integer minutes;

	@JsonIgnore
	@Setter
	Routine routine;

	// 인바디 기록
	// 눈바디 기록
}
