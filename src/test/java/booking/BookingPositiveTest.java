package booking;

import booking.apiclient.BookingApiClient;
import booking.config.BookingConfig;
import booking.dto.AuthResponse;
import booking.dto.BookingDTO;
import booking.dto.CreateBookingResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static booking.config.BookingApiConfig.getBookingConfig;
import static booking.steps.BookingSteps.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingPositiveTest extends BaseApiTest {
    private static final BookingConfig config = getBookingConfig();
    private final BookingApiClient bookingApiClient = new BookingApiClient();
    private static final Faker faker = new Faker();

    @Test
    @Tag("api")
    @Epic("Бронирования")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Denis")
    @DisplayName("Авторизация")
    void authTest() {

        Response authResponse = bookingApiClient.auth(config.bookingUsername(), config.bookingPassword());
        assertThat(authResponse.as(AuthResponse.class).getToken()).isNotNull();
    }

    @Test
    @Tag("api")
    @Epic("Бронирования")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Denis")
    @DisplayName("Создание бронирования")
    void createBookingTest() {

        BookingDTO bookingDTO = buildBookingRequest();

        Response resp = bookingApiClient.createBooking(bookingDTO);
        assertThat(resp.getStatusCode()).isEqualTo(200);

        CreateBookingResponse createBookingResponse = resp.as(CreateBookingResponse.class);
        assertThat(createBookingResponse.getBooking()).isNotNull();
        bookingShouldBeEqual(bookingDTO, createBookingResponse.getBooking());
    }


    @Test
    @Tag("api")
    @Epic("Бронирования")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Denis")
    @DisplayName("Полное обновление бронирования")
    void updateBookingTest() {

        Response createResp = bookingApiClient.createBooking(buildBookingRequestForUpdate());
        assertThat(createResp.getStatusCode()).isEqualTo(200);

        BookingDTO updateBookingReq = buildBookingRequestForUpdate();
        Response response = bookingApiClient.
                updateBooking(updateBookingReq, createResp.as(CreateBookingResponse.class).getBookingId());
        assertThat(response.getStatusCode()).isEqualTo(200);

        BookingDTO updatedBookingDTO = response.as(BookingDTO.class);
        bookingShouldBeEqual(updateBookingReq, updatedBookingDTO);
    }

    @Test
    @Tag("api")
    @Epic("Бронирования")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Denis")
    @DisplayName("Частичное обновление бронирования")
    void partialUpdateBookingTest() {

        Response createResp = bookingApiClient.createBooking(buildBookingRequestForUpdate());
        assertThat(createResp.getStatusCode()).isEqualTo(200);

        BookingDTO bookingDTO = new BookingDTO(faker.formula1().driver(), faker.number().numberBetween(100, 500), "2025-12-03");

        Response response = bookingApiClient.
                partialUpdateBooking(bookingDTO, createResp.as(CreateBookingResponse.class).getBookingId());
        assertThat(response.getStatusCode()).isEqualTo(200);

        BookingDTO updatedBookingDTO = response.as(BookingDTO.class);
        assertThat(bookingDTO.getFirstname()).isEqualTo(updatedBookingDTO.getFirstname());
        assertThat(bookingDTO.getTotalprice()).isEqualTo(updatedBookingDTO.getTotalprice());
        assertThat(bookingDTO.getBookingdates().getCheckin()).isEqualTo(updatedBookingDTO.getBookingdates().getCheckin());
    }

    @Test
    @Tag("api")
    @Epic("Бронирования")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Denis")
    @DisplayName("Удаление бронирования")
    void deleteBookingTest() {

        Integer bookingId = bookingApiClient.createBooking(buildBookingRequest()).as(CreateBookingResponse.class).getBookingId();

        Response deleteResponse = bookingApiClient.deleteBooking(bookingId);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(201);

        Response getResponse = bookingApiClient.getBooking(bookingId);
        assertThat(getResponse.getStatusCode()).isEqualTo(404);
    }
}