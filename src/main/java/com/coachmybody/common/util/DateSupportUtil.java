package com.coachmybody.common.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateSupportUtil {

	private static final int EXPIRE_AMOUNT = 1;
	private static final TemporalUnit EXPIRE_UNIT = ChronoUnit.DAYS;

	public static Instant calculateExpireAt(Instant now) {
		return now.plus(EXPIRE_AMOUNT, EXPIRE_UNIT);
	}
}
