package com.coachmybody.routine.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(exclude = "createdAt")
@Getter
@IdClass(RoutineBookmarkKey.class)
@Entity
public class RoutineBookmark {
	@Id
	@Column(name = "routine_id", nullable = false)
	private Long routineId;

	@Id
	@Type(type = "uuid-char")
	@Column(name = "user_id", nullable = false)
	private UUID userId;

	@CreatedDate
	private LocalDateTime createdAt;

	public RoutineBookmark() {
	}

	public RoutineBookmark(Long routineId, UUID userId) {
		this.routineId = routineId;
		this.userId = userId;
	}

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
	}
}
