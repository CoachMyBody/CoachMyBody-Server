package com.coachmybody.record.interfaces.dto;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "운동 기록 요청")
@Getter
public class RecordCreateRequest {
	@ApiModelProperty(value = "루틴 id", required = true)
	@NotNull(message = "The routineId must be not Null")
	Long routineId;

	@ApiModelProperty(value = "날짜", required = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	String date;

	@ApiModelProperty(value = "운동 시간 (시간)", required = true)
	@NotNull(message = "The hours must be not Null")
	Integer hours;

	@ApiModelProperty(value = "운동 시간 (분)", required = true)
	@NotNull(message = "The minutes must be not Null")
	Integer minutes;

	@ApiModelProperty(value = "셀프 피드백")
	String feedbackBySelf;

	@ApiModelProperty(value = "오늘의 인바디")
	InbodyCreateRequest inbody;

	@ApiModelProperty(value = "오늘의 눈바디")
	NunbodyCreateRequest nunbody;

}
