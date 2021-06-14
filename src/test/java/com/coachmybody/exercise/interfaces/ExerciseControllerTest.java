package com.coachmybody.exercise.interfaces;

import static com.coachmybody.exercise.type.ExerciseRecordType.*;
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
import com.coachmybody.exercise.type.MuscleType;
import com.coachmybody.test.ApiTest;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.restassured.http.ContentType;

class ExerciseControllerTest extends ApiTest {
	@MockBean
	private ExerciseService exerciseService;

	@Test
	void findExercise() throws JsonProcessingException {
		long exerciseId = 1L;
		String name = "name";
		String imageUri = "imageUri";
		ExerciseCategoryType category = ExerciseCategoryType.CARDIO;
		BodyPartType bodyPart = BodyPartType.FULL_BODY;
		List<BodyPartSubType> bodyPartSubs = List.of(BodyPartSubType.ABS);
		ExerciseTimeDto exerciseTime = new ExerciseTimeDto(LAB_SET, 1, null, null, 3);
		List<MuscleType> muscles = List.of(MuscleType.ERECTOR_SPINAE);
		List<String> tags = List.of("tag");
		ExerciseSimpleResponse exerciseSimpleResponse = ExerciseSimpleResponse.builder()
			.id(1L)
			.name("name")
			.imageUri("imageUri")
			.tags(List.of("tag"))
			.sets("sets")
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

		ExerciseSimpleResponse relateExercise = relatedExercises.get(0);

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
			.body("exerciseTime.type", equalTo(LAB_SET.name()))
			.body("exerciseTime.exerciseLab", equalTo(1))
			.body("exerciseTime.exerciseSet", equalTo(3))
			.body("muscles", equalTo(muscles.stream().map(Enum::toString).collect(Collectors.toList())))
			.body("tags", equalTo(tags))
			.body("relatedExercises[0].id", equalTo(relateExercise.getId().intValue()))
			.body("relatedExercises[0].name", equalTo(relateExercise.getName()))
			.body("relatedExercises[0].imageUri", equalTo(relateExercise.getImageUri()))
			.body("relatedExercises[0].tags", equalTo(relateExercise.getTags()))
			.body("relatedExercises[0].sets", equalTo(relateExercise.getSets()));
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