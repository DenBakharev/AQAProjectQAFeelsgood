package webshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import webshop.config.WebDriverConfig;
import webshop.util.AttachManager;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;
import static webshop.config.Config.getSelenoidChromeOptions;
import static webshop.config.Config.getWebDriverConfig;

public class BaseTest {

    private static final WebDriverConfig config = getWebDriverConfig();

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.browserSize = config.browserSize();
        Configuration.browser = config.browser();

//        Configuration.headless = true;
        if ("remote".equals(config.run())) {
            Configuration.remote =
                    "https://" + config.selenoidUser() + ":" + config.selenoidPassword() + "@" + config.selenoidUrl();
            Configuration.browserCapabilities = getSelenoidChromeOptions();
        }

//        Configuration.remote = "http://localhost:4444/wd/hub";
//        Configuration.browserCapabilities.setCapability("enableVNC", true);
    }

    @AfterEach
    void after() {

        if (hasWebDriverStarted()) {
            AttachManager.takeScreenshot();
            AttachManager.pageSource();
            AttachManager.browserConsoleLogs();
            if ("remote".equals(config.run())) {
                AttachManager.addVideo();
            }

            clearBrowserLocalStorage();
            closeWebDriver();
        }
    }
}
