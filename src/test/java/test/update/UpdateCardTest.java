package test.update;

import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.util.Map;

public class UpdateCardTest extends BaseTest {
    @Test
    public void checkUpdateCard() {
        String updatedCardName = "Update Card Name" + LocalDateTime.now();
        requestWithAuth()
                .pathParam("card_id", UrlParamValues.CARD_ID_TO_UPDATE)
                .body(Map.of("name", updatedCardName))
                .contentType(ContentType.JSON)
                .put(CardsEndpoints.UPDATE_CARD_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(updatedCardName));
        requestWithAuth()
                .pathParam("card_id", UrlParamValues.CARD_ID_TO_UPDATE)
                .get(CardsEndpoints.GET_CARD_URL)
                .then()
                .body("name", Matchers.equalTo(updatedCardName));
    }
}
