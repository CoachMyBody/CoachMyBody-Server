package com.coachmybody.exercise.domain;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class ExerciseToMuscleKey implements Serializable {
	private Exercise exercise;
	private Muscle muscle;
}
