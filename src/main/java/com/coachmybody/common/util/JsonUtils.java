package com.coachmybody.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {
	private final static String PARSED_ERROR_MESSAGE = "The result can not be parsed";
	public final static ObjectMapper JSON_MAPPER;

	static {
		JSON_MAPPER = new ObjectMapper().registerModules(
				new JavaTimeModule())
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	public static <T> String toJsonString(T value) {
		try {
			return JsonUtils.JSON_MAPPER.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(PARSED_ERROR_MESSAGE);
		}
	}
}
