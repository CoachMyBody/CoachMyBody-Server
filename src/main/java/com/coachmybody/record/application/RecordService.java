package com.coachmybody.record.application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.coachmybody.common.component.S3UploadComponent;
import com.coachmybody.common.dto.PageResponse;
import com.coachmybody.common.exception.DuplicatedEntityException;
import com.coachmybody.common.exception.FileUploadException;
import com.coachmybody.common.exception.NotFoundEntityException;
import com.coachmybody.common.util.DateUtils;
import com.coachmybody.record.domain.Inbody;
import com.coachmybody.record.domain.Nunbody;
import com.coachmybody.record.domain.NunbodyCompare;
import com.coachmybody.record.domain.Record;
import com.coachmybody.record.domain.RecordRoutine;
import com.coachmybody.record.domain.RecordRoutineExercise;
import com.coachmybody.record.domain.repository.InbodyRepository;
import com.coachmybody.record.domain.repository.NunbodyCompareRepository;
import com.coachmybody.record.domain.repository.NunbodyQueryRepository;
import com.coachmybody.record.domain.repository.NunbodyRepository;
import com.coachmybody.record.domain.repository.RecordQueryRepository;
import com.coachmybody.record.domain.repository.RecordRepository;
import com.coachmybody.record.domain.repository.RecordRoutineExerciseRepository;
import com.coachmybody.record.domain.repository.RecordRoutineRepository;
import com.coachmybody.record.interfaces.dto.InbodyCreateRequest;
import com.coachmybody.record.interfaces.dto.InbodyNunbodyCheckResponse;
import com.coachmybody.record.interfaces.dto.NunbodyCreateRequest;
import com.coachmybody.record.interfaces.dto.NunbodyResponse;
import com.coachmybody.record.interfaces.dto.RecordCreateRequest;
import com.coachmybody.record.interfaces.dto.RecordDailyResponse;
import com.coachmybody.record.interfaces.dto.RecordMonthlyOneDayResponse;
import com.coachmybody.record.interfaces.dto.RecordMonthlyResponse;
import com.coachmybody.record.interfaces.type.NunbodySortType;
import com.coachmybody.record.type.NunbodyCompareType;
import com.coachmybody.routine.domain.Routine;
import com.coachmybody.routine.domain.repository.RoutineRepository;
import com.coachmybody.user.domain.User;
import com.google.common.collect.Lists;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	private final NunbodyQueryRepository nunbodyQueryRepository;
	private final NunbodyCompareRepository nunbodyCompareRepository;
	private final S3UploadComponent s3UploadComponent;

	@Transactional
	public void create(RecordCreateRequest request, User user) {
		Inbody inbody = null;
		if (request.getInbody() != null) {
			InbodyCreateRequest inbodyRequest = request.getInbody();
			inbody = Inbody.of(inbodyRequest, user);
			inbody = inbodyRepository.save(inbody);
		}

		Nunbody nunbody = null;
		if (request.getNunbody() != null) {
			NunbodyCreateRequest nunbodyRequest = request.getNunbody();
			nunbody = Nunbody.of(nunbodyRequest, user);
			nunbody = nunbodyRepository.save(nunbody);
		}

		Routine routine = routineRepository.findById(request.getRoutineId())
			.orElseThrow(EntityNotFoundException::new);

		RecordRoutine recordRoutine = RecordRoutine.of(routine);
		recordRoutine = recordRoutineRepository.save(recordRoutine);

		Record record = Record.builder()
			.date(request.getDate())
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
	public RecordMonthlyResponse getMonthlyRecord(User user, YearMonth yearMonth) {
		LocalDate startDate = DateUtils.convertFirstDayOfMonth(yearMonth);
		LocalDate endDate = DateUtils.convertLastDayOfMonth(yearMonth);

		Map<LocalDate, List<Record>> recordMap = new HashMap<>();
		List<Record> records = recordQueryRepository.findByDateBetween(startDate, endDate, user);
		for (Record record : records) {
			LocalDate date = record.getDate();
			List<Record> mapRecordList = recordMap.get(date) == null ? Lists.newArrayList() : recordMap.get(date);
			mapRecordList.add(record);
			recordMap.put(date, mapRecordList);
		}

		List<RecordMonthlyOneDayResponse> days = Lists.newArrayList();
		int recordDayCount = 0;
		do {
			String dayOfWeek = DateUtils.convertDateToDayOfWeek(startDate);
			int recordCount = 0;

			List<Record> recordList = recordMap.get(startDate);
			if (recordList != null) {
				recordCount = recordList.size();
				recordDayCount++;
			}

			RecordMonthlyOneDayResponse response = RecordMonthlyOneDayResponse.builder()
				.date(startDate)
				.dayOfWeek(dayOfWeek)
				.recordCount(recordCount)
				.build();
			days.add(response);

			startDate = startDate.plusDays(1);
		} while (!startDate.isAfter(endDate));

		return RecordMonthlyResponse.builder()
			.days(days)
			.recordDayCount(recordDayCount)
			.build();
	}

	@Transactional
	public void createInbody(User user, InbodyCreateRequest request) {
		Optional<Inbody> checkInbody = inbodyRepository.findInbodyByUserAndDate(user, request.getDate());

		if (checkInbody.isPresent()) {
			throw new DuplicatedEntityException();
		}

		Inbody inbody = Inbody.of(request, user);

		inbodyRepository.save(inbody);
	}

	@Transactional
	public void createNunbody(User user, NunbodyCreateRequest request) {
		Optional<Nunbody> checkNunbody = nunbodyRepository.findNunbodyByUserAndDate(user, request.getDate());

		if (checkNunbody.isPresent()) {
			throw new DuplicatedEntityException();
		}

		Nunbody nunbody = Nunbody.of(request, user);

		nunbodyRepository.save(nunbody);
	}

	@Transactional
	public String uploadNunbodyImage(MultipartFile image) {
		String imageUri = "";
		try {
			imageUri = s3UploadComponent.uploadImage(image);
		} catch (IOException e) {
			throw new FileUploadException();
		}
		return imageUri;
	}

	@Transactional(readOnly = true)
	public PageResponse<NunbodyResponse> getNunbody(User user, Pageable pageable, NunbodySortType sort) {
		Page<Nunbody> nunbody = nunbodyQueryRepository.getNunbody(user, pageable, sort);
		return PageResponse.of(nunbody, NunbodyResponse::of);
	}

	@Transactional
	public void createNunbodyCompare(User user, Long nunbodyId, NunbodyCompareType type) {
		Nunbody nunbody = nunbodyRepository.findById(nunbodyId)
			.orElseThrow(NotFoundEntityException::new);

		Optional<NunbodyCompare> nunbodyBeforeAfterOptional = nunbodyCompareRepository.findNunbodyCompareByUser(
			user);

		NunbodyCompare nunbodyCompare = new NunbodyCompare(user);

		if (nunbodyBeforeAfterOptional.isPresent()) {
			nunbodyCompare = nunbodyBeforeAfterOptional.get();
		}

		if (type == NunbodyCompareType.BEFORE) {
			nunbodyCompare.setBeforeNunbody(nunbody);
		} else {
			nunbodyCompare.setAfterNunbody(nunbody);
		}

		nunbodyCompareRepository.save(nunbodyCompare);
	}

	@Transactional
	public void deleteNunbodyCompare(User user, NunbodyCompareType type) {
		NunbodyCompare nunbodyCompare = nunbodyCompareRepository.findNunbodyCompareByUser(user)
			.orElseThrow(NotFoundEntityException::new);

		if (type == NunbodyCompareType.BEFORE) {
			nunbodyCompare.setBeforeNunbody(null);
		} else {
			nunbodyCompare.setAfterNunbody(null);
		}
	}

	@Transactional(readOnly = true)
	public InbodyNunbodyCheckResponse checkInbodyNunbody(User user, LocalDate date) {
		Optional<Inbody> inbody = inbodyRepository.findInbodyByUserAndDate(user, date);
		Optional<Nunbody> nunbody = nunbodyRepository.findNunbodyByUserAndDate(user, date);

		return InbodyNunbodyCheckResponse.builder()
			.hasInbody(inbody.isPresent())
			.hasNunbody(nunbody.isPresent())
			.build();
	}
}