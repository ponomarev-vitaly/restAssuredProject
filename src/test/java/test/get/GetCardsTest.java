package test.get;

import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import test.BaseTest;

public class GetCardsTest extends BaseTest {

    @Test
    public void checkGetCards(){
        requestWithAuth()
                .pathParam("list_id", UrlParamValues.EXISTING_LIST_ID)
                .get(CardsEndpoints.GET_ALL_CARDS_URL)
                .then()
                .statusCode(200);
    }

    @Test
    public void checkGetCard(){
        requestWithAuth()
                .pathParam("card_id", UrlParamValues.EXISTING_CARD_ID)
                .get(CardsEndpoints.GET_CARD_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("#test card name updated"));
    }

    @Test
    public void checkGetCardsValidation() { // Create a method, which is for the JSON schema validation only.
        requestWithAuth()
                .queryParams("fields", "id,name")
                .pathParam("list_id", UrlParamValues.EXISTING_LIST_ID)
                .get(CardsEndpoints.GET_ALL_CARDS_URL)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_cards.json"));
    }


    @Test
    public void checkGetCardValidation(){ // Create a method, which is for the JSON schema validation only.
        requestWithAuth()
                .queryParams("fields", "id,name")
                .pathParam("card_id", UrlParamValues.EXISTING_CARD_ID)
                .get(CardsEndpoints.GET_CARD_URL)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"))
//                .body("name", Matchers.equalTo("New Board")); // This is the line of code with the incorrect card name.
                .body("name", Matchers.equalTo("#test card name updated")); // This is the line of code with the correct card name.
    }
}
