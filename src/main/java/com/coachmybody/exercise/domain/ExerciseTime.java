package com.coachmybody.exercise.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;

@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@Entity
public abstract class ExerciseTime implements Serializable {
	@Id
	@OneToOne
	@JoinColumn(name = "exercise_id")
	private Exercise exercise;
}