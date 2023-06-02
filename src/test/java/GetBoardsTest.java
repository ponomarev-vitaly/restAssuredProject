import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class GetBoardsTest extends BaseTest{



    @Test
    public void checkGetBoards() { // Create a method.
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("member", "vitalyponomarev3")
                .get("/1/members/{member}/boards")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_boards.json"));
    }


    @Test
    public void checkGetBoard(){
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("id", "646746aecb24dbfdcd185380")
                .get("/1/boards/{id}")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("New Board"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_board.json"));
    }
}
