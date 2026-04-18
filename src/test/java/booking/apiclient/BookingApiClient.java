package booking.apiclient;

import booking.BaseApiTest;
import booking.config.BookingConfig;
import booking.dto.AuthRequest;
import booking.dto.AuthResponse;
import booking.dto.BookingDTO;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static booking.config.BookingApiConfig.getBookingConfig;
import static io.restassured.RestAssured.given;

public class BookingApiClient extends BaseApiTest {
    private static final BookingConfig config = getBookingConfig();

    private final RequestSpecification spec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON).build();
    @Step("Авторизовать пользователя")
    public Response auth(String user, String password) {
        return given(spec)
                .body(new AuthRequest(user, password))
                .when()
                .post("/auth")
                .then()
                .extract().response();
    }
    @Step("Создать бронирование")
    public Response createBooking(BookingDTO bookingDTO) {

        return given(spec)
                .body(bookingDTO)
                .post("/booking")
                .then()
                .extract().response();
    }
    @Step("Получить бронирование id={0}")
    public Response getBooking(Integer id) {
        return given()
                .cookie("token", getToken())
                .pathParam("BOOKING_ID", id)
                .get("/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }
    @Step("Обновить бронирование полностью id={1}")
    public Response updateBooking(BookingDTO bookingDTO, Integer id) {

        return given(spec)
                .cookie("token", getToken())
                .body(bookingDTO)
                .pathParam("BOOKING_ID", id)
                .put("/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }
    @Step("Обновить бронирование частично id={1}")
    public Response partialUpdateBooking(BookingDTO bookingDTO, Integer id) {

        return given(spec)
                .cookie("token", getToken())
                .body(bookingDTO)
                .pathParam("BOOKING_ID", id)
                .patch("/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }
    @Step("Удалить бронирование id={0}")
    public Response deleteBooking(Integer id) {
        return given()
                .cookie("token", getToken())
                .pathParam("BOOKING_ID", id)
                .delete("/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }
    private String getToken() {
        return auth(config.bookingUsername(), config.bookingPassword()).as(AuthResponse.class).getToken();
    }
}