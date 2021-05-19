package com.coachmybody.record.domain.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coachmybody.record.domain.Nunbody;
import com.coachmybody.user.domain.User;

public interface NunbodyRepository extends JpaRepository<Nunbody, Long> {
	Optional<Nunbody> findNunbodyByUserAndDate(User user, LocalDate date);
}
