package com.coachmybody.user.interfaces.dto;

import com.coachmybody.coach.domain.Coach;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "강사 연동 정보 조회 응답")
@Builder
@Value
public class UserCoachConnectionResponse {
	Boolean isConnected;

	public static UserCoachConnectionResponse of(Coach coach) {
		return UserCoachConnectionResponse.builder()
			.isConnected(coach.isConnected())
			.build();
	}
}
