package com.coachmybody.record.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.coachmybody.exercise.domain.Exercise;
import com.coachmybody.exercise.type.ExerciseRecordType;
import com.coachmybody.routine.domain.RoutineExercise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class RecordRoutineExercise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "exercise_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Exercise exercise;

	@JoinColumn(name = "record_routine_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private RecordRoutine recordRoutine;

	@Enumerated(value = EnumType.STRING)
	private ExerciseRecordType recordType;

	private Integer priority;

	private Integer exerciseMinutes;

	private Integer exerciseSeconds;

	private Integer exerciseLab;

	private Integer exerciseSet;

	private Float exerciseWeight;

	private Float exerciseDistance;

	public static RecordRoutineExercise of(RoutineExercise exercise, RecordRoutine recordRoutine) {
		return RecordRoutineExercise.builder()
			.exercise(exercise.getExercise())
			.recordRoutine(recordRoutine)
			.recordType(exercise.getRecordType())
			.priority(exercise.getPriority())
			.exerciseMinutes(exercise.getExerciseMinutes())
			.exerciseSeconds(exercise.getExerciseSeconds())
			.exerciseLab(exercise.getExerciseLab())
			.exerciseSet(exercise.getExerciseSet())
			.exerciseWeight(exercise.getExerciseWeight())
			.exerciseDistance(exercise.getExerciseDistance())
			.build();
	}
}
