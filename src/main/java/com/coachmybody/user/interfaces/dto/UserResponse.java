package com.coachmybody.user.interfaces.dto;

import com.coachmybody.user.domain.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "유저 정보 조회 응답")
@Builder
@Value
public class UserResponse {
	@ApiModelProperty(value = "유저 아이디", required = true)
	String id;

	@ApiModelProperty(value = "소셜 아이디", required = true)
	String socialId;

	@ApiModelProperty(value = "닉네임", required = true)
	String nickname;

	@ApiModelProperty(value = "이메일", required = true)
	String email;

	public static UserResponse of(User user) {
		return UserResponse.builder()
			.id(user.getId().toString())
			.socialId(user.getSocialId())
			.nickname(user.getNickname())
			.email(user.getEmail())
			.build();
	}
}
