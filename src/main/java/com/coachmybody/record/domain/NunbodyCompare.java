package com.coachmybody.record.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.coachmybody.user.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class NunbodyCompare {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "user_id", nullable = false)
	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	@JoinColumn(name = "before_nunbody_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Nunbody beforeNunbody;

	@JoinColumn(name = "after_nunbody_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Nunbody afterNunbody;

	public NunbodyCompare(User user) {
		this.user = user;
	}
}
