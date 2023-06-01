import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetCardsValidationTest {
    @BeforeAll
    public static void setBaseUrl(){
        RestAssured.baseURI = "https://api.trello.com";
    }

    GetBoardsTest obj;

    @Test
    public void checkGetCardWIthInvalidId(){ // This is the test to check if the user sends invalid id.
        Response response = requestWithAuth()
                .pathParam("card_id", "invalid")
                .get("/1/cards/{card_id}");
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void checkGetCardWIthInvalidAuth() { // This is the test for the situation when the user tries to access a board without access key and token.
        Response response = RestAssured.given()
                .pathParam("card_id", "646748eaef222a0de8dfb52c")
                .get("/1/cards/{card_id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized card permission requested", response.body().asString());
    }

    @Test
    public void checkGetCardWithAnotherUserCredentials(){
        // This is the test for the situation when the user uses the key and token from the another user.
        Response response = RestAssured.given()
                .queryParams(Map.of(
                        "key", "8b32218e6887516d17c84253faf967b6",
                        "token", "492343b8106e7df3ebb7f01e219cbf32827c852a5f9e2b8f9ca296b1cc604955"
                ))
                .pathParam("card_id", "646748eaef222a0de8dfb52c")
                .get("/1/cards/{card_id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }

    public static RequestSpecification requestWithAuth() {
        return RestAssured.given() // Create object.
                .queryParams(Map.of(
                        "key", "36a241085b4ad3d2267dbd22fc544c5d",
                        "token", "ATTAefa2c1bf12159579a4153dc7d77b4ca9055612b58ba8c673c1b0a04d6ad834c3EB4CCA0D"
                ));
    }
}
