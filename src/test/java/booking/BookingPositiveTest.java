package booking;

import booking.dto.AuthRequest;
import booking.dto.AuthResponse;
import booking.dto.CreateBookingDTO;
import booking.dto.CreateBookingResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingPositiveTest extends BaseApiTest {


    @Test
    @Tag("api")
    void authTest() {
        String user = "admin";
        String password = "password123";

        AuthResponse authResponse = given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest(user, password))
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract().as(AuthResponse.class);
        assertThat(authResponse.getToken()).isNotNull();
    }

    @Test
    @Tag("api")
    void createBookingTest() {
        CreateBookingResponse response = given()
                .contentType(ContentType.JSON)
                .body(buildBookingRequest())
                .post( "/booking")
                .then()
                .statusCode(200)
                .extract().as(CreateBookingResponse.class);

        assertThat(response.getBookingId()).isNotNull();
        assertThat(response.getBooking().getTotalprice()).isEqualTo(100);
        assertThat(response.getBooking().getBookingdates().getCheckin()).isEqualTo("2025-12-01");
        assertThat(response.getBooking().isDepositpaid()).isFalse();
    }

    private static CreateBookingDTO buildBookingRequest() {
        return CreateBookingDTO.builder()
                .firstname("Ivan")
                .lastname("Petrov")
                .totalprice(100)
                .depositpaid(false)
                .bookingdates(CreateBookingDTO.BookingDates.builder()
                        .checkin("2025-12-01")
                        .checkout("2026-12-01")
                        .build())
                .additionalneeds("newspaper")
                .build();
    }
}