package com.coachmybody.user.interfaces.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(description = "토큰 리프레시 요청")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RefreshRequest {

	@ApiModelProperty(value = "리프레시 토큰", required = true)
	@NotNull(message = "The refreshToken must be not Null")
	String refreshToken;
}
