package com.coachmybody.routine.application;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coachmybody.common.exception.NotAcceptableException;
import com.coachmybody.exercise.domain.Exercise;
import com.coachmybody.exercise.domain.repository.ExerciseRepository;
import com.coachmybody.routine.domain.Routine;
import com.coachmybody.routine.domain.RoutineExercise;
import com.coachmybody.routine.domain.repository.RoutineExerciseQueryRepository;
import com.coachmybody.routine.domain.repository.RoutineExerciseRepository;
import com.coachmybody.routine.domain.repository.RoutineRepository;
import com.coachmybody.routine.interfaces.dto.RoutineDetailResponse;
import com.coachmybody.routine.interfaces.dto.RoutineExerciseUpdateRequest;
import com.coachmybody.routine.interfaces.dto.RoutineSimpleResponse;
import com.coachmybody.routine.type.UnitType;
import com.coachmybody.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoutineService {

	private final RoutineRepository routineRepository;
	private final ExerciseRepository exerciseRepository;
	private final RoutineExerciseRepository routineExerciseRepository;
	private final RoutineExerciseQueryRepository routineExerciseQueryRepository;

	@Transactional
	public void create(String title, User user) {

		Routine routine = new Routine(title, user);

		routineRepository.save(routine);
	}

	@Transactional(readOnly = true)
	public List<RoutineSimpleResponse> findMyRoutine(User user, boolean hasExercise) {
		return routineRepository.findAllByUser(user)
			.stream()
			.map(RoutineSimpleResponse::of)
			.filter(routineSimpleResponse -> {
				if (hasExercise) {
					return routineSimpleResponse.getExerciseCount() > 0;
				}
				return true;
			})
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public RoutineDetailResponse findRoutineById(final long routineId) {
		Routine routine = routineRepository.findById(routineId)
			.orElseThrow(EntityNotFoundException::new);

		return RoutineDetailResponse.of(routine);
	}

	@Transactional
	public void deleteByIds(List<Long> routineIds, UUID userId) {
		List<Routine> routines = routineRepository.findAllById(routineIds);

		routines.forEach(routine -> {
			UUID routineUserId = routine.getUser().getId();
			if (!routineUserId.equals(userId)) {
				throw new NotAcceptableException();
			}
		});

		routineRepository.deleteAll(routines);
	}

	@Transactional
	public void addExercises(final long routineId, List<Long> exerciseIds) {
		Routine routine = routineRepository.findById(routineId)
			.orElseThrow(EntityNotFoundException::new);

		List<Exercise> exercises = exerciseRepository.findAllById(exerciseIds);

		List<RoutineExercise> routineExercises = routine.getExercises()
			.stream()
			.sorted(Comparator.comparing(RoutineExercise::getPriority))
			.collect(Collectors.toList());

		int prePriority = 0;
		if (routineExercises.size() > 0) {
			int routineExercisesSize = routineExercises.size();
			prePriority = routineExercises.get(routineExercisesSize - 1).getPriority() + 1;
		}
		int finalPrePriority = prePriority;

		exercises.forEach(exercise -> {
			RoutineExercise routineExercise = RoutineExercise.builder()
				.routine(routine)
				.exercise(exercise)
				.unitValue(0f)
				.unit(UnitType.KG)
				.priority(exercises.indexOf(exercise) + finalPrePriority)
				.build();
			routineExerciseRepository.save(routineExercise);
		});
	}

	@Transactional
	public void updateRoutineExercise(final long id, RoutineExerciseUpdateRequest request) {
		RoutineExercise routineExercise = routineExerciseRepository.findById(id)
			.orElseThrow(EntityNotFoundException::new);

		routineExercise.updateLabSet(request);
	}

	@Transactional
	public void updateTitle(final long routineId, final String newTitle) {
		Routine routine = routineRepository.findById(routineId)
			.orElseThrow(EntityNotFoundException::new);

		routine.updateTitle(newTitle);
	}

	@Transactional
	public void deleteExercises(List<Long> ids) {
		routineExerciseQueryRepository.deleteAllByIds(ids);
	}

	@Transactional
	public void updateRoutineExerciseOrder(List<Long> ids) {
		ids.forEach(id -> {
			RoutineExercise routineExercise = routineExerciseRepository.findById(id)
				.orElseThrow(EntityNotFoundException::new);
			routineExercise.updatePriority(ids.indexOf(id));
		});
	}
}