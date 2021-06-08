package com.coachmybody.routine.domain;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RoutineBookmarkKey implements Serializable {
	private Long routineId;
	private UUID userId;
}