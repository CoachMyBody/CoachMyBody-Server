package com.coachmybody.exercise.type;

import lombok.Getter;

@Getter
public enum ExerciseCategoryType {
	FITNESS("헬스"),
	PILATES("필라테스"),
	YOGA("요가"),
	CARDIO("유산소");

	private String name;

	ExerciseCategoryType(String name) {
		this.name = name;
	}
}
