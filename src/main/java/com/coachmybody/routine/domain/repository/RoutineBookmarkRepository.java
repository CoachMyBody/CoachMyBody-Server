package com.coachmybody.routine.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coachmybody.routine.domain.RoutineBookmark;
import com.coachmybody.routine.domain.RoutineBookmarkKey;

public interface RoutineBookmarkRepository extends JpaRepository<RoutineBookmark, RoutineBookmarkKey> {
}
