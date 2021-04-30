package com.coachmybody.routine.type;

import lombok.Getter;

@Getter
public enum UnitType {
	KM("km"),
	KG("kg");

	private String name;

	UnitType(String name) {
		this.name = name;
	}
}
