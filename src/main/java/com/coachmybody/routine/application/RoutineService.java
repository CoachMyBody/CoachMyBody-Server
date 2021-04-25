package com.coachmybody.routine.application;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coachmybody.common.exception.NotAcceptableException;
import com.coachmybody.routine.domain.Routine;
import com.coachmybody.routine.domain.repository.RoutineQueryRepository;
import com.coachmybody.routine.domain.repository.RoutineRepository;
import com.coachmybody.routine.interfaces.dto.RoutineDetailResponse;
import com.coachmybody.routine.interfaces.dto.RoutineSimpleResponse;
import com.coachmybody.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoutineService {

	private final RoutineRepository routineRepository;
	private final RoutineQueryRepository routineQueryRepository;

	@Transactional
	public void create(String title, User user) {

		Routine routine = new Routine(title, user);

		routineRepository.save(routine);
	}

	@Transactional(readOnly = true)
	public List<RoutineSimpleResponse> findMyRoutine(User user) {
		return routineRepository.findAllByUser(user)
			.stream()
			.map(RoutineSimpleResponse::of)
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
		List<Routine> routines = routineQueryRepository.findByIds(routineIds);

		routines.forEach(routine -> {
			UUID routineUserId = routine.getUser().getId();
			if (!routineUserId.equals(userId)) {
				throw new NotAcceptableException();
			}
		});

		routineRepository.deleteAll(routines);
	}
}