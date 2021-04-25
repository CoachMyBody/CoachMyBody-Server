package com.coachmybody.routine.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.coachmybody.user.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Routine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@JoinColumn(name = "user_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "routine", cascade = CascadeType.ALL)
	private List<RoutineExercise> exercises;

	public Routine(String title, User user) {
		this.title = title;
		this.user = user;
	}
}
