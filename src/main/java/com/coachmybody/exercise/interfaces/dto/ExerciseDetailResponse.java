package com.coachmybody.exercise.interfaces.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.coachmybody.exercise.domain.Exercise;
import com.coachmybody.exercise.domain.ExerciseToBodyPartSub;
import com.coachmybody.exercise.type.BodyPartSubType;
import com.coachmybody.exercise.type.BodyPartType;
import com.coachmybody.exercise.type.ExerciseCategoryType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "운동 상세 조회 응답")
@Builder
@Value
public class ExerciseDetailResponse {
	@ApiModelProperty(value = "id", required = true)
	Long id;

	@ApiModelProperty(value = "운동 이름", example = "데드리프트", required = true)
	String name;

	@ApiModelProperty(value = "이미지", required = true)
	String imageUri;

	@ApiModelProperty(value = "운동 종목", example = "헬스", required = true)
	ExerciseCategoryType category;

	@ApiModelProperty(value = "운동 부위", example = "전신", required = true)
	BodyPartType bodyPart;

	@ApiModelProperty(value = "운동 상세 부위", example = "대퇴사두근", required = true)
	List<BodyPartSubType> bodyPartSubList;

	public static ExerciseDetailResponse of(Exercise exercise) {
		List<BodyPartSubType> bodyPartSubList = exercise.getExerciseToBodyPartSubList().stream()
			.map(exerciseToBodyPartSub -> exerciseToBodyPartSub.getBodyPartSub().getName())
			.collect(Collectors.toList());

		return ExerciseDetailResponse.builder()
			.id(exercise.getId())
			.name(exercise.getName())
			.imageUri(exercise.getImageUri())
			.category(exercise.getCategory())
			.bodyPart(exercise.getBodyPart())
			.bodyPartSubList(bodyPartSubList)
			.build();
	}
}
