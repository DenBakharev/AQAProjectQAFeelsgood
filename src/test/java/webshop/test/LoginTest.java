package webshop.test;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import webshop.pages.WsRegistrationPage;
import webshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;
import static webshop.config.Config.WEB_SHOP_REGISTRATION_URL;
import static webshop.config.Config.WEB_SHOP_URL;

public class LoginTest {

    private static final Faker faker = new Faker();
    private static String password;
    private static String emailAddress;


    @BeforeAll
    static void beforeAll() {
        password = faker.credentials().password();
        emailAddress = faker.internet().emailAddress();

        open(WEB_SHOP_REGISTRATION_URL, WsRegistrationPage.class)
                .register(faker.name().firstName(), faker.name().lastName(), emailAddress, password)
                .checkUserLoggedIn(emailAddress);

        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
    @Test
    public void successLoginTest() {

        open(WEB_SHOP_URL, WsWelcomePage.class)
                .openLogin()
                .checkLoginPageOpened()
                .enterEmail(emailAddress)
                .enterPassword(password)
                .checkRememberMe()
                .submitLogin()
                .checkUserLoggedIn(emailAddress);
    }
}
