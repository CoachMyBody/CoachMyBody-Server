package com.coachmybody.record.interfaces.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "눈바디 등록 요청")
@Getter
public class NunbodyCreateRequest {
	@ApiModelProperty(value = "이미지", required = true)
	@NotNull(message = "The imageUri must not be Null")
	String imageUri;

	@ApiModelProperty(value = "태그", required = true)
	@NotNull(message = "The tag must not be Null")
	String tag;
}
