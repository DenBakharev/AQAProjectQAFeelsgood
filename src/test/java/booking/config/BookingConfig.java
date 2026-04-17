package booking.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:config/booking.properties"
})
@Config.LoadPolicy(Config.LoadType.MERGE)
public interface BookingConfig extends Config {
    String bookingUsername();
    String bookingPassword();
}