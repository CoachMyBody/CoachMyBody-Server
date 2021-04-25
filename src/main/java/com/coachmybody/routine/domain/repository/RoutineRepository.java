package com.coachmybody.routine.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coachmybody.routine.domain.Routine;
import com.coachmybody.user.domain.User;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

	List<Routine> findAllByUser(User user);

}