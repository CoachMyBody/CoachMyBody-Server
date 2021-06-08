package com.coachmybody.exercise.interfaces.dto;

import java.util.List;

import com.coachmybody.common.util.StringUtils;
import com.coachmybody.exercise.domain.Exercise;
import com.coachmybody.exercise.domain.ExerciseRecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "운동 정보 응답")
@Builder
@Value
public class ExerciseSimpleResponse {
	@ApiModelProperty(value = "운동 id", required = true)
	Long id;

	@ApiModelProperty(value = "운동 이름", example = "데드리프트", required = true)
	String name;

	@ApiModelProperty(value = "운동 이미지", required = true)
	String imageUri;

	@ApiModelProperty(value = "태그", required = true)
	List<String> tags;

	@ApiModelProperty(value = "세트", required = true)
	String sets;

	public static ExerciseSimpleResponse of(Exercise exercise) {
		String imageUri = exercise.getImageUri() == null ? "" : exercise.getImageUri();
		String tag1 = "#" + exercise.getCategory().getName();
		String tag2 = "#" + exercise.getBodyPart().getName();

		ExerciseRecord exerciseRecord = exercise.getExerciseRecord();
		String sets = StringUtils.getExerciseSets(exerciseRecord);

		return ExerciseSimpleResponse.builder()
			.id(exercise.getId())
			.name(exercise.getName())
			.imageUri(imageUri)
			.tags(List.of(tag1, tag2))
			.sets(sets)
			.build();
	}
}
