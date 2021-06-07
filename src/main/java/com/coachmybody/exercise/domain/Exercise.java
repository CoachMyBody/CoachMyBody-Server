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
import javax.persistence.OneToOne;

import com.coachmybody.exercise.type.BodyPartType;
import com.coachmybody.exercise.type.ExerciseCategoryType;

import lombok.Getter;

@Getter
@Entity
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Enumerated(value = EnumType.STRING)
	private ExerciseCategoryType category;

	@Enumerated(value = EnumType.STRING)
	private BodyPartType bodyPart;

	private String imageUri;

	private String description;

	private String caution;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "main_body_part_sub_id", nullable = false)
	private BodyPartSub mainBodyPartSub;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "exercise")
	private ExerciseRecord exerciseRecord;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "exercise")
	private Set<ExerciseToMuscle> exerciseToMuscles;
}