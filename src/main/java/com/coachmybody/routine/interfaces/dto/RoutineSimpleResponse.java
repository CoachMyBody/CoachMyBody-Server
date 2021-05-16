package com.coachmybody.routine.interfaces.dto;

import java.util.List;

import com.coachmybody.record.domain.RecordRoutine;
import com.coachmybody.record.domain.RecordRoutineExercise;
import com.coachmybody.routine.domain.Routine;
import com.coachmybody.routine.domain.RoutineExercise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "루틴 정보 응답")
@Builder
@Value
public class RoutineSimpleResponse {
	@ApiModelProperty(value = "루틴 id")
	Long id;

	@ApiModelProperty(value = "루틴 제목")
	String title;

	@ApiModelProperty(value = "썸네일 이미지")
	String imageUri;

	@ApiModelProperty(value = "운동 수")
	Integer exerciseCount;

	public static RoutineSimpleResponse of(Routine routine) {
		String imageUri = "기본 이미지";
		int exerciseCount = 0;

		List<RoutineExercise> exercises = routine.getExercises();
		if (exercises.size() > 1) {
			imageUri = exercises.get(0).getExercise().getImageUri();
			exerciseCount = exercises.size();
		}

		return RoutineSimpleResponse.builder()
			.id(routine.getId())
			.title(routine.getTitle())
			.imageUri(imageUri)
			.exerciseCount(exerciseCount)
			.build();
	}

	public static RoutineSimpleResponse of(RecordRoutine routine) {
		String imageUri = "기본 이미지";
		int exerciseCount = 0;

		List<RecordRoutineExercise> exercises = routine.getExercises();
		if (exercises.size() > 1) {
			imageUri = exercises.get(0).getExercise().getImageUri();
			exerciseCount = exercises.size();
		}

		return RoutineSimpleResponse.builder()
			.id(routine.getId())
			.title(routine.getTitle())
			.imageUri(imageUri)
			.exerciseCount(exerciseCount)
			.build();
	}
}
