package com.coachmybody.exercise.type;

import lombok.Getter;

@Getter
public enum MuscleType {
	TRAPEZIUS("승모근", "등세모근", "Trapezius"),
	LATISSIMUS_DORSI("광배근", "넓은등근", "Latissimus dorsi"),
	SERRATUS_ANTERIOR("전거근", "앞톺니근", "Serratus anterior"),
	ERECTOR_SPINAE("기립근", "척추세움근", "Erector spinae"),
	DELTOID("삼각근", "어깨세모근", "Deltoid"),
	INFRASPINATUS("극하근", "가시아래근", "Infraspinatus")

	;

	private String name;
	private String ko;
	private String eng;

	MuscleType(String name, String ko, String eng) {
		this.name = name;
		this.ko = ko;
		this.eng = eng;
	}
}
