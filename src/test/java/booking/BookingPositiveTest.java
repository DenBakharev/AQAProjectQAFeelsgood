package booking;

import booking.apiclient.BookingApiClient;
import booking.dto.AuthResponse;
import booking.dto.BookingDTO;
import booking.dto.CreateBookingResponse;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingPositiveTest extends BaseApiTest {

    private static final Faker faker = new Faker();
    private final BookingApiClient bookingApiClient = new BookingApiClient();
    private static final String USER = "admin", PASSWORD = "password123";

    @Test
    @Tag("api")
    void authTest() {

        Response authResponse = bookingApiClient.auth(USER, PASSWORD);
        assertThat(authResponse.as(AuthResponse.class).getToken()).isNotNull();
    }

    @Test
    @Tag("api")
    void createBookingTest() {
        Response resp = bookingApiClient.createBooking(buildBookingRequest());

        assertThat(resp.getStatusCode()).isEqualTo(200);
        CreateBookingResponse createBookingResponse = resp.as(CreateBookingResponse.class);

        assertThat(createBookingResponse.getBookingId()).isNotNull();
        assertThat(createBookingResponse.getBooking().getTotalprice()).isEqualTo(100);
        assertThat(createBookingResponse.getBooking().getBookingdates().getCheckin()).isEqualTo("2025-12-01");
        assertThat(createBookingResponse.getBooking().isDepositpaid()).isFalse();
    }

    private static BookingDTO buildBookingRequest() {
        return BookingDTO.builder()
                .firstname("Ivan")
                .lastname("Petrov")
                .totalprice(100)
                .depositpaid(false)
                .bookingdates(BookingDTO.BookingDates.builder()
                        .checkin("2025-12-01")
                        .checkout("2026-12-01")
                        .build())
                .additionalneeds("newspaper")
                .build();
    }

    @Test
    @Tag("api")
    void updateBookingTest() {

        Response createResp = bookingApiClient.createBooking(buildBookingRequestForUpdate());
        assertThat(createResp.getStatusCode()).isEqualTo(200);

        BookingDTO updateBookingReq = buildBookingRequestForUpdate();

        Response response = bookingApiClient.
                updateBooking(updateBookingReq, createResp.as(CreateBookingResponse.class).getBookingId());
        assertThat(response.getStatusCode()).isEqualTo(200);

        BookingDTO updatedBookingDTO = response.as(BookingDTO.class);
        assertThat(updatedBookingDTO.equals(updateBookingReq)).isTrue();
    }

    private static BookingDTO buildBookingRequestForUpdate() {
        return BookingDTO.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(1000, 10000))
                .depositpaid(faker.bool().bool())
                .bookingdates(BookingDTO.BookingDates.builder()
                        .checkin("2025-12-01")
                        .checkout("2026-12-01")
                        .build())
                .additionalneeds(faker.videoGame().title())
                .build();
    }
}