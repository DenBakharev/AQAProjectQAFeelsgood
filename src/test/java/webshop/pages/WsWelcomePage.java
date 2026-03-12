package webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class WsWelcomePage {
    private final SelenideElement registerButton = $("a[href='/register']");
    private final SelenideElement loginButton = $("a[href='/login']");
    private final SelenideElement selectCompButton = $("a[href='/computers']");
    private final SelenideElement selectDesktopButton = $("a[href='/desktops']");

    public WsRegistrationPage openRegistration() {
        registerButton.click();

        return new WsRegistrationPage();
    }

    public WsLoginPage openLogin() {
        loginButton.click();

        return new WsLoginPage();
    }

    public WsSelectCompPage selectComp() {
        selectCompButton.hover();   // два вызова в одном методе т.к. всплывающее окно, и в нем уже выбираем
        selectDesktopButton.click();

        return new WsSelectCompPage();
    }
}
