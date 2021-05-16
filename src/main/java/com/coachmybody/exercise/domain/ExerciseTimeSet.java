package com.coachmybody.exercise.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;

@Getter
@DiscriminatorValue("TIME_SET")
@Entity
public class ExerciseTimeSet extends ExerciseRecord {
	@Column
	private Integer exerciseMinutes;

	@Column
	private Integer exerciseSeconds;

	@Column
	private Integer exerciseSet;
}
