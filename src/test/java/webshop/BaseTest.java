package webshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import webshop.util.AttachManager;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class BaseTest {
    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeAll
    static void before() {
        Configuration.browserSize = "1920x1080";
    }

    //    @BeforeEach
//    void closeBrowser() {
//        closeWebDriver();
//    }
    @AfterEach
    void after() {

        if (hasWebDriverStarted()) {
            AttachManager.takeScreenshot();
            AttachManager.pageSource();
            AttachManager.browserConsoleLogs();

            clearBrowserLocalStorage();
            closeWebDriver();
        }
    }
}
