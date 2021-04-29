package com.coachmybody.record.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.coachmybody.record.interfaces.dto.RecordCreateRequest;
import com.coachmybody.routine.domain.Routine;
import com.coachmybody.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Record {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer hours;

	private Integer minutes;

	private String feedbackBySelf;

	private String feedbackByTrainer;

	@JoinColumn(name = "user_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@JoinColumn(name = "routine_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Routine routine;

	public static Record of(User user, RecordCreateRequest request) {
		return Record.builder()
			.hours(request.getHours())
			.minutes(request.getMinutes())
			.feedbackBySelf(request.getFeedbackBySelf())
			.user(user)
			.routine(request.getRoutine())
			.build();
	}
}