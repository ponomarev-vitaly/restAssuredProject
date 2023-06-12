package test.delete;

import consts.BoardsEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.util.Map;

public class DeleteBoardTest extends BaseTest {
    private String createdBoardId;
    @BeforeEach
    public void createBoard(){
        // This code causes an error. I have to observe it carefully.
//        createdBoardId = requestWithAuth()
//                .body(Map.of("name", "New Board"))
//                .contentType(ContentType.JSON)
//                .put(BoardsEndpoints.CREATE_BOARD_URL)
//                .body().jsonPath().get("id");

        createdBoardId = requestWithAuth()
                .body(Map.of("name", "New Board"))
                .contentType(ContentType.JSON)
                .post(BoardsEndpoints.CREATE_BOARD_URL)
                .body().jsonPath().get("id");
    }

    /**
     PUT Method:

     The PUT method is used to update or replace an existing resource on the server.
     It requires the client to send the entire updated representation of the resource.
     If the resource exists, it is updated with the new representation sent in the request.
     If it doesn't exist, the server may create a new resource with the requested identifier.
     A PUT request is idempotent, meaning that multiple identical requests should have the same effect as a single request.
     Repeated PUT requests with the same data will not create multiple copies of the resource.
     In RESTful APIs, the URL typically includes the identifier of the resource being updated, such as /resource/{id}.
     POST Method:

     The POST method is used to submit data to the server to create a new resource.
     It does not require the client to send the entire representation of the resource; instead, it sends data that the server uses to create the resource.
     Each POST request typically results in the creation of a new resource on the server, with a server-generated identifier assigned to it.
     Unlike PUT, POST requests are not idempotent. Sending the same POST request multiple times will result in multiple resources being created on the server.
     In RESTful APIs, the URL typically represents the collection where the new resource will be created, such as /resources.
     */

    @Test
    public void checkDeleteBoard(){
        System.out.println("Created Board ID: " + createdBoardId);
        requestWithAuth()
                .pathParam("id", createdBoardId)
                .delete(BoardsEndpoints.DELETE_BOARD_URL)
                .then()
                .statusCode(200)
                .body("_value", Matchers.equalTo(null));
        requestWithAuth()
                //.queryParam("fields", "id,name")
                .pathParam("member", UrlParamValues.USER_NAME)
                .get(BoardsEndpoints.GET_ALL_BOARDS_URL)
                .then()
                .body("id", Matchers.not(Matchers.hasItem(createdBoardId)));
    }
}
