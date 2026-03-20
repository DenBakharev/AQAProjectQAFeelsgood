package webshop.config;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    public static final String WEB_SHOP_URL = "https://demowebshop.tricentis.com";
    public static final String WEB_SHOP_REGISTRATION_URL = WEB_SHOP_URL + "/register";
    public static final String WEB_SHOP_LOGIN_URL = WEB_SHOP_URL + "/login";

    public static WebDriverConfig getWebDriverConfig() {
        return ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    }

    public static ChromeOptions getSelenoidChromeOptions() {

        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", "128");

        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("name", "useToastTest");            // имя сессии
        selenoidOptions.put("sessionTimeout", "15m");           // таймаут сессии
        selenoidOptions.put("env", List.of("TZ=UTC"));          // временная зона
        selenoidOptions.put("labels", Map.of("manual", "true")); // кнопка ручного удаления
        selenoidOptions.put("enableVideo", true);               // запись видео
        selenoidOptions.put("enableVNC", true);
        options.setCapability("selenoid:options", selenoidOptions);

        return options;
    }
}
