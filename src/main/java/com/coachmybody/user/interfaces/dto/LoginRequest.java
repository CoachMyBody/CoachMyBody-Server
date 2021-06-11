package com.coachmybody.user.interfaces.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(description = "로그인 요청")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginRequest {
	@NotNull(message = "The socialId must not be Null")
	@ApiModelProperty(value = "소셜 고유 id", required = true)
	String socialId;
}