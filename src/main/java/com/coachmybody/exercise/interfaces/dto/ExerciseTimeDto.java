package com.coachmybody.exercise.interfaces.dto;

import com.coachmybody.exercise.domain.ExerciseLabSet;
import com.coachmybody.exercise.domain.ExerciseRecord;
import com.coachmybody.exercise.domain.ExerciseTimeSet;
import com.coachmybody.exercise.type.ExerciseRecordType;

import lombok.Getter;

@Getter
public class ExerciseTimeDto {
	ExerciseRecordType type;
	Integer exerciseLab;
	Integer exerciseMinutes;
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