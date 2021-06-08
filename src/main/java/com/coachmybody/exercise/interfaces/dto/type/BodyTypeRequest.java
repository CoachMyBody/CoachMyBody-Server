package com.coachmybody.exercise.interfaces.dto.type;

import lombok.Getter;

@Getter
public enum BodyTypeRequest {
	FULL_BODY("전신"),
	UPPER_BODY("상체"),
	LOWER_BODY("하체"),
	NONE("전체");

	private String name;

	BodyTypeRequest(String name) {
		this.name = name;
	}
}
