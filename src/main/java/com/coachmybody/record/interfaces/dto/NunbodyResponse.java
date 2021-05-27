package com.coachmybody.record.interfaces.dto;

import com.coachmybody.common.util.DateUtils;
import com.coachmybody.record.domain.Nunbody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@ApiModel(description = "눈바디 조회 응답")
@Builder
@Value
public class NunbodyResponse {
	@ApiModelProperty(value = "눈바디 아이디", required = true)
	Long id;

	@ApiModelProperty(value = "이미지", required = true)
	String imageUri;

	@ApiModelProperty(value = "등록일", required = true)
	String date;

	@ApiModelProperty(value = "태그", required = true)
	String tag;

	public static NunbodyResponse of(Nunbody nunbody) {
		return NunbodyResponse.builder()
			.id(nunbody.getId())
			.imageUri(nunbody.getImageUri())
			.date(DateUtils.convertDateToString(nunbody.getDate()))
			.tag(nunbody.getTag() == null ? "" : nunbody.getTag())
			.build();
	}
}
