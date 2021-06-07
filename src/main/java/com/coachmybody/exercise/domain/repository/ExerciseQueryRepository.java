package com.coachmybody.exercise.domain.repository;

import static com.coachmybody.exercise.domain.QExercise.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.coachmybody.exercise.domain.Exercise;
import com.coachmybody.exercise.interfaces.dto.ExerciseFilterRequest;
import com.coachmybody.exercise.interfaces.dto.type.BodyTypeRequest;
import com.coachmybody.exercise.type.BodyPartType;
import com.coachmybody.exercise.type.ExerciseCategoryType;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ExerciseQueryRepository {

	private final JPAQueryFactory queryFactory;

	public Page<Exercise> findExercises(ExerciseFilterRequest filter, Pageable pageable) {
		QueryResults<Exercise> results = queryFactory.selectFrom(exercise)
			.where(category(filter.getCategory())
				.and(eqBodyPart(filter.getBodyPart())))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetchResults();

		return new PageImpl<>(results.getResults(), pageable, results.getTotal());
	}

	BooleanExpression eqBodyPart(BodyTypeRequest bodyPart) {
		switch (bodyPart) {
			case NONE:
				return null;
			case FULL_BODY:
				return exercise.bodyPart.eq(BodyPartType.FULL_BODY);
			case UPPER_BODY:
				return exercise.bodyPart.in(BodyPartType.UPPER_BODY, BodyPartType.UPPER_LOWER_BODY);
			case LOWER_BODY:
				return exercise.bodyPart.in(BodyPartType.LOWER_BODY, BodyPartType.UPPER_LOWER_BODY);
		}
		return null;
	}

	BooleanExpression category(ExerciseCategoryType category) {
		return exercise.category.eq(category);
	}
}