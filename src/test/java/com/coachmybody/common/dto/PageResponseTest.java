package com.coachmybody.common.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

class PageResponseTest {
	private final List<Integer> data = new ArrayList<>();

	@BeforeEach
	void init() {
		for (int i = 1; i <= 100; i++) {
			data.add(i);
		}
	}

	@DisplayName(value = "페이징 처리 테스트")
	@Test
	void pagination() {
		// given
		long totalCount = 100L;
		int size = 20;
		List<Integer> pageData = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			pageData.add(i);
		}

		// when
		PageRequest pageRequest = PageRequest.of(0, size);
		PageResponse<Integer> pageResponse = PageResponse.of(pageRequest, data);

		// then
		assertThat(pageResponse.getTotalCount()).isEqualTo(totalCount);
		assertThat(pageResponse.getSize()).isEqualTo(size);
		assertThat(pageResponse.getHasNext()).isTrue();
		assertThat(pageResponse.getData()).containsAll(pageData);
	}
}