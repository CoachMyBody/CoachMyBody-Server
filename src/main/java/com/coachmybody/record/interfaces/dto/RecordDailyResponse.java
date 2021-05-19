package com.coachmybody.record.interfaces.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.coachmybody.exercise.domain.ExerciseToMuscle;
import com.coachmybody.exercise.type.MuscleType;
import com.coachmybody.record.domain.Record;
import com.coachmybody.record.domain.RecordRoutineExercise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "데일리 운동 현황 조회 응답")
@Builder
@Value
public class RecordDailyResponse {
	@ApiModelProperty(value = "루틴 리스트", example = "[a, b]")
	List<RecordRoutineSimpleResponse> routines;

	@ApiModelProperty(value = "운동한 근육 리스트", example = "[a, b]")
	List<RecordMuscleResponse> muscles;

	public static RecordDailyResponse of(List<Record> records) {
		Map<MuscleType, Integer> recordMuscle = getRecordMuscles(records);

		List<RecordMuscleResponse> muscles = recordMuscle.keySet()
			.stream()
			.map(muscleType -> RecordMuscleResponse.builder()
				.muscle(muscleType)
				.count(recordMuscle.get(muscleType))
				.build())
			.collect(Collectors.toList());

		return RecordDailyResponse.builder()
			.routines(records.stream()
				.map(RecordRoutineSimpleResponse::of)
				.collect(Collectors.toList()))
			.muscles(muscles)
			.build();
	}

	private static Map<MuscleType, Integer> getRecordMuscles(List<Record> records) {
		Map<MuscleType, Integer> recordMuscle = new HashMap<>();

		records.forEach(record -> {
			List<RecordRoutineExercise> exercises = record.getRecordRoutine().getExercises();
			exercises.forEach(recordRoutineExercise -> {
				Set<ExerciseToMuscle> exerciseToMuscles = recordRoutineExercise.getExercise().getExerciseToMuscles();
				exerciseToMuscles.forEach(exerciseToMuscle -> {
					MuscleType muscle = exerciseToMuscle.getMuscle().getName();
					try {
						recordMuscle.put(muscle, recordMuscle.get(muscle) + 1);
					} catch (NullPointerException e) {
						recordMuscle.put(muscle, 0);
					}
				});
			});
		});

		return recordMuscle;
	}
}
