package webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class WsLoginPage {

    private final SelenideElement pageTitle = $("div.page-title h1");
    private final SelenideElement inputEmailButton = $("input#Email");
    private final SelenideElement inputPasswordButton = $("input#Password");
    private final SelenideElement rememberMeButton = $("input#RememberMe");
    private final SelenideElement inputLoginButton = $("input.login-button");
    private final SelenideElement showEmailTitle = $("a[href='/customer/info']");

    public WsLoginPage checkLoginPageOpened() {
        pageTitle.shouldHave(text("Welcome, Please Sign In!"));

        return this;
    }

    public WsLoginPage enterEmail(String emailAddress) {
        inputEmailButton.setValue(emailAddress);

        return this;
    }

    public WsLoginPage enterPassword(String password) {
        inputPasswordButton.setValue(password);

        return this;
    }

    public WsLoginPage checkRememberMe() {
        rememberMeButton.click();

        return this;
    }

    public WsLoginPage submitLogin() {
        inputLoginButton.click();

        return this;
    }

    public WsLoginPage checkUserLoggedIn(String emailAddress) {
        showEmailTitle.shouldHave(text(emailAddress));

        return this;
    }


}
