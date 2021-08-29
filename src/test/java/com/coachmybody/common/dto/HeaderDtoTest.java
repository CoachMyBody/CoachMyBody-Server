package com.coachmybody.common.dto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

class HeaderDtoTest {
	private HttpHeaders httpHeaders;

	@BeforeEach
	void init() {
		httpHeaders = new HttpHeaders();
		httpHeaders.add("authorization", "Bearer tokenValue");
		httpHeaders.add("OS", "iOS");
		httpHeaders.add("App-Version", "1.0.0");
	}

	@DisplayName(value = "Request Header를 HeaderDto 객체로 반환")
	@Test
	void convertHeaderDto() {
		String token = "tokenValue";
		String os = "iOS";
		String appVersion = "1.0.0";

		HeaderDto headerDto = HeaderDto.of(httpHeaders);

		assertThat(headerDto.getToken()).isEqualTo(token);
		assertThat(headerDto.getOs()).isEqualTo(os);
		assertThat(headerDto.getAppVersion()).isEqualTo(appVersion);
	}
}