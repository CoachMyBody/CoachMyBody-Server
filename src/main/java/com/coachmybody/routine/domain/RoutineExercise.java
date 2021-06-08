package com.coachmybody.routine.domain;

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
import com.coachmybody.routine.interfaces.dto.RoutineExerciseUpdateRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class RoutineExercise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "exercise_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Exercise exercise;

	@JoinColumn(name = "routine_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Routine routine;

	private Integer priority;

	@Enumerated(value = EnumType.STRING)
	private ExerciseRecordType recordType;

	private Integer exerciseMinutes;

	private Integer exerciseSeconds;

	private Integer exerciseLab;

	private Integer exerciseSet;

	private Float exerciseWeight;

	private Float exerciseDistance;

	public void updateLabSet(RoutineExerciseUpdateRequest request) {
		this.exerciseMinutes = request.getExerciseMinutes();
		this.exerciseSeconds = request.getExerciseSeconds();
		this.exerciseLab = request.getExerciseLab();
		this.exerciseSet = request.getExerciseSet();
		this.exerciseWeight = request.getExerciseWeight();
		this.exerciseDistance = request.getExerciseDistance();
	}

	public void updatePriority(int priority) {
		this.priority = priority;
	}
}
