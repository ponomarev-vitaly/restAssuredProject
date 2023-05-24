import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class TrelloTest {
    @Test
    public void checkTrelloApi() { // Create a method.
        RestAssured.given() // Create object.
                .log().all()
                .baseUri("https://api.trello.com")
                .get()
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
