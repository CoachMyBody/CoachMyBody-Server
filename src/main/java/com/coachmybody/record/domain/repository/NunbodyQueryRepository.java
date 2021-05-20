package com.coachmybody.record.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.coachmybody.record.domain.Nunbody;
import com.coachmybody.record.interfaces.type.NunbodySortType;
import com.coachmybody.user.domain.User;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.coachmybody.record.domain.QNunbody.nunbody;

@RequiredArgsConstructor
@Repository
public class NunbodyQueryRepository {
	private final JPAQueryFactory queryFactory;

	public Page<Nunbody> getNunbody(User user, Pageable pageable, NunbodySortType sort) {
		QueryResults<Nunbody> results = queryFactory.selectFrom(nunbody)
			.where(nunbody.user.eq(user))
			.orderBy(sort(sort))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetchResults();

		return new PageImpl<>(results.getResults(), pageable, results.getTotal());
	}

	OrderSpecifier sort(NunbodySortType sort) {
		switch (sort) {
			case DATE_ASC:
				return nunbody.date.asc();
			case DATE_DESC:
				return nunbody.date.desc();
		}
		return nunbody.date.desc();
	}
}
