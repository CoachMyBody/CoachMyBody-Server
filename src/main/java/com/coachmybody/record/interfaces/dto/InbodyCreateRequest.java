package com.coachmybody.record.interfaces.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "인바디 등록 요청")
@Getter
public class InbodyCreateRequest {
	@ApiModelProperty(value = "체중", required = true)
	@NotNull(message = "The weight must not be Null")
	Float weight;

	@ApiModelProperty(value = "골격근량", required = true)
	@NotNull(message = "The skeletalMuscleMass must not be Null")
	Float skeletalMuscleMass;

	@ApiModelProperty(value = "체지방량", required = true)
	@NotNull(message = "The bodyFatMass must not be Null")
	Float bodyFatMass;
}
