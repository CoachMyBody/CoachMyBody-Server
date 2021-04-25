package com.coachmybody.exercise.type;

import lombok.Getter;

@Getter
public enum BodyPartSubType {
	BACK("등"),
	SHOULDER("어깨"),
	CHEST("가슴"),
	ABS("복부"),
	ARM("팔"),
	HIP("엉덩이"),
	LEG("다리");

	private String name;

	BodyPartSubType(String name) {
		this.name = name;
	}
}
