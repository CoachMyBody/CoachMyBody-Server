package com.coachmybody.exercise.interfaces.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.coachmybody.exercise.domain.Exercise;
import com.coachmybody.exercise.domain.ExerciseToMuscle;
import com.coachmybody.exercise.domain.Muscle;
import com.coachmybody.exercise.type.BodyPartSubType;
import com.coachmybody.exercise.type.BodyPartType;
import com.coachmybody.exercise.type.ExerciseCategoryType;
import com.coachmybody.exercise.type.MuscleType;

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

	@ApiModelProperty(value = "전체 그룹", example = "전신", required = true)
	BodyPartType bodyPart;

	@ApiModelProperty(value = "상세 그룹", example = "복부, 다리", required = true)
	List<BodyPartSubType> bodyPartSubs;

	@ApiModelProperty(value = "운동 시간", required = true)
	ExerciseTimeDto exerciseTime;

	@ApiModelProperty(value = "적용 근육", example = "[TRAPEZIUS, LATISSIMUS_DORSI]", required = true)
	List<MuscleType> muscles;

	@ApiModelProperty(value = "연관 운동", example = "[DEAD_LIFT, SHOULDER_PRESS]")
	List<ExerciseSimpleResponse> relatedExercises;

	public static ExerciseDetailResponse of(Exercise exercise) {
		List<Muscle> muscleList = exercise.getExerciseToMuscles()
			.stream()
			.map(ExerciseToMuscle::getMuscle)
			.collect(Collectors.toList());

		List<BodyPartSubType> bodyPartSubs = muscleList.stream()
			.map(muscle -> muscle.getBodyPartSub().getName())
			.distinct()
			.collect(Collectors.toList());

		List<MuscleType> muscles = muscleList.stream()
			.map(Muscle::getName)
			.collect(Collectors.toList());

		return ExerciseDetailResponse.builder()
			.id(exercise.getId())
			.name(exercise.getName())
			.imageUri(exercise.getImageUri())
			.category(exercise.getCategory())
			.bodyPart(exercise.getBodyPart())
			.bodyPartSubs(bodyPartSubs)
			.exerciseTime(new ExerciseTimeDto(exercise.getExerciseTime()))
			.muscles(muscles)
			.build();
	}
}
