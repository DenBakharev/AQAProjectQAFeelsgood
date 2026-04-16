package booking.apiclient;

import booking.BaseApiTest;
import booking.config.BookingConfig;
import booking.dto.AuthRequest;
import booking.dto.AuthResponse;
import booking.dto.BookingDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static booking.config.BookingApiConfig.getBookingConfig;
import static io.restassured.RestAssured.given;

public class BookingApiClient extends BaseApiTest {
    private static final BookingConfig config = getBookingConfig();

    public Response auth(String user, String password) {
        return given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest(user, password))
                .when()
                .post("/auth")
                .then()
                .extract().response();
    }

    public Response createBooking(BookingDTO bookingDTO) {

        return given()
                .contentType(ContentType.JSON)
                .body(bookingDTO)
                .post("/booking")
                .then()
                .extract().response();
    }

    public Response updateBooking(BookingDTO bookingDTO, Integer id) {

        return given()
                .cookie("token", getToken())
                .contentType(ContentType.JSON)
                .body(bookingDTO)
                .pathParam("BOOKING_ID", id)
                .put("/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }

    private String getToken() {
        return auth(config.bookingUsername(), config.bookingPassword()).as(AuthResponse.class).getToken();
    }
}