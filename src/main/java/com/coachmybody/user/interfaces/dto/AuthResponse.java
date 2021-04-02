package com.coachmybody.user.interfaces.dto;

import java.time.Instant;

import com.coachmybody.user.domain.UserAuth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "로그인 응답")
@Builder
@Value
public class AuthResponse {

	@ApiModelProperty(value = "액세스 토큰", required = true)
	String accessToken;

	@ApiModelProperty(value = "리프레시 토큰", required = true)
	String refreshToken;

	@ApiModelProperty(value = "토큰 만료 날짜", required = true)
	Instant expiredAt;

	public static AuthResponse of(UserAuth userAuth) {
		return AuthResponse.builder()
			.accessToken(userAuth.getAccessToken())
			.refreshToken(userAuth.getRefreshToken())
			.expiredAt(userAuth.getExpiredAt())
			.build();
	}
}
