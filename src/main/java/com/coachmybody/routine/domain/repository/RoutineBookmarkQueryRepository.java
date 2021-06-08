package com.coachmybody.routine.domain.repository;

import static com.coachmybody.routine.domain.QRoutine.*;
import static com.coachmybody.routine.domain.QRoutineBookmark.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.coachmybody.routine.domain.Routine;
import com.coachmybody.user.domain.User;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RoutineBookmarkQueryRepository {
	private final JPAQueryFactory queryFactory;

	public Page<Routine> findBookmarkRoutines(User user, Pageable pageable) {
		QueryResults<Routine> results = queryFactory.selectFrom(routine)
			.join(routineBookmark).on(routine.id.eq(routineBookmark.routineId))
			.where(routineBookmark.userId.eq(user.getId()))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetchResults();

		return new PageImpl<>(results.getResults(), pageable, results.getTotal());
	}
}
