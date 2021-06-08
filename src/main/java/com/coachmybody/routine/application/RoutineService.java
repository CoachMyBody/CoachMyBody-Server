package com.coachmybody.routine.application;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coachmybody.common.exception.NotFoundEntityException;
import com.coachmybody.exercise.domain.Exercise;
import com.coachmybody.exercise.domain.ExerciseLabSet;
import com.coachmybody.exercise.domain.ExerciseRecord;
import com.coachmybody.exercise.domain.ExerciseTimeSet;
import com.coachmybody.exercise.domain.repository.ExerciseRepository;
import com.coachmybody.exercise.type.ExerciseRecordType;
import com.coachmybody.routine.domain.Routine;
import com.coachmybody.routine.domain.RoutineBookmark;
import com.coachmybody.routine.domain.RoutineBookmarkKey;
import com.coachmybody.routine.domain.RoutineExercise;
import com.coachmybody.routine.domain.repository.RoutineBookmarkRepository;
import com.coachmybody.routine.domain.repository.RoutineExerciseQueryRepository;
import com.coachmybody.routine.domain.repository.RoutineExerciseRepository;
import com.coachmybody.routine.domain.repository.RoutineRepository;
import com.coachmybody.routine.interfaces.dto.RoutineDetailResponse;
import com.coachmybody.routine.interfaces.dto.RoutineExerciseUpdateRequest;
import com.coachmybody.routine.interfaces.dto.RoutineSimpleResponse;
import com.coachmybody.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoutineService {

	private final RoutineRepository routineRepository;
	private final ExerciseRepository exerciseRepository;
	private final RoutineExerciseRepository routineExerciseRepository;
	private final RoutineExerciseQueryRepository routineExerciseQueryRepository;
	private final RoutineBookmarkRepository routineBookmarkRepository;

	@Transactional
	public void create(String title, User user) {

		Routine routine = new Routine(title, user);

		routineRepository.save(routine);
	}

	@Transactional(readOnly = true)
	public List<RoutineSimpleResponse> findMyRoutines(User user, boolean hasExercise) {
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
	public RoutineDetailResponse findRoutineById(final long routineId, final UUID userId) {
		Routine routine = routineRepository.findById(routineId)
			.orElseThrow(EntityNotFoundException::new);

		RoutineBookmarkKey key = new RoutineBookmarkKey(routineId, userId);
		boolean isBookmarked = routineBookmarkRepository.existsById(key);

		return RoutineDetailResponse.of(routine, isBookmarked);
	}

	@Transactional
	public void deleteByIds(List<Long> routineIds) {
		List<Routine> routines = routineRepository.findAllById(routineIds);
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
			ExerciseRecord exerciseRecord = exercise.getExerciseRecord();
			ExerciseRecordType recordType = exerciseRecord.getType();

			Integer exerciseLab = null, exerciseSet = null, exerciseMinutes = null, exerciseSeconds = null;

			switch (recordType) {
				case LAB_SET:
					exerciseLab = ((ExerciseLabSet)exerciseRecord).getExerciseLab();
					exerciseSet = ((ExerciseLabSet)exerciseRecord).getExerciseSet();
					break;
				case TIME_SET:
					exerciseMinutes = ((ExerciseTimeSet)exerciseRecord).getExerciseMinutes();
					exerciseSeconds = ((ExerciseTimeSet)exerciseRecord).getExerciseSeconds();
					exerciseSet = ((ExerciseTimeSet)exerciseRecord).getExerciseSet();
					break;
			}

			RoutineExercise routineExercise = RoutineExercise.builder()
				.routine(routine)
				.exercise(exercise)
				.priority(exercises.indexOf(exercise) + finalPrePriority)
				.recordType(exerciseRecord.getType())
				.exerciseLab(exerciseLab)
				.exerciseSet(exerciseSet)
				.exerciseMinutes(exerciseMinutes)
				.exerciseSeconds(exerciseSeconds)
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

	@Transactional
	public boolean bookmark(final long routineId, final UUID userId) {
		routineRepository.findById(routineId)
			.orElseThrow(NotFoundEntityException::new);

		RoutineBookmarkKey key = new RoutineBookmarkKey(routineId, userId);

		if (routineBookmarkRepository.existsById(key)) {
			routineBookmarkRepository.deleteById(key);
			return false;
		} else {
			RoutineBookmark routineBookmark = new RoutineBookmark(routineId, userId);
			routineBookmarkRepository.save(routineBookmark);
			return true;
		}
	}

	@Transactional
	public void deleteBookmark(User user, List<Long> routineIds) {
		UUID userId = user.getId();
		for (Long routineId : routineIds) {
			RoutineBookmarkKey key = new RoutineBookmarkKey(routineId, userId);
			routineBookmarkRepository.deleteById(key);
		}
	}
}