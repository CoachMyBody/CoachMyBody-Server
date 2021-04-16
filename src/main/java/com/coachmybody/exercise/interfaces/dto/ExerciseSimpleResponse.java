package com.coachmybody.exercise.interfaces.dto;

import com.coachmybody.exercise.domain.Exercise;

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

	public static ExerciseSimpleResponse of(Exercise exercise) {
		return ExerciseSimpleResponse.builder()
			.id(exercise.getId())
			.name(exercise.getName())
			.imageUri(exercise.getImageUri())
			.build();
	}
}
