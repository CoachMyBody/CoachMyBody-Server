package com.coachmybody.common.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PageResponse<T> {
	@ApiModelProperty(value = "전체 데이터 수", position = 1, required = true)
	@Builder.Default
	Long totalCount = 0L;

	@ApiModelProperty(value = "조회된 데이터 수", position = 10)
	Integer size;

	@ApiModelProperty(value = "다음 페이지 여부", position = 20)
	Boolean hasNext;

	@ApiModelProperty(value = "데이터", position = 30)
	@Builder.Default
	List<T> data = List.of();

	@JsonIgnore
	Object[] cursor;

	public static <I, O> PageResponse<O> of(Pageable pageable, List<O> data) {
		long start = pageable.getOffset();
		long end = (start + pageable.getPageSize()) > data.size() ? data.size() : (start + pageable.getPageSize());
		Page<O> pages = new PageImpl<>(data.subList((int)start, (int)end), pageable, data.size());
		return PageResponse.<O>builder()
			.totalCount(pages.getTotalElements())
			.size(pages.getSize())
			.hasNext(pages.hasNext())
			.data(pages.getContent())
			.build();
	}

	public static <T> PageResponse<T> of(List<T> data) {
		return PageResponse.<T>builder()
			.totalCount((long)data.size())
			.size(data.size())
			.data(data)
			.hasNext(false)
			.build();
	}

	public static <T> PageResponse<T> of(long total, List<T> data) {
		return PageResponse.<T>builder()
			.totalCount(total)
			.data(data)
			.build();
	}

	public static <I, O> PageResponse<O> of(Page<I> page, List<O> data) {
		return PageResponse.<O>builder()
			.totalCount(page.getTotalElements())
			.size(page.getSize())
			.hasNext(page.hasNext())
			.data(data)
			.build();
	}

	public static <I, O> PageResponse<O> of(Page<I> page, Function<I, O> converter) {
		return PageResponse.<O>builder()
			.totalCount(page.getTotalElements())
			.size(page.getSize())
			.hasNext(page.hasNext())
			.data(page.getContent().stream()
				.map(converter)
				.collect(Collectors.toList()))
			.build();
	}
}
