package webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class WsWelcomePage {
    private final SelenideElement registerButton = $("a[href='/register']");
    private final SelenideElement loginButton = $("a[href='/login']");
    private final SelenideElement selectCompButton = $("a[href='/computers']");
    private final SelenideElement selectDesktopButton = $("a[href='/desktops']");

    @Step("Открыть страницу регистрации")
    public WsRegistrationPage openRegistration() {
        registerButton.click();

        return new WsRegistrationPage();
    }

    @Step("Открыть страницу ввода логина")
    public WsLoginPage openLogin() {
        loginButton.click();

        return new WsLoginPage();
    }

    @Step("Выбрать категорию Компьютеры и подкатегорию Системные блоки")
    public WsSelectCompPage selectComp() {
        selectCompButton.shouldBe(visible).hover();   // два вызова в одном методе т.к. всплывающее окно, и в нем уже выбираем
        selectDesktopButton.shouldBe(visible).click();

        return new WsSelectCompPage();
    }
}
