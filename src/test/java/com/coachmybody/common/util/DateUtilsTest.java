package com.coachmybody.common.util;

import static org.assertj.core.api.Assertions.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateUtilsTest {
	@DisplayName(value = "현재 기준 하루를 더한 날짜를 반환")
	@Test
	void plusOneDay() {
		// given
		Instant now = Instant.now();
		Instant expect = DateUtils.calculateExpireAt(now);

		// when
		Instant tomorrow = now.plus(1, ChronoUnit.DAYS);

		// then
		assertThat(tomorrow).isEqualTo(expect);
	}

	@DisplayName(value = "날짜를 yyyy-MM-dd 형식으로 반환")
	@Test
	void convertDatePattern() {
		// given
		LocalDate date = LocalDate.of(2021, 8, 29);
		String expect = "2021-08-29";

		// when
		String result = DateUtils.convertStringBarType(date);

		// then
		assertThat(result).isEqualTo(expect);
	}

	@DisplayName(value = "년월을 해당 년월의 1일 날짜로 반환")
	@Test
	void convertFirstDayOfMonth() {
		// given
		YearMonth yearMonth = YearMonth.of(2021, 8);
		LocalDate expect = LocalDate.of(2021, 8, 1);

		// when
		LocalDate result = DateUtils.convertFirstDayOfMonth(yearMonth);

		// then
		assertThat(result).isEqualTo(expect);
	}

	@DisplayName(value = "년월을 해당 년월의 마지막 날짜로 반환")
	@Test
	void convertLastDayOfMonth() {
		// given
		YearMonth yearMonth = YearMonth.of(2021, 8);
		LocalDate expect = LocalDate.of(2021, 8, 31);

		// when
		LocalDate result = DateUtils.convertLastDayOfMonth(yearMonth);

		// then
		assertThat(result).isEqualTo(expect);
	}

	@DisplayName(value = "날짜를 요일로 반환")
	@Test
	void convertDayOfWeek() {
		// given
		LocalDate date = LocalDate.of(2021, 8, 29);
		String expect = "일요일";

		// when
		String result = DateUtils.convertDateToDayOfWeek(date);

		// then
		assertThat(result).isEqualTo(expect);
	}
}