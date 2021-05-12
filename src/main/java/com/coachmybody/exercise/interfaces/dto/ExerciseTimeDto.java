package com.coachmybody.exercise.interfaces.dto;

import com.coachmybody.exercise.domain.ExerciseLabSet;
import com.coachmybody.exercise.domain.ExerciseTime;
import com.coachmybody.exercise.domain.ExerciseTimeSet;
import com.coachmybody.exercise.type.ExerciseTimeType;

import lombok.Getter;

@Getter
public class ExerciseTimeDto {
	ExerciseTimeType type;
	Integer exerciseLab;
	Integer exerciseMinutes;
	Integer exerciseSeconds;
	Integer exerciseSet;

	public ExerciseTimeDto(ExerciseTime exerciseTime) {
		if (exerciseTime instanceof ExerciseTimeSet) {
			this.type = ExerciseTimeType.TIME_SET;
			this.exerciseMinutes = ((ExerciseTimeSet)exerciseTime).getExerciseMinutes();
			this.exerciseSeconds = ((ExerciseTimeSet)exerciseTime).getExerciseSeconds();
			this.exerciseSet = ((ExerciseTimeSet)exerciseTime).getExerciseSet();
		} else if (exerciseTime instanceof ExerciseLabSet) {
			this.type = ExerciseTimeType.LAB_SET;
			this.exerciseLab = ((ExerciseLabSet)exerciseTime).getExerciseLab();
			this.exerciseLab = ((ExerciseLabSet)exerciseTime).getExerciseSet();
		}
	}
}