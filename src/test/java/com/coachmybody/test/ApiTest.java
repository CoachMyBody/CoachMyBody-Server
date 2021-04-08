package com.coachmybody.test;

import static com.coachmybody.common.util.JsonUtils.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.context.WebApplicationContext;

import com.coachmybody.CoachMyBodyApiApplication;

import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest(
	classes = {CoachMyBodyApiApplication.class},
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ApiTest {
	@Autowired
	private WebApplicationContext webApplicationContext;

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() {
		RestAssured.reset();
		RestAssured.port = port;
		RestAssured.config()
			.objectMapperConfig(
				new ObjectMapperConfig().jackson2ObjectMapperFactory((type, s) -> JSON_MAPPER)
			);

		RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	@AfterEach
	void tearDown() { RestAssured.reset(); }
}
