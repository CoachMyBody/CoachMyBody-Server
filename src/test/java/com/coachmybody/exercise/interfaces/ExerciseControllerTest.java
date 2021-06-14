package com.coachmybody.exercise.interfaces;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.coachmybody.common.exception.NotFoundEntityException;
import com.coachmybody.exercise.application.ExerciseService;
import com.coachmybody.exercise.interfaces.dto.ExerciseDetailResponse;
import com.coachmybody.exercise.interfaces.dto.ExerciseSimpleResponse;
import com.coachmybody.exercise.interfaces.dto.ExerciseTimeDto;
import com.coachmybody.exercise.type.BodyPartSubType;
import com.coachmybody.exercise.type.BodyPartType;
import com.coachmybody.exercise.type.ExerciseCategoryType;
import com.coachmybody.exercise.type.ExerciseRecordType;
import com.coachmybody.exercise.type.MuscleType;
import com.coachmybody.test.ApiTest;

import io.restassured.http.ContentType;

class ExerciseControllerTest extends ApiTest {
	@MockBean
	private ExerciseService exerciseService;

	@Test
	void findExercise() {
		long exerciseId = 1L;
		String name = "name";
		String imageUri = "imageUri";
		ExerciseCategoryType category = ExerciseCategoryType.CARDIO;
		BodyPartType bodyPart = BodyPartType.FULL_BODY;
		List<BodyPartSubType> bodyPartSubs = List.of(BodyPartSubType.ABS);
		ExerciseTimeDto exerciseTime = new ExerciseTimeDto(ExerciseRecordType.LAB_SET, 1, null, null, 3);
		List<MuscleType> muscles = List.of(MuscleType.ERECTOR_SPINAE);
		List<String> tags = List.of("tag");
		ExerciseSimpleResponse exerciseSimpleResponse = ExerciseSimpleResponse.builder()
			.build();
		List<ExerciseSimpleResponse> relatedExercises = List.of(exerciseSimpleResponse);

		ExerciseDetailResponse exercise = ExerciseDetailResponse.builder()
			.id(exerciseId)
			.name(name)
			.imageUri(imageUri)
			.category(category)
			.bodyPart(bodyPart)
			.bodyPartSubs(bodyPartSubs)
			.exerciseTime(exerciseTime)
			.muscles(muscles)
			.tags(tags)
			.relatedExercises(relatedExercises)
			.build();

		BDDMockito.given(exerciseService.findExerciseById(exerciseId))
			.willReturn(exercise);

		given()
			.contentType(ContentType.JSON)
			.when()
			.get("/api/v1/exercises/{exerciseId}", exerciseId)
			.then()
			.statusCode(200)
			.body("id", equalTo((int)exerciseId))
			.body("name", equalTo(name))
			.body("imageUri", equalTo(imageUri))
			.body("category", equalTo(category.toString()))
			.body("bodyPart", equalTo(bodyPart.toString()))
			.body("bodyPartSubs", equalTo(bodyPartSubs.stream().map(Enum::toString).collect(Collectors.toList())))
			.body("exerciseTime", equalTo(exerciseTime))
			.body("muscles", equalTo(muscles.stream().map(Enum::toString).collect(Collectors.toList())))
			.body("tags", equalTo(tags))
			.body("relatedExercises", equalTo(relatedExercises));
	}

	@Test
	void findNonexistentExercise() {
		long exerciseId = 1L;

		BDDMockito.given(exerciseService.findExerciseById(exerciseId))
			.willThrow(new NotFoundEntityException());

		given()
			.contentType(ContentType.JSON)
			.when()
			.get("/api/v1/exercises/{exerciseId}", exerciseId)
			.then()
			.statusCode(404);
	}
}