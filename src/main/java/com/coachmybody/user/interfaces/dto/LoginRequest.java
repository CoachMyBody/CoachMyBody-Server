package com.coachmybody.user.interfaces.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "로그인 요청")
@Getter
public class LoginRequest {

	@NotNull(message = "The socialId must not be Null")
	@ApiModelProperty(value = "소셜 고유 id", required = true)
	String socialId;
}