package test.get;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.CardIdValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.CardIdValidationArgumentsProvider;
import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;

public class GetCardsValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkGetCardWIthInvalidId(CardIdValidationArgumentsHolder validationArguments){ // This is the test to check if the user sends invalid id.
        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .get(CardsEndpoints.GET_CARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkGetCardWIthInvalidAuth(AuthValidationArgumentsHolder validationArguments) { // This is the test for the situation when the user tries to access a board without access key and token.
        Response response = requestWithoutAuth()
                .pathParam("card_id", UrlParamValues.EXISTING_CARD_ID)
                .get(CardsEndpoints.GET_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized card permission requested", response.body().asString());
    }

    @Test
    public void checkGetCardWithAnotherUserCredentials(){
        // This is the test for the situation when the user uses the key and token from the another user.
        Response response = requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam("card_id", UrlParamValues.EXISTING_CARD_ID)
                .get(CardsEndpoints.GET_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }
}
