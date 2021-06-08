package com.coachmybody.routine.interfaces.dto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.coachmybody.routine.domain.Routine;
import com.google.common.collect.Lists;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "루틴 상세 정보 응답")
@Builder
@Value
public class RoutineDetailResponse {
	@ApiModelProperty(value = "루틴 id")
	Long id;

	@ApiModelProperty(value = "루틴 제목")
	String title;

	@ApiModelProperty(value = "내용")
	String content;

	@ApiModelProperty(value = "작성자 프로필")
	String profileUri;

	@ApiModelProperty(value = "작성자")
	String nickname;

	@ApiModelProperty(value = "운동 리스트")
	List<RoutineExerciseResponse> exercises;

	@ApiModelProperty(value = "태그")
	List<String> tags;

	@ApiModelProperty(value = "북마크 여부")
	boolean isBookmarked;

	public static RoutineDetailResponse of(Routine routine, boolean isBookmarked) {
		return RoutineDetailResponse.builder()
			.id(routine.getId())
			.title(routine.getTitle())
			.content("")
			.nickname(routine.getUser().getNickname())
			.profileUri("")
			.exercises(routine.getExercises().stream()
				.map(RoutineExerciseResponse::of)
				.sorted(Comparator.comparing(RoutineExerciseResponse::getPriority))
				.collect(Collectors.toList()))
			.tags(Lists.newArrayList())
			.isBookmarked(isBookmarked)
			.build();
	}
}