package test.update;

import arguments.holders.BoardIdValidationArgumentsHolder;
import arguments.providers.BoardIdValidationArgumentsProvider;
import consts.BoardsEndpoints;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;

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
}
