package com.coachmybody.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.coachmybody.user.interfaces.dto.RegisterRequest;
import com.coachmybody.user.type.LoginType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String socialId;

	@Enumerated(value = EnumType.STRING)
	private LoginType loginType;

	private String nickname;

	private String email;

	public static User of(RegisterRequest request) {
		return User.builder()
			.socialId(request.getSocialId())
			.loginType(request.getLoginType())
			.nickname(request.getNickname())
			.email(request.getEmail())
			.build();
	}
}
