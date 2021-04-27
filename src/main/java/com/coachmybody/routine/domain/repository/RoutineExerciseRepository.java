package com.coachmybody.routine.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coachmybody.routine.domain.RoutineExercise;

public interface RoutineExerciseRepository extends JpaRepository<RoutineExercise, Long> {
}
