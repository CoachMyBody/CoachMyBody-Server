package com.coachmybody.common.util;

import com.coachmybody.exercise.domain.ExerciseLabSet;
import com.coachmybody.exercise.domain.ExerciseRecord;
import com.coachmybody.exercise.domain.ExerciseTimeSet;
import com.coachmybody.exercise.type.ExerciseRecordType;

public class StringUtils {
	public static String getExerciseSets(ExerciseRecord exerciseRecord) {
		ExerciseRecordType type = exerciseRecord.getType();
		String sets = "";
		switch (type) {
			case TIME_SET:
				int tsMinutes = ((ExerciseTimeSet)exerciseRecord).getExerciseMinutes();
				int tsSeconds = ((ExerciseTimeSet)exerciseRecord).getExerciseSeconds();
				int tsSet = ((ExerciseTimeSet)exerciseRecord).getExerciseSet();
				sets = tsMinutes + "분 " + tsSeconds + "초 " + tsSet + "세트";
				break;
			case LAB_SET:
				int lsLab = ((ExerciseLabSet)exerciseRecord).getExerciseLab();
				int lsSet = ((ExerciseLabSet)exerciseRecord).getExerciseSet();
				sets = lsLab + "회 " + lsSet + "세트";
				break;
		}
		return sets;
	}
}
