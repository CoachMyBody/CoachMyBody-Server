package com.coachmybody.exercise.type;

import lombok.Getter;

@Getter
public enum BodyPartSubType {
	TRAP("승모"),
	SHOULDERS("어깨"),
	CHEST("가슴"),
	BICEPS("이두"),
	ABS("복부"),
	FOREARMS("전완"),
	THIGHS("대퇴"),
	TRICEPS("삼두"),
	BACK("등"),
	WAIST("허리"),
	GLUTES("둔부"),
	HAMSTRINGS("슬굴곡근"),
	CALVES("종아리");

	private String name;

	BodyPartSubType(String name) {
		this.name = name;
	}
}
