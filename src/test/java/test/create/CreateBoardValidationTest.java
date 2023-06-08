package test.create;

import arguments.providers.BoardNameValidationArgumentsProvider;
import consts.BoardsEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;

import java.util.Map;

public class CreateBoardValidationTest extends BaseTest {
    @ParameterizedTest
    @ArgumentsSource(BoardNameValidationArgumentsProvider.class)
    public void checkCreateBoardWithInvalidName(Map bodyParams){
        Response response = requestWithAuth()
                .body(bodyParams)
                .contentType(ContentType.JSON)
                .post(BoardsEndpoints.CREATE_BOARD_URL);
        response
                .then()
                .statusCode(400);
        String errorMessage = response.jsonPath().getString("message");
        Assertions.assertEquals("invalid value for name", errorMessage);
    }
}
