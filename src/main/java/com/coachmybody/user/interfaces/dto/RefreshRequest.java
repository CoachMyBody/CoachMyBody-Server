package com.coachmybody.user.interfaces.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "토큰 리프레시 요청")
@Getter
public class RefreshRequest {

	@ApiModelProperty(value = "리프레시 토큰", required = true)
	@NotNull(message = "The refreshToken must be not Null")
	String refreshToken;
}
