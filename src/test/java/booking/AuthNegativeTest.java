package booking;

import booking.dto.AuthRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
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
    @Epic("Бронирования")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Denis")
    @DisplayName("Авторизация с некорректными данными")
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
    @Epic("Бронирования")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Denis")
    @DisplayName("Авторизация с некорректными данными(без тела запроса)")
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
