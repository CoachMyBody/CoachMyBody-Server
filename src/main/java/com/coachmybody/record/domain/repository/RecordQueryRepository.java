package com.coachmybody.record.domain.repository;

import static com.coachmybody.record.domain.QRecord.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.coachmybody.record.domain.Record;
import com.coachmybody.user.domain.User;
import com.querydsl.core.types.OrderSpecifier;
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

	public List<Record> findByDateBetween(LocalDate startDate, LocalDate endDate, User user) {
		return queryFactory.selectFrom(record)
			.where(betweenDateAndUser(startDate, endDate, user))
			.fetch();
	}

	public LocalDate findFirstRecordDate(User user) {
		return queryFactory.select(record.date)
			.from(record)
			.where(eqUser(user))
			.orderBy(dateAsc())
			.fetchFirst();
	}

	private BooleanExpression eqDateAndUser(LocalDate date, User user) {
		return record.date.eq(date)
			.and(record.user.eq(user));
	}

	private BooleanExpression betweenDateAndUser(LocalDate startDate, LocalDate endDate, User user) {
		return record.date.between(startDate, endDate)
			.and(record.user.eq(user));
	}

	private BooleanExpression eqUser(User user) {
		return record.user.eq(user);
	}

	private OrderSpecifier dateAsc() {
		return record.date.asc();
	}
}