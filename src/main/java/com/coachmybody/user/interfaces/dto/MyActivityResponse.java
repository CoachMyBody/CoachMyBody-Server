package com.coachmybody.user.interfaces.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import com.coachmybody.common.util.DateUtils;
import com.coachmybody.user.type.BadgeType;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class MyActivityResponse {
	Integer level;
	List<BadgeType> badges;
	String startDate;
	Long periodDays;

	public static MyActivityResponse of(LocalDate startDate) {
		LocalDate today = LocalDate.now();

		long periodDays = 0;
		if (startDate != null) {
			periodDays = ChronoUnit.DAYS.between(startDate, today);
		}

		return MyActivityResponse.builder()
			.level(0)
			.badges(Collections.emptyList())
			.startDate(DateUtils.convertStringDotType(startDate))
			.periodDays(periodDays)
			.build();
	}
}
