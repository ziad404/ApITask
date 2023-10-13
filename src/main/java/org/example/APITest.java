package org.example;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class APITest {
    private static final String BASE_URL = "https://pay2.foodics.dev/cp_internal";
    private static final String LOGIN_ENDPOINT = "/login";
    // Setting up the base URL before all test cases
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testSuccessfulLogin() {
        // Test Case 1: Successful Login
        String requestBody = "{\n" +
                "  \"email\": \"merchant@foodics.com\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"token\": \"Lyz22cfYKMetFhKQybx5HAmVimF1i0xO\"\n" +
                "}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .statusCode(200)
                .log().all(); // Log the response for debugging
    }

    @Test
    public void testInvalidCredentials() {
        // Test Case 2: Invalid Credentials
        String requestBody = "{\n" +
                "  \"email\": \"invalid@foodics.com\",\n" +
                "  \"password\": \"invalidPassword\",\n" +
                "  \"token\": \"invalidToken\"\n" +
                "}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .statusCode(401)
                .log().all();
    }

    @Test
    public void testMissingToken() {
        // Test Case 3: Missing Token
        String requestBody = "{\n" +
                "  \"email\": \"merchant@foodics.com\",\n" +
                "  \"password\": \"123456\"\n" +
                "}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .statusCode(400)
                .log().all();
    }
}
