import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetBoardsTest {
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
    public void checkGetBoards() { // Create a method.
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("member", "vitalyponomarev3")
                .get("/1/members/{member}/boards")
                .then()
                .statusCode(200);
    }


    @Test
    public void checkGetBoard(){
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("id", "646746aecb24dbfdcd185380")
                .get("/1/boards/{id}")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("New Board"));
    }
}
