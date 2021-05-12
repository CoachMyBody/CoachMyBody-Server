package com.coachmybody.exercise.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.coachmybody.exercise.type.MuscleType;

import lombok.Getter;

@Getter
@Entity
public class Muscle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(value = EnumType.STRING)
	private MuscleType name;

	@JoinColumn(name = "body_part_sub_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private BodyPartSub bodyPartSub;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "muscle", cascade = CascadeType.ALL)
	private Set<ExerciseToMuscle> exerciseToMuscles;
}
