package webshop.test;

import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import webshop.BaseTest;
import webshop.pages.WsLoginPage;
import webshop.pages.WsRegistrationPage;
import webshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.*;
import static webshop.config.Config.*;

public class LoginTest extends BaseTest {

    private static final Faker faker = new Faker();
    private static String password;
    private static String emailAddress;

    @Nested
    public class PositiveTest {

        @BeforeEach
        void beforeEach() {
            password = faker.credentials().password();
            emailAddress = faker.internet().emailAddress();

            open(WEB_SHOP_REGISTRATION_URL, WsRegistrationPage.class)
                    .register(faker.name().firstName(), faker.name().lastName(), emailAddress, password)
                    .checkUserLoggedIn(emailAddress);

            clearBrowserCookies();
            clearBrowserLocalStorage();
        }

        @Test
        @Tag("positive")
        @Epic("Авторизация")
        @Feature("Авторизация покупателя")
        @DisplayName("Успешная авторизация ранее зарегистрированного пользователя")
        @Severity(BLOCKER)
        @Owner("Denis")
        @Link(name = "TASK-122")
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

    @ParameterizedTest(name = "Авторизация с невалидной почтой: {0}")
    @CsvFileSource(resources = "/email.csv")
    @Tag("negative")
    @Epic("Авторизация")
    @Feature("Авторизация покупателя")
    @DisplayName("Проверка невалидных данных электронной почты")
    @Severity(NORMAL)
    @Owner("Denis")
    @Link(name = "TASK-122")
    @Description("Проверяем негативные варианты ввода электронной почты")
    public void InvalidLoginTest(String email) {
        open(WEB_SHOP_LOGIN_URL, WsLoginPage.class)
                .enterEmail(email)
                .enterPassword(email)
                .submitLogin()
                .checkValidationError();

    }
}
