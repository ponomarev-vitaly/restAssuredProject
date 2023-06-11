package test.delete;

import consts.BoardsEndpoints;
import io.restassured.http.ContentType;
import test.BaseTest;

import java.util.Map;

public class DeleteBoardTest extends BaseTest {
    private String createBoardId;
    public void createBoard(){
        createBoardId = requestWithAuth()
                .body(Map.of("name", "New Board"))
                .contentType(ContentType.JSON)
                .put(BoardsEndpoints.CREATE_BOARD_URL)
                .body().jsonPath().get("id");

    }
}
