import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetBoardsValidationTest {
    @BeforeAll
    public static void setBaseUrl(){
        RestAssured.baseURI = "https://api.trello.com";
    }

    public static RequestSpecification requestWithAuth() {
        return RestAssured.given() // Create object.
                .queryParams(Map.of(
                        "key", "36a241085b4ad3d2267dbd22fc544c5d",
                        "token", "ATTAefa2c1bf12159579a4153dc7d77b4ca9055612b58ba8c673c1b0a04d6ad834c3EB4CCA0D"
                ));
    }

    @Test
    public void checkGetBoardsWIthInvalidId(){ // This is test to check if the user sends invalid id.
        Response response = requestWithAuth()
                .pathParam("id", "invalid")
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void checkGetBoardsWIthInvalidAuth() { // This is the test for the situation when the user tries to access a board without access key and token.
        Response response = RestAssured.given()
                .pathParam("id", "646746aecb24dbfdcd185380")
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized permission respected", response.body().asString());
    }

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
