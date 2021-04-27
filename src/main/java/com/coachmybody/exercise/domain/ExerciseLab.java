package com.coachmybody.exercise.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;

@Getter
@Entity
public class ExerciseLab implements Serializable {
	@Id
	@OneToOne
	@JoinColumn(name = "exercise_id")
	private Exercise exercise;

	private Integer exerciseLab;

	private Integer exerciseSet;
}