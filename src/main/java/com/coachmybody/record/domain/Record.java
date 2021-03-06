package com.coachmybody.record.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.coachmybody.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Record {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate date;

	@Column(nullable = false)
	private Integer hours;

	@Column(nullable = false)
	private Integer minutes;

	private String feedbackBySelf;

	private String feedbackByTrainer;

	@Builder.Default
	private LocalDateTime createdAt = LocalDateTime.now();

	@JoinColumn(name = "user_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@JoinColumn(name = "record_routine_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private RecordRoutine recordRoutine;

	@JoinColumn(name = "inbody_id", nullable = false)
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Inbody inbody;

	@JoinColumn(name = "nunbody_id", nullable = false)
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Nunbody nunbody;
}