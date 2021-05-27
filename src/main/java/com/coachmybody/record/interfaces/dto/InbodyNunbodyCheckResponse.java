package com.coachmybody.record.interfaces.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class InbodyNunbodyCheckResponse {
	@ApiModelProperty(value = "인바디 여부", required = true)
	Boolean hasInbody;

	@ApiModelProperty(value = "눈바디 여부", required = true)
	Boolean hasNunbody;
}
