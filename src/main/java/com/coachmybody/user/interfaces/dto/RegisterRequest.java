package com.coachmybody.user.interfaces.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.coachmybody.user.type.LoginType;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@ApiModel(description = "회원가입 요청")
@Value
public class RegisterRequest {

	@ApiModelProperty(value = "아이디", required = true)
	@NotNull(message = "The socialId must not be Null")
	String socialId;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@ApiModelProperty(value = "로그인 타입", example = "KAKAO", required = true)
	@NotNull(message = "The loginType must not be Null")
	LoginType loginType;

	@ApiModelProperty(value = "닉네임", required = true)
	@NotNull(message = "The nickname must not be Null")
	String nickname;

	@ApiModelProperty(value = "이메일", example = "test@cmb.com", required = true)
	@Email
	String email;
}
