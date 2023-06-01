import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GetCardsValidationTest {
    @BeforeAll
    public static void setBaseUrl(){
        RestAssured.baseURI = "https://api.trello.com";
    }

    GetBoardsTest obj;

    @Test
    public void checkGetCards(){
        obj.requestWithAuth()
                .pathParam("list_id", "646746aecb24dbfdcd185387")
                .get("/1/lists/{list_id}/cards")
                .then()
                .statusCode(200);
    }

    @Test
    public void checkGetCard(){
        obj.requestWithAuth()
                .pathParam("card_id", "646748eaef222a0de8dfb52c")
                .get("/1/cards/{card_id}")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("#test card name updated"));
    }

    @Test
    public void checkGetCardsValidation() { // Create a method, which is for the JSON schema validation only.
        obj.requestWithAuth()
                .queryParams("fields", "id,name")
                .pathParam("list_id", "646746aecb24dbfdcd185387")
                .get("/1/lists/{list_id}/cards")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_cards.json"));
    }


    @Test
    public void checkGetCardValidation(){ // Create a method, which is for the JSON schema validation only.
        obj.requestWithAuth()
                .queryParams("fields", "id,name")
                .pathParam("card_id", "646748eaef222a0de8dfb52c")
                .get("/1/cards/{card_id}")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"))
//                .body("name", Matchers.equalTo("New Board")); // This is the line of code with the incorrect card name.
                .body("name", Matchers.equalTo("#test card name updated")); // This is the line of code with the correct card name.
    }
}
