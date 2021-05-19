package com.coachmybody.record.domain.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coachmybody.record.domain.Inbody;
import com.coachmybody.user.domain.User;

public interface InbodyRepository extends JpaRepository<Inbody, Long> {
	Optional<Inbody> findInbodyByUserAndDate(User user, LocalDate date);
}
