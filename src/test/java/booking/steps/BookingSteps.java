package booking.steps;

import booking.dto.BookingDTO;
import io.qameta.allure.Step;
import net.datafaker.Faker;

import static org.junit.jupiter.api.Assertions.*;

public class BookingSteps {
    private static final Faker faker = new Faker();

    @Step("Проверить соответствие всех полей в ответе")
    public static void bookingShouldBeEqual(BookingDTO expected, BookingDTO actual) {

        assertAll(
                () -> assertEquals(expected.getFirstname(), actual.getFirstname()),
                () -> assertEquals(expected.getLastname(), actual.getLastname()),
                () -> assertEquals(expected.getTotalprice(), actual.getTotalprice()),
                () -> assertNotNull(actual.getBookingdates()),
                () -> assertEquals(expected.getBookingdates().getCheckin(),
                        actual.getBookingdates().getCheckin()),
                () -> assertEquals(expected.getBookingdates().getCheckout(),
                        actual.getBookingdates().getCheckout()),
                () -> assertEquals(expected.getAdditionalneeds(), actual.getAdditionalneeds())
        );
    }
    @Step("Создать бронь")
    public static BookingDTO buildBookingRequest() {
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
    @Step("Обновить бронь")
    public static BookingDTO buildBookingRequestForUpdate() {
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
