package com.coachmybody.record.interfaces.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "먼슬리 운동 현황 조회 응답")
@Builder
@Value
public class RecordMonthlyResponse {
	@ApiModelProperty(value = "일일 응답 값")
	List<RecordMonthlyOneDayResponse> days;

	@ApiModelProperty(value = "기록 일 수")
	Integer recordDayCount;
}
