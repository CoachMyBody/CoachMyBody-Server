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
import com.coachmybody.routine.interfaces.dto.RoutineExerciseUpdateRequest;
import com.coachmybody.routine.type.UnitType;

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

	private Integer priority;

	@JoinColumn(name = "exercise_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Exercise exercise;

	@JoinColumn(name = "routine_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Routine routine;

	private Float unitValue;

	@Enumerated(value = EnumType.STRING)
	private UnitType unit;

	private Integer exerciseLab;

	private Integer exerciseSet;

	public void updateLabSet(RoutineExerciseUpdateRequest request) {
		this.unitValue = request.getUnitValue();
		this.exerciseLab = request.getExerciseLab();
		this.exerciseSet = request.getExerciseSet();
	}
}
