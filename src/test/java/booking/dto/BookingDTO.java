package booking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookingDates {
        private String checkin;
        private String checkout;
    }

    public BookingDTO(String firstname, int totalprice, String checkin) {
        this.firstname = firstname;
        this.totalprice = totalprice;
        this.bookingdates= new BookingDates();
        this.bookingdates.checkin = checkin;
    }
}