package com.coachmybody.user.interfaces.dto;

import javax.validation.constraints.Email;

import com.coachmybody.user.type.LoginType;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NonNull;
import lombok.Value;

@ApiModel(description = "회원가입 요청")
@Value
public class RegisterRequest {

	@ApiModelProperty(value = "아이디", required = true)
	String socialId;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@ApiModelProperty(value = "로그인 타입", example = "KAKAO", required = true)
	LoginType loginType;

	@ApiModelProperty(value = "닉네임", required = true)
	@NonNull
	String nickname;

	@ApiModelProperty(value = "이메일", required = true)
	@Email
	String email;
}
