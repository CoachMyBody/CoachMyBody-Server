package com.coachmybody.exercise.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coachmybody.exercise.domain.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
