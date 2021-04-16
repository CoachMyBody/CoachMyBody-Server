package com.coachmybody.exercise.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.coachmybody.exercise.type.BodyPartSubType;

import lombok.Getter;

@Getter
@Entity
public class BodyPartSub {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(value = EnumType.STRING)
	private BodyPartSubType name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "bodyPartSub", cascade = CascadeType.ALL)
	private List<ExerciseToBodyPartSub> exerciseToBodyPartSubList;
}
