package com.coachmybody.exercise.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Getter
@IdClass(ExerciseToBodyPartSubKey.class)
@Entity
public class ExerciseToBodyPartSub {
	@Id
	@JoinColumn(name = "exercise_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Exercise exercise;

	@Id
	@JoinColumn(name = "body_part_sub_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private BodyPartSub bodyPartSub;
}
