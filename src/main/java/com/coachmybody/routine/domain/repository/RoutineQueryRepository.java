package com.coachmybody.routine.domain.repository;

import static com.coachmybody.routine.domain.QRoutine.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.coachmybody.routine.domain.Routine;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RoutineQueryRepository {
	private final JPAQueryFactory queryFactory;

	public List<Routine> findByIds(List<Long> ids) {
		return queryFactory.selectFrom(routine)
			.where(routine.id.in(ids))
			.fetch();
	}
}
