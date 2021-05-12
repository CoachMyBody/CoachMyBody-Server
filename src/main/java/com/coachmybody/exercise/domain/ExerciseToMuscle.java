package com.coachmybody.exercise.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Getter
@IdClass(ExerciseToMuscleKey.class)
@Entity
public class ExerciseToMuscle {
	@Id
	@JoinColumn(name = "exercise_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Exercise exercise;

	@Id
	@JoinColumn(name = "muscle_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Muscle muscle;
}
