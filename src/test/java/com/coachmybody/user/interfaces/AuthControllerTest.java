package com.coachmybody.user.interfaces;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.coachmybody.common.exception.DuplicatedEntityException;
import com.coachmybody.common.exception.InvalidRefreshTokenException;
import com.coachmybody.common.exception.NotFoundEntityException;
import com.coachmybody.common.util.DateUtils;
import com.coachmybody.common.util.JsonUtils;
import com.coachmybody.test.ApiTest;
import com.coachmybody.user.application.UserService;
import com.coachmybody.user.domain.UserAuth;
import com.coachmybody.user.interfaces.dto.AuthResponse;
import com.coachmybody.user.interfaces.dto.LoginRequest;
import com.coachmybody.user.interfaces.dto.RefreshRequest;
import com.coachmybody.user.interfaces.dto.RegisterRequest;
import com.coachmybody.user.type.LoginType;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.restassured.http.ContentType;

class AuthControllerTest extends ApiTest {
	@MockBean
	private UserService userService;

	@Test
	void register() {
		RegisterRequest registerRequest = new RegisterRequest("socialId", LoginType.KAKAO, "nickname",
			"email@email.com");

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
		RegisterRequest duplicatedRequest = new RegisterRequest("socialId", LoginType.KAKAO, "nickname",
			"email@email.com");

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

	@Test
	void registerBadRequest() {
		RegisterRequest registerBadRequest = new RegisterRequest("socialId", LoginType.KAKAO, null, "email@email.com");

		given()
			.contentType(ContentType.JSON)
			.body(registerBadRequest)
			.when()
			.post("/api/v1/auth/register")
			.then()
			.statusCode(400);
	}

	@Test
	void login() throws JsonProcessingException {
		String socialId = "socialId";
		UUID userId = UUID.randomUUID();
		String accessToken = "accessToken";
		String refreshToken = "refreshToken";
		Instant now = Instant.now();
		Instant expiredAt = DateUtils.calculateExpireAt(now);

		UserAuth auth = UserAuth.builder()
			.id(1L)
			.userId(userId)
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.expiredAt(expiredAt)
			.createdDate(now)
			.build();

		BDDMockito.given(userService.login(socialId))
			.willReturn(AuthResponse.of(auth));

		LoginRequest loginRequest = new LoginRequest(socialId);

		given()
			.contentType(ContentType.JSON)
			.body(loginRequest)
			.when()
			.post("/api/v1/auth/login")
			.then()
			.statusCode(200)
			.body(
				"accessToken", equalTo(accessToken),
				"refreshToken", equalTo(refreshToken),
				"expiredAt", equalTo(JsonUtils.JSON_MAPPER.writeValueAsString(expiredAt).replaceAll("\"", ""))
			);
	}

	@Test
	void loginWithoutRegister() {
		String socialId = "socialId";
		LoginRequest loginRequest = new LoginRequest(socialId);

		BDDMockito.given(userService.login(socialId))
			.willThrow(new NotFoundEntityException());

		given()
			.contentType(ContentType.JSON)
			.body(loginRequest)
			.when()
			.post("/api/v1/auth/login")
			.then()
			.statusCode(404);
	}

	@Test
	void refresh() throws JsonProcessingException {
		UUID userId = UUID.randomUUID();
		String accessToken = "accessToken";
		String refreshToken = "refreshToken";
		Instant createdDate = Instant.now();
		Instant expiredAt = DateUtils.calculateExpireAt(createdDate);

		UserAuth userAuth = UserAuth.builder()
			.id(1L)
			.userId(userId)
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.createdDate(createdDate)
			.expiredAt(expiredAt)
			.build();

		BDDMockito.given(userService.refresh(refreshToken))
			.willReturn(AuthResponse.of(userAuth));

		RefreshRequest refreshRequest = new RefreshRequest("refreshToken");

		given()
			.contentType(ContentType.JSON)
			.body(refreshRequest)
			.when()
			.put("/api/v1/auth/refresh")
			.then()
			.statusCode(200)
			.body(
				"accessToken", equalTo(accessToken),
				"refreshToken", equalTo(refreshToken),
				"expiredAt", equalTo(JsonUtils.JSON_MAPPER.writeValueAsString(expiredAt).replaceAll("\"", ""))
			);
	}

	@Test
	void refreshTokenExpired() {
		String refreshToken = "refreshToken";
		RefreshRequest refreshRequest = new RefreshRequest(refreshToken);

		BDDMockito.given(userService.refresh(refreshToken))
			.willThrow(new InvalidRefreshTokenException());

		given()
			.contentType(ContentType.JSON)
			.body(refreshRequest)
			.when()
			.put("/api/v1/auth/refresh")
			.then()
			.statusCode(401);
	}
}