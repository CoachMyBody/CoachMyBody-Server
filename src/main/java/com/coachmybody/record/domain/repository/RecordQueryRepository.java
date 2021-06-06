package com.coachmybody.record.domain.repository;

import static com.coachmybody.record.domain.QRecord.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.coachmybody.record.domain.Record;
import com.coachmybody.user.domain.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RecordQueryRepository {
	private final JPAQueryFactory queryFactory;

	public List<Record> findByDate(LocalDate date, User user) {
		return queryFactory.selectFrom(record)
			.where(eqDateAndUser(date, user))
			.fetch();
	}

	BooleanExpression eqDateAndUser(LocalDate date, User user) {
		return record.date.eq(date)
			.and(record.user.eq(user));
	}
}
