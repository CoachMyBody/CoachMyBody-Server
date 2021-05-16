package com.coachmybody.record.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.coachmybody.routine.domain.Routine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class RecordRoutine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recordRoutine", cascade = CascadeType.ALL)
	private List<RecordRoutineExercise> exercises;

	public static RecordRoutine of(Routine routine) {
		return RecordRoutine.builder()
			.title(routine.getTitle())
			.build();
	}
}