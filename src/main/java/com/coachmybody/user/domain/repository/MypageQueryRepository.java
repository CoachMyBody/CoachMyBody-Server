package com.coachmybody.user.domain.repository;

import com.coachmybody.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MypageQueryRepository {
    private final JPAQueryFactory queryFactory;
}