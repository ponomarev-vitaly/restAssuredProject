import io.restassured.RestAssured;

public class RestAssuredRunner {
    public static void main(String[] args) { // Create main method.
        RestAssured.given() // Create object.
                .baseUri("https://api.trello.com")
                .get();

    }
}
