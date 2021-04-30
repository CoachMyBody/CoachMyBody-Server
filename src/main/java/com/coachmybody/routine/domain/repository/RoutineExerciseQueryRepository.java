package com.coachmybody.routine.domain.repository;

import static com.coachmybody.routine.domain.QRoutineExercise.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RoutineExerciseQueryRepository {
	private final JPAQueryFactory queryFactory;

	public long deleteAllByIds(List<Long> ids) {
		return queryFactory.delete(routineExercise)
			.where(routineExercise.id.in(ids))
			.execute();
	}
}
