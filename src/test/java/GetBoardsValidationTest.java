import arguments.holders.BoardIdValidationArgumentsHolder;
import arguments.providers.BoardIdValidationArgumentsProvider;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Map;

public class GetBoardsValidationTest extends BaseTest{




    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)
    public void checkGetBoardWIthInvalidId(BoardIdValidationArgumentsHolder validationArguments){ // This is the test to check if the user sends invalid id.
        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @Test
    public void checkGetBoardWIthInvalidAuth() { // This is the test for the situation when the user tries to access a board without access key and token.
        Response response = requestWithoutAuth()
                .pathParam("id", "646746aecb24dbfdcd185380")
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized permission requested", response.body().asString());
    }

    @Test
    public void checkGetBoardWithAnotherUserCredentials(){
        // This is the test for the situation when the user uses the key and token from the another user.
        Response response = requestWithoutAuth()
                .queryParams(Map.of(
                        "key", "8b32218e6887516d17c84253faf967b6",
                        "token", "492343b8106e7df3ebb7f01e219cbf32827c852a5f9e2b8f9ca296b1cc604955"
                ))
                .pathParam("id", "646746aecb24dbfdcd185380")
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }
}
