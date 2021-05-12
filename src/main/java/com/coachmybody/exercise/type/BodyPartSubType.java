package com.coachmybody.exercise.type;

import lombok.Getter;

@Getter
public enum BodyPartSubType {
	SHOULDERS("어깨"),
	CHEST("가슴"),
	BACK("등"),
	ARMS("팔"),
	ABS("복부"),
	HIP("엉덩이"),
	LEGS("다리");

	private String name;

	BodyPartSubType(String name) {
		this.name = name;
	}
}