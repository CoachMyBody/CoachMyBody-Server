package com.coachmybody.user.domain;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

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
	@GeneratedValue
	@Type(type = "uuid-char")
	private UUID id;

	@Column(unique = true)
	private String socialId;

	@Enumerated(value = EnumType.STRING)
	private LoginType loginType;

	private String nickname;

	private String email;

	private LocalDate birth;

	private String phone;

	public static User of(RegisterRequest request) {
		return User.builder()
			.id(UUID.randomUUID())
			.socialId(request.getSocialId())
			.loginType(request.getLoginType())
			.nickname(request.getNickname())
			.email(request.getEmail())
			.build();
	}
}
