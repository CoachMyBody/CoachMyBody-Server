package com.coachmybody.user.domain;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.coachmybody.common.util.DateUtils;
import com.coachmybody.common.util.UuidUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class UserAuth {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@Type(type = "uuid-char")
	private UUID userId;

	private String accessToken;

	private String refreshToken;

	private Instant expiredAt;

	@Builder.Default
	private Instant createdDate = Instant.now();


	public static UserAuth newAuth(UUID userId) {
		Instant now = Instant.now();

		return UserAuth.builder()
			.userId(userId)
			.accessToken(UuidUtils.generateUuid())
			.refreshToken(UuidUtils.generateUuid())
			.expiredAt(DateUtils.calculateExpireAt(now))
			.build();
	}

	public void refresh() {
		Instant now = Instant.now();
		this.accessToken = UuidUtils.generateUuid();
		this.expiredAt = DateUtils.calculateExpireAt(now);
	}
}
