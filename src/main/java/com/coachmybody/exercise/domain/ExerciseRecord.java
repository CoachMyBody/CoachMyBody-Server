package com.coachmybody.exercise.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.coachmybody.exercise.type.ExerciseRecordType;

import lombok.Getter;

@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Entity
public abstract class ExerciseRecord implements Serializable {
	@Id
	@OneToOne
	@JoinColumn(name = "exercise_id")
	private Exercise exercise;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "type", insertable = false, updatable = false)
	private ExerciseRecordType type;
}