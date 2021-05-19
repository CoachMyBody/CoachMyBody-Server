package com.coachmybody.record.interfaces.dto;

import java.util.List;

import com.coachmybody.record.domain.Record;
import com.coachmybody.record.domain.RecordRoutine;
import com.coachmybody.record.domain.RecordRoutineExercise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "루틴 정보 응답")
@Builder
@Value
public class RecordRoutineSimpleResponse {
	@ApiModelProperty(value = "루틴 id")
	Long id;

	@ApiModelProperty(value = "루틴 제목")
	String title;

	@ApiModelProperty(value = "썸네일 이미지")
	String imageUri;

	@ApiModelProperty(value = "운동 수")
	Integer exerciseCount;

	@ApiModelProperty(value = "강사 피드백 여부")
	Boolean hasFeedbackByTrainer;

	@ApiModelProperty(value = "운동 시간 (시간)")
	Integer hours;

	@ApiModelProperty(value = "운동 시간 (분)")
	Integer minutes;

	public static RecordRoutineSimpleResponse of(Record record) {
		RecordRoutine recordRoutine = record.getRecordRoutine();

		String imageUri = "기본 이미지";
		int exerciseCount = 0;

		List<RecordRoutineExercise> exercises = recordRoutine.getExercises();
		if (exercises.size() > 1) {
			imageUri = exercises.get(0).getExercise().getImageUri();
			exerciseCount = exercises.size();
		}

		String feedback = record.getFeedbackByTrainer();
		boolean hasFeedbackByTrainer = false;
		if (feedback != null && !feedback.equals("")) {
			hasFeedbackByTrainer = true;
		}

		return RecordRoutineSimpleResponse.builder()
			.id(recordRoutine.getId())
			.title(recordRoutine.getTitle())
			.imageUri(imageUri)
			.exerciseCount(exerciseCount)
			.hasFeedbackByTrainer(hasFeedbackByTrainer)
			.hours(record.getHours())
			.minutes(record.getMinutes())
			.build();
	}
}
