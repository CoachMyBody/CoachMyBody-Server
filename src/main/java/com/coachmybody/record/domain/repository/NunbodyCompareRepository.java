package com.coachmybody.record.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coachmybody.record.domain.NunbodyCompare;
import com.coachmybody.user.domain.User;

public interface NunbodyCompareRepository extends JpaRepository<NunbodyCompare, Long> {
	Optional<NunbodyCompare> findNunbodyCompareByUser(User user);
}