package com.coachmybody.routine.interfaces;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.coachmybody.common.exception.NotFoundEntityException;
import com.coachmybody.routine.application.RoutineService;
import com.coachmybody.routine.interfaces.dto.RoutineCreateRequest;
import com.coachmybody.routine.interfaces.dto.RoutineDetailResponse;
import com.coachmybody.routine.interfaces.dto.RoutineExerciseResponse;
import com.coachmybody.test.ApiTest;
import com.coachmybody.user.application.UserService;
import com.coachmybody.user.domain.User;
import com.coachmybody.user.type.LoginType;

import io.restassured.http.ContentType;

class RoutineControllerTest extends ApiTest {
	@MockBean
	private RoutineService routineService;

	@MockBean
	private UserService userService;

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

	@Test
	void createRoutineWithoutLogin() {
		RoutineCreateRequest routineCreateRequest = new RoutineCreateRequest("title");

		given()
			.contentType(ContentType.JSON)
			.body(routineCreateRequest)
			.when()
			.post("/api/v1/routines")
			.then()
			.statusCode(401);
	}

	@Test
	void createRoutineBadRequest() {
		String mockAccessToken = "mockAccessToken";
		String authorization = String.format("Bearer %s", mockAccessToken);
		mockLogin(mockAccessToken);

		RoutineCreateRequest routineCreateRequest = new RoutineCreateRequest(null);

		given()
			.contentType(ContentType.JSON)
			.headers("Authorization", authorization)
			.body(routineCreateRequest)
			.when()
			.post("/api/v1/routines")
			.then()
			.statusCode(400);
	}

	/*@Test
	void findRoutine() {
		String mockAccessToken = "mockAccessToken";
		String authorization = String.format("Bearer %s", mockAccessToken);
		mockLogin(mockAccessToken);

		Long routineId = 1L;
		UUID userId = UUID.randomUUID();

		RoutineExerciseResponse exercise = RoutineExerciseResponse.builder()
			.id(1L)
			.name("name")
			.imageUri("imageUri")
			.exerciseLab(1)
			.exerciseSet(3)
			.build();

		RoutineDetailResponse routine = RoutineDetailResponse.builder()
			.id(routineId)
			.title("title")
			.content("content")
			.profileUri("profileUri")
			.nickname("nickname")
			.exercises(List.of(exercise))
			.tags(List.of("tag"))
			.isBookmarked(true)
			.build();

		BDDMockito.given(routineService.findRoutineById(routineId, userId))
			.willReturn(routine);

		given()
			.contentType(ContentType.JSON)
			.headers("Authorization", authorization)
			.when()
			.get("/api/v1/routines/{routineId}", routineId)
			.then()
			.statusCode(200);
	}

	@Test
	void findNonexistentRoutine() {
		String mockAccessToken = "mockAccessToken";
		String authorization = String.format("Bearer %s", mockAccessToken);
		mockLogin(mockAccessToken);

		long routineId = 1L;
		UUID userId = UUID.randomUUID();

		BDDMockito.given(routineService.findRoutineById(routineId, userId))
			.willThrow(new NotFoundEntityException());

		given()
			.contentType(ContentType.JSON)
			.headers("Authorization", authorization)
			.when()
			.get("/api/v1/routines/{routineId}", routineId)
			.then()
			.statusCode(404);
	}

	@Test
	void updateTitle() {
		String mockAccessToken = "mockAccessToken";
		String authorization = String.format("Bearer %s", mockAccessToken);
		mockLogin(mockAccessToken);

		long routineId = 1L;
		String newTitle = "newTitle";

		doNothing().when(routineService).updateTitle(routineId, newTitle);

		given()
			.queryParam("newTitle", newTitle)
			.contentType(ContentType.JSON)
			.headers("Authorization", authorization)
			.when()
			.patch("/api/v1/routines/{routineId}/title", routineId)
			.then()
			.statusCode(200);
	}

	@Test
	void updateTitleNonExistentRoutine() {
		String mockAccessToken = "mockAccessToken";
		String authorization = String.format("Bearer %s", mockAccessToken);
		mockLogin(mockAccessToken);

		long routineId = 1L;
		String newTitle = "newTitle";


		doThrow(new NotFoundEntityException()).when(routineService).updateTitle(routineId, newTitle);

		given()
			.queryParam("newTitle", newTitle)
			.contentType(ContentType.JSON)
			.headers("Authorization", authorization)
			.when()
			.patch("/api/v1/routines/{routineId}/title", routineId)
			.then()
			.statusCode(404);
	}*/

	private void mockLogin(String mockAccessToken) {
		BDDMockito.given(userService.isValidToken(mockAccessToken))
			.willReturn(true);
	}
}