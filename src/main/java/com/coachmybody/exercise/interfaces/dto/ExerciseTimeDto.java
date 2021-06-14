package com.coachmybody.exercise.interfaces.dto;

import com.coachmybody.exercise.domain.ExerciseLabSet;
import com.coachmybody.exercise.domain.ExerciseRecord;
import com.coachmybody.exercise.domain.ExerciseTimeSet;
import com.coachmybody.exercise.type.ExerciseRecordType;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExerciseTimeDto {
	ExerciseRecordType type;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	Integer exerciseLab;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	Integer exerciseMinutes;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	Integer exerciseSeconds;
	Integer exerciseSet;

	public ExerciseTimeDto(ExerciseRecord exerciseRecord) {
		if (exerciseRecord instanceof ExerciseTimeSet) {
			this.type = ExerciseRecordType.TIME_SET;
			this.exerciseMinutes = ((ExerciseTimeSet)exerciseRecord).getExerciseMinutes();
			this.exerciseSeconds = ((ExerciseTimeSet)exerciseRecord).getExerciseSeconds();
			this.exerciseSet = ((ExerciseTimeSet)exerciseRecord).getExerciseSet();
		} else if (exerciseRecord instanceof ExerciseLabSet) {
			this.type = ExerciseRecordType.LAB_SET;
			this.exerciseLab = ((ExerciseLabSet)exerciseRecord).getExerciseLab();
			this.exerciseSet = ((ExerciseLabSet)exerciseRecord).getExerciseSet();
		}
	}
}