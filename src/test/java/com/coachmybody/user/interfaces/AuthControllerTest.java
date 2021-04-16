package com.coachmybody.user.interfaces;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.coachmybody.common.exception.DuplicatedEntityException;
import com.coachmybody.test.ApiTest;
import com.coachmybody.user.interfaces.dto.RegisterRequest;
import com.coachmybody.user.application.UserService;
import com.coachmybody.user.type.LoginType;

import io.restassured.http.ContentType;

class AuthControllerTest extends ApiTest {
	@MockBean
	private UserService userService;

	@Test
	void register() {
		RegisterRequest registerRequest = new RegisterRequest("socialId", LoginType.KAKAO, "nickname", "email@email.co");

		given()
			.contentType(ContentType.JSON)
			.body(registerRequest)
			.when()
			.post("/api/v1/auth/register")
			.then()
			.statusCode(201);
	}

	@Test
	void registerDuplicated() {
		RegisterRequest duplicatedRequest = new RegisterRequest("socialId", LoginType.KAKAO, "nickname", "email@email.co");

		doThrow(new DuplicatedEntityException())
			.when(userService)
			.register(duplicatedRequest);

		given()
			.contentType(ContentType.JSON)
			.body(duplicatedRequest)
			.when()
			.post("/api/v1/auth/register")
			.then()
			.statusCode(409);
	}
}