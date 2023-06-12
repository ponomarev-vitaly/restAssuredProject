package test.delete;

import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import test.BaseTest;

import java.util.Map;

public class DeleteCardValidationTest extends BaseTest {
    private String createdCardId;
    @BeforeEach
    public void createCard(){
        createdCardId = requestWithAuth()
                .body(Map.of(
                        "name", "New Card",
                        "idList", UrlParamValues.EXISTING_LIST_ID

                ))
                .contentType(ContentType.JSON)
                .post(CardsEndpoints.CREATE_CARD_URL)
                .body().jsonPath().get("id");
    }



}
