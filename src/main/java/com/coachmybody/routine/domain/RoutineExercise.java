package com.coachmybody.routine.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.coachmybody.exercise.domain.Exercise;

import lombok.Getter;

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
}
