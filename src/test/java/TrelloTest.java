import io.restassured.RestAssured;

public class TrelloTest {
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
