package com.coachmybody.record.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.coachmybody.record.interfaces.dto.InbodyCreateRequest;
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
public class Inbody {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Float weight;

	private Float skeletalMuscleMass;

	private Float bodyFatMass;

	private LocalDate date;

	@Builder.Default
	private LocalDateTime createdAt = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public static Inbody of(InbodyCreateRequest request, User user) {
		return Inbody.builder()
			.weight(request.getWeight())
			.skeletalMuscleMass(request.getSkeletalMuscleMass())
			.bodyFatMass(request.getBodyFatMass())
			.date(request.getDate())
			.user(user)
			.build();
	}
}