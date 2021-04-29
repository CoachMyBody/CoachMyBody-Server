package com.coachmybody.record.application;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coachmybody.record.domain.Record;
import com.coachmybody.record.domain.repository.RecordRepository;
import com.coachmybody.record.interfaces.dto.RecordCreateRequest;
import com.coachmybody.routine.domain.Routine;
import com.coachmybody.routine.domain.repository.RoutineRepository;
import com.coachmybody.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecordService {
	private final RecordRepository recordRepository;
	private final RoutineRepository routineRepository;

	@Transactional
	public void create(RecordCreateRequest request, User user) {
		Routine routine = routineRepository.findById(request.getRoutineId())
			.orElseThrow(EntityNotFoundException::new);

		request.setRoutine(routine);
		Record record = Record.of(user, request);

		recordRepository.save(record);
	}
}