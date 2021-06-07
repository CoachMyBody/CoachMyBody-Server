package com.coachmybody.exercise.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	@JoinColumn(name = "body_part_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private BodyPart bodyPart;
}
