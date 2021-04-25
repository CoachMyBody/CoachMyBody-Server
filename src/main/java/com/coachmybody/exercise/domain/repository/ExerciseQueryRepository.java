package com.coachmybody.exercise.domain.repository;

import static com.coachmybody.exercise.domain.QExercise.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.coachmybody.exercise.domain.Exercise;
import com.coachmybody.exercise.interfaces.dto.ExerciseFilterRequest;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ExerciseQueryRepository {
	
	private final JPAQueryFactory queryFactory;

	public Page<Exercise> findExercises(ExerciseFilterRequest filter, Pageable pageable) {
		QueryResults<Exercise> results = queryFactory
			.selectFrom(exercise)
			.where(eq(filter))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetchResults();

		return new PageImpl<>(results.getResults(), pageable, results.getTotal());
	}

	BooleanExpression eq(ExerciseFilterRequest filter) {
		return exercise.bodyPart.eq(filter.getBodyPart())
			.and(exercise.category.eq(filter.getCategory()));
	}
}