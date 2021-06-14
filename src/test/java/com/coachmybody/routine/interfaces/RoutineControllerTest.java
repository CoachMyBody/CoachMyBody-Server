package com.coachmybody.routine.interfaces;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.Mockito.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.coachmybody.routine.application.RoutineService;
import com.coachmybody.routine.interfaces.dto.RoutineCreateRequest;
import com.coachmybody.test.ApiTest;
import com.coachmybody.user.application.UserService;
import com.coachmybody.user.domain.User;
import com.coachmybody.user.type.LoginType;

import io.restassured.http.ContentType;

class RoutineControllerTest extends ApiTest {
	@MockBean
	private UserService userService;

	@MockBean
	private RoutineService routineService;

	@Test
	void createRoutine() {
		String mockAccessToken = "mockAccessToken";
		String authorization = String.format("Bearer %s", mockAccessToken);
		mockLogin(mockAccessToken);

		String title = "title";
		User user = User.builder()
			.id(UUID.randomUUID())
			.socialId("socialId")
			.loginType(LoginType.KAKAO)
			.nickname("nickname")
			.email("email")
			.build();

		doNothing().when(routineService).create(title, user);

		RoutineCreateRequest routineCreateRequest = new RoutineCreateRequest("title");

		given()
			.contentType(ContentType.JSON)
			.headers("Authorization", authorization)
			.body(routineCreateRequest)
			.when()
			.post("/api/v1/routines")
			.then()
			.statusCode(201);
	}

	private void mockLogin(String mockAccessToken) {
		BDDMockito.given(userService.isValidToken(mockAccessToken))
			.willReturn(true);
	}
}