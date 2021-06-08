package com.coachmybody.record.interfaces.dto;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "먼슬리 기록의 개별 하루 응답")
@Builder
@Value
public class RecordMonthlyOneDayResponse {

	@ApiModelProperty(value = "날짜", example = "5월 1일")
	LocalDate date;

	@ApiModelProperty(value = "요일", example = "토요일")
	String dayOfWeek;

	@ApiModelProperty(value = "운동 기록 개수")
	Integer recordCount;
}
