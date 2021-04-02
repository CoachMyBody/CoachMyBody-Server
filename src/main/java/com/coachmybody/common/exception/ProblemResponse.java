package com.coachmybody.common.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@ApiModel(description = "에러 응답")
@Value
public class ProblemResponse {

	@ApiModelProperty(value = "에러 제목", required = true)
	String title;

	@ApiModelProperty(value = "에러 코드", required = true)
	int status;

	@ApiModelProperty(value = "에러 발생 이유", required = true)
	String reason;
}
