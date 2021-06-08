package com.coachmybody.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Locale;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {

	private static final int EXPIRE_AMOUNT = 1;
	private static final TemporalUnit EXPIRE_UNIT = ChronoUnit.DAYS;

	public static Instant calculateExpireAt(Instant now) {
		return now.plus(EXPIRE_AMOUNT, EXPIRE_UNIT);
	}

	public static String convertDateToString(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		return date.format(formatter);
	}

	public static LocalDate convertFirstDayOfMonth(YearMonth yearMonth) {
		return yearMonth.atDay(1);
	}

	public static LocalDate convertLastDayOfMonth(YearMonth yearMonth) {
		return yearMonth.atEndOfMonth();
	}

	public static String convertDateToDayOfWeek(LocalDate date) {
		return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
	}
}
