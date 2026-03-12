package webshop.test;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import webshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;
import static webshop.config.Config.WEB_SHOP_URL;

public class RegistrationTest {

    private static final Faker faker = new Faker();

    @Test
    void registrationTest() {
        String password = faker.credentials().password();
        String emailAddress = faker.internet().emailAddress();

        open(WEB_SHOP_URL, WsWelcomePage.class)
                .openRegistration()
                .verifyRegistrationOpened()
                .selectMailGender()
                .enterFirstName(faker.name().firstName())
                .enterLastName(faker.name().lastName())
                .enterEmail(emailAddress)
                .enterPassword(password)
                .confirmPassword(password)
                .submitRegistration()
                .checkResult()
                .checkUserLoggedIn(emailAddress);
    }
}