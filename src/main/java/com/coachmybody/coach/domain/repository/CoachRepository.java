package com.coachmybody.coach.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coachmybody.coach.domain.Coach;
import com.coachmybody.user.domain.User;

public interface CoachRepository extends JpaRepository<Coach, Long> {
	Optional<Coach> findByUser(User user);
}
