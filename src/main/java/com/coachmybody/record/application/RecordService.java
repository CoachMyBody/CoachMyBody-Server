package com.coachmybody.record.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coachmybody.record.domain.Inbody;
import com.coachmybody.record.domain.Nunbody;
import com.coachmybody.record.domain.Record;
import com.coachmybody.record.domain.RecordRoutine;
import com.coachmybody.record.domain.RecordRoutineExercise;
import com.coachmybody.record.domain.repository.InbodyRepository;
import com.coachmybody.record.domain.repository.NunbodyRepository;
import com.coachmybody.record.domain.repository.RecordQueryRepository;
import com.coachmybody.record.domain.repository.RecordRepository;
import com.coachmybody.record.domain.repository.RecordRoutineExerciseRepository;
import com.coachmybody.record.domain.repository.RecordRoutineRepository;
import com.coachmybody.record.interfaces.dto.InbodyCreateRequest;
import com.coachmybody.record.interfaces.dto.NunbodyCreateRequest;
import com.coachmybody.record.interfaces.dto.RecordCreateRequest;
import com.coachmybody.record.interfaces.dto.RecordDailyResponse;
import com.coachmybody.record.interfaces.dto.RecordMonthlyResponse;
import com.coachmybody.routine.domain.Routine;
import com.coachmybody.routine.domain.repository.RoutineRepository;
import com.coachmybody.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecordService {
	private final RecordRepository recordRepository;
	private final RecordQueryRepository recordQueryRepository;
	private final RoutineRepository routineRepository;
	private final InbodyRepository inbodyRepository;
	private final NunbodyRepository nunbodyRepository;
	private final RecordRoutineRepository recordRoutineRepository;
	private final RecordRoutineExerciseRepository recordRoutineExerciseRepository;

	@Transactional
	public void create(RecordCreateRequest request, User user) {
		Inbody inbody = null;
		if (request.getInbody() != null) {
			InbodyCreateRequest inbodyRequest = request.getInbody();

			inbody = Inbody.builder()
				.weight(inbodyRequest.getWeight())
				.skeletalMuscleMass(inbodyRequest.getSkeletalMuscleMass())
				.bodyFatMass(inbodyRequest.getBodyFatMass())
				.user(user)
				.build();

			inbody = inbodyRepository.save(inbody);
		}

		Nunbody nunbody = null;
		if (request.getNunbody() != null) {
			NunbodyCreateRequest nunbodyRequest = request.getNunbody();

			nunbody = Nunbody.builder()
				.imageUri(nunbodyRequest.getImageUri())
				.tag(nunbodyRequest.getTag())
				.user(user)
				.build();

			nunbody = nunbodyRepository.save(nunbody);
		}

		Routine routine = routineRepository.findById(request.getRoutineId())
			.orElseThrow(EntityNotFoundException::new);

		RecordRoutine recordRoutine = RecordRoutine.of(routine);
		recordRoutine = recordRoutineRepository.save(recordRoutine);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(request.getDate(), formatter);

		Record record = Record.builder()
			.date(date)
			.hours(request.getHours())
			.minutes(request.getMinutes())
			.feedbackBySelf(request.getFeedbackBySelf())
			.user(user)
			.recordRoutine(recordRoutine)
			.inbody(inbody)
			.nunbody(nunbody)
			.build();

		recordRepository.save(record);

		RecordRoutine finalRecordRoutine = recordRoutine;
		routine.getExercises()
			.forEach(routineExercise -> {
				RecordRoutineExercise recordRoutineExercise = RecordRoutineExercise.of(routineExercise,
					finalRecordRoutine);
				recordRoutineExerciseRepository.save(recordRoutineExercise);
			});
	}

	@Transactional(readOnly = true)
	public RecordDailyResponse getDailyRecord(LocalDate date, User user) {
		List<Record> records = recordQueryRepository.findByDate(date, user);
		return RecordDailyResponse.of(records);
	}

	@Transactional(readOnly = true)
	public RecordMonthlyResponse getMonthlyRecord(LocalDate date, User user) {
		return null;
	}
}