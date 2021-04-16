package com.coachmybody.exercise.domain;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class ExerciseToBodyPartSubKey implements Serializable {
	private Exercise exercise;
	private BodyPartSub bodyPartSub;
}
