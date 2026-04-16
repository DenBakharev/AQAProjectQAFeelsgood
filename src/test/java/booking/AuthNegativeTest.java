package booking;

import booking.dto.AuthRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthNegativeTest extends BaseApiTest{

    @ParameterizedTest()
    @CsvFileSource(resources = "/authnegative.csv")
    @Tag("api")
    void authNegativeTest(String email, String password) {

        String authResponse = given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest(email, password))
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract().asString();

        assertThat(authResponse).doesNotContain("token");
    }
    @Test
    @Tag("api")
    void authNegativeWithoutBodyTest() {

        String response = given()
                .contentType(ContentType.JSON)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract().asString();

        assertThat(response).doesNotContain("token");
    }
}
