package com.coachmybody.common.dto;

import org.springframework.http.HttpHeaders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class HeaderDto {
	private String token;
	private String os;
	private String appVersion;

	public static HeaderDto of(HttpHeaders headers) {
		String token = headers.toSingleValueMap().get("authorization");
		String os = headers.toSingleValueMap().get("OS");
		String appVersion = headers.toSingleValueMap().get("App-Version");

		return HeaderDto.builder()
			.token(token == null ? "NONE" : token.substring(7))
			.os(os == null ? "NONE" : os)
			.appVersion(appVersion == null ? "0.0.0" : appVersion)
			.build();
	}
}
