package com.coachmybody.record.interfaces.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.coachmybody.record.domain.Record;
import com.coachmybody.routine.interfaces.dto.RoutineSimpleResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "데일리 운동 현황 조회 응답")
@Builder
@Value
public class RecordDailyResponse {
	@ApiModelProperty(value = "루틴 리스트", example = "[a, b]")
	List<RoutineSimpleResponse> routines;

	public static RecordDailyResponse of(List<Record> records) {
		return RecordDailyResponse.builder()
			.routines(records.stream()
				.map(Record::getRecordRoutine)
				.map(RoutineSimpleResponse::of)
				.collect(Collectors.toList()))
			.build();
	}
}
