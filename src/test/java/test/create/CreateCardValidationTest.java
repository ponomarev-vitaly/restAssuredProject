package test.create;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.CardBodyValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.CardBodyValidationArgumentsProvider;
import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;

import java.util.Map;

public class CreateCardValidationTest extends BaseTest {
    // This is solution 1 of the CardBodyValidationArguentsProvider class. Not all tests are working correctly.

//    @ParameterizedTest
//    @ArgumentsSource(CardBodyValidationArgumentsProvider.class)
//    public void checkCreateCardWithInvalidName(CardBodyValidationArgumentsHolder validationArguments) {
//        Response response = requestWithAuth()
//                .body(validationArguments.getBodyParams())
//                .contentType(ContentType.JSON)
//                .post(CardsEndpoints.CREATE_CARD_URL);
//        response
//                .then()
//                .statusCode(400);
//        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
//        given()
//                .log()
//                .all()
//                .when()
//                .get()
//                .then()
//                .log().all().statusCode(200);
//    }

    // This is solution 2 of the CardBodyValidationArguentsProvider class.
    @ParameterizedTest
    @ArgumentsSource(CardBodyValidationArgumentsProvider.class)
    public void checkCreateCardWithInvalidName(CardBodyValidationArgumentsHolder validationArguments) {
        Response response = requestWithAuth()
                .body(validationArguments.getBodyParams())
                .contentType(ContentType.JSON)
                .log().all()
                .post(CardsEndpoints.CREATE_CARD_URL);
        response
                .then().statusCode(400);
//        System.out.println("This is response: " + response.body().asString());
//        System.out.println("This is an error: " + validationArguments.getErrorMessage());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkCreateCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .body(Map.of(
                        "name", "New item",
                        "idList", UrlParamValues.EXISTING_LIST_ID
                ))
                .contentType(ContentType.JSON)
                .post(CardsEndpoints.CREATE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @Test
    public void checkCreateCardWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .body(Map.of(
                        "name", "New item",
                        "idList", UrlParamValues.EXISTING_LIST_ID
                ))
                .contentType(ContentType.JSON)
                .post(CardsEndpoints.CREATE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }
}