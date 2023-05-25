import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class TrelloTest {

    public static void setBaseUrl(){
        RestAssured.baseURI = "https://api.trello.com";
    }
    @Test
    public void checkTrelloApi() { // Create a method.
        RestAssured.given() // Create object.
                .log().all()
                .get()
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
