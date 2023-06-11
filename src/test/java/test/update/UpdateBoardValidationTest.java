package test.update;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.BoardIdValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.BoardIdValidationArgumentsProvider;
import consts.BoardsEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;

import java.util.Map;

public class UpdateBoardValidationTest extends BaseTest {
    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)

    public void checkUpdateBoardWithInvalidId(BoardIdValidationArgumentsHolder argumentsHolder){
        Response response = requestWithAuth()
                .pathParams(argumentsHolder.getPathParams())
                .put(BoardsEndpoints.UPDATE_BOARD_URL);
        response
                .then()
                .statusCode(argumentsHolder.getStatusCode());
        Assertions.assertEquals(argumentsHolder.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkUpdateBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) { // This is the test for the situation when the user tries to access a board without access key and token.
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id", UrlParamValues.EXISTING_BOARD_ID)
                .body(Map.of("name", "UpdatedName"))
                .contentType(ContentType.JSON)
                .put(BoardsEndpoints.UPDATE_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }
}
