package com.coachmybody.user.interfaces.dto;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@ApiModel(description = "유저 정보 수정 요청")
@Value
public class UserUpdateRequest {
	@ApiModelProperty(value = "닉네임")
	String nickname;

	@ApiModelProperty(value = "생년월일")
	LocalDate birth;

	@ApiModelProperty(value = "연락처")
	String phone;
}
