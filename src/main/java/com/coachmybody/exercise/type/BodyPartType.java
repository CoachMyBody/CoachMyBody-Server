package com.coachmybody.exercise.type;

import lombok.Getter;

@Getter
public enum BodyPartType {
	FULL_BODY("전신"),
	UPPER_BODY("상체"),
	LOWER_BODY("하체");

	private String name;

	BodyPartType(String name) {
		this.name = name;
	}
}