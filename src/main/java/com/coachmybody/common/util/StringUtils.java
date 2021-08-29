package com.coachmybody.common.util;

import com.coachmybody.exercise.domain.ExerciseLabSet;
import com.coachmybody.exercise.domain.ExerciseRecord;
import com.coachmybody.exercise.domain.ExerciseTimeSet;
import com.coachmybody.exercise.type.ExerciseRecordType;

public class StringUtils {
	private static final String TIME_SET_STR = "%d분 %d초 %d세트";
	private static final String LAB_SET_STR = "%d회 %d세트";

	public static String getExerciseSets(ExerciseRecord exerciseRecord) {
		ExerciseRecordType type = exerciseRecord.getType();

		if (type == ExerciseRecordType.TIME_SET) {
			int tsMinutes = ((ExerciseTimeSet)exerciseRecord).getExerciseMinutes();
			int tsSeconds = ((ExerciseTimeSet)exerciseRecord).getExerciseSeconds();
			int tsSet = ((ExerciseTimeSet)exerciseRecord).getExerciseSet();
			return String.format(TIME_SET_STR, tsMinutes, tsSeconds, tsSet);
		}

		int lsLab = ((ExerciseLabSet)exerciseRecord).getExerciseLab();
		int lsSet = ((ExerciseLabSet)exerciseRecord).getExerciseSet();
		return String.format(LAB_SET_STR, lsLab, lsSet);
	}
}
