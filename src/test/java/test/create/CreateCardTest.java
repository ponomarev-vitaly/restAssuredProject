package test.create;

import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
public class CreateCardTest extends BaseTest {
    private String createdCardId;
    // Solution 1.
    @Test
    public void checkCreateCard() {
        String cardName = "New Card " + LocalDateTime.now();
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", cardName);
        requestBody.put("idList", UrlParamValues.EXISTING_LIST_ID);
        Response response = requestWithAuth()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .post(CardsEndpoints.CREATE_CARD_URL);
        createdCardId = response.body().jsonPath().get("id");
        response.then()
                .statusCode(200)
                .body("name", Matchers.equalTo(cardName));
        requestWithAuth()
                .pathParam("list_id", UrlParamValues.EXISTING_LIST_ID)
                .get(CardsEndpoints.GET_ALL_CARDS_URL)
                .then()
                .body("name", Matchers.hasItem(cardName));
    }
    @AfterEach
    public void deleteCreatedCard() {
        if (createdCardId != null) {
            requestWithAuth()
                    .pathParam("id", createdCardId)
                    .delete(CardsEndpoints.DELETE_CARD_URL)
                    .then()
                    .statusCode(200);
        }
    }

    // Solution 2. There are some not major differences compared to the previous sample of code.
//    @Test
//    public void checkCreateCard() {
//        String cardName = "New Card" + LocalDateTime.now();
//        Response response = requestWithAuth()
//                .body(Map.of(
//                        "name", cardName,
//                        "idList", UrlParamValues.EXISTING_LIST_ID
//                ))
//                .contentType(ContentType.JSON)
//                .post(CardsEndpoints.CREATE_CARD_URL);
//        createdCardId = response.body().jsonPath().get("id");
//        response
//                .then()
//                .statusCode(200)
//                .body("name", Matchers.equalTo(cardName));
//        requestWithAuth()
//                .pathParam("list_id", UrlParamValues.EXISTING_LIST_ID)
//                .get(CardsEndpoints.GET_ALL_CARDS_URL)
//                .then()
//                .body("name", Matchers.hasItem(cardName));
//    }
//
//    @AfterEach
//    public void deleteCreatedCard() {
//        requestWithAuth()
//                .pathParam("id", createdCardId)
//                .delete(CardsEndpoints.DELETE_CARD_URL)
//                .then()
//                .statusCode(200);
//    }
}