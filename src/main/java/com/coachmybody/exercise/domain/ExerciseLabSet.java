package com.coachmybody.exercise.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;

@Getter
@DiscriminatorValue("LAB_SET")
@Entity
public class ExerciseLabSet extends ExerciseTime {
	@Column
	private Integer exerciseLab;

	@Column
	private Integer exerciseSet;
}
