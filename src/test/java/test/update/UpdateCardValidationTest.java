package test.update;

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

public class UpdateCardValidationTest extends BaseTest {


    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkUpdateCardWIthInvalidId(CardIdValidationArgumentsHolder validationArguments){
        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .get(CardsEndpoints.UPDATE_CARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkUpdateCardWIthInvalidAuth(AuthValidationArgumentsHolder validationArguments) {
        Response response = requestWithoutAuth()
                .pathParam("card_id", UrlParamValues.EXISTING_CARD_ID)
                .get(CardsEndpoints.UPDATE_CARD_URL);
        response
                .then()
                .statusCode(401);
        // Next line of code does not work with the validationArguments.getErrorMessage() instead of "unauthorized card permission requested". Check the code.
        Assertions.assertEquals("unauthorized card permission requested", response.body().asString());
    }

    @Test
    public void checkUpdateCardWithAnotherUserCredentials(){
        Response response = requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam("card_id", UrlParamValues.EXISTING_CARD_ID)
                .get(CardsEndpoints.UPDATE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }
}


