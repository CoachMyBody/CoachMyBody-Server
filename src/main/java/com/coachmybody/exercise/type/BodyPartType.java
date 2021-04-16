package com.coachmybody.exercise.type;

import lombok.Getter;

@Getter
public enum BodyPartType {
	FULL_BODY("FULL_BODY", "전신"),
	UPPER_BODY("UPPER_BODY", "상체"),
	LOWER_BODY("LOWER_BODY", "하체");

	private String name;
	private String ko;

	BodyPartType(String name, String ko) {
		this.name = name;
		this.ko = ko;
	}
}