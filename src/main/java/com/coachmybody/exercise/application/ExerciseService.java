package com.coachmybody.exercise.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coachmybody.common.exception.NotFoundEntityException;
import com.coachmybody.exercise.domain.Exercise;
import com.coachmybody.exercise.domain.repository.ExerciseQueryRepository;
import com.coachmybody.exercise.domain.repository.ExerciseRepository;
import com.coachmybody.exercise.interfaces.dto.ExerciseDetailResponse;
import com.coachmybody.exercise.interfaces.dto.ExerciseFilterRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExerciseService {

	private final ExerciseRepository exerciseRepository;
	private final ExerciseQueryRepository exerciseQueryRepository;

	public Page<Exercise> findExercises(ExerciseFilterRequest filter, Pageable pageable) {
		return exerciseQueryRepository.findExercises(filter, pageable);
	}

	public ExerciseDetailResponse findExerciseById(final long exerciseId) {
		Exercise exercise = exerciseRepository.findById(exerciseId)
			.orElseThrow(NotFoundEntityException::new);

		List<Exercise> relatedExercises = exerciseQueryRepository.findRelatedExercises(exercise);

		return ExerciseDetailResponse.of(exercise, relatedExercises);
	}
}
