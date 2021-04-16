package com.coachmybody.exercise.interfaces.dto;

import com.coachmybody.exercise.type.BodyPartType;
import com.coachmybody.exercise.type.ExerciseCategoryType;

import lombok.Value;

@Value
public class ExerciseFilterRequest {
	ExerciseCategoryType category;
	BodyPartType bodyPart;
}
