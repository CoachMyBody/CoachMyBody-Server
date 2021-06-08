package com.coachmybody.exercise.interfaces.dto;

import com.coachmybody.exercise.interfaces.dto.type.BodyTypeRequest;
import com.coachmybody.exercise.type.ExerciseCategoryType;

import lombok.Value;

@Value
public class ExerciseFilterRequest {
	ExerciseCategoryType category;
	BodyTypeRequest bodyPart;
}
