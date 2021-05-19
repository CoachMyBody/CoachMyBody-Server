package com.coachmybody.record.interfaces.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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

	@ApiModelProperty(value = "날짜", example = "2021-05-19", required = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate date;
}
