package webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class WsLoginPage {

    private final SelenideElement pageTitle = $("div.page-title h1");
    private final SelenideElement inputEmailButton = $("input#Email");
    private final SelenideElement inputPasswordButton = $("input#Password");
    private final SelenideElement rememberMeButton = $("input#RememberMe");
    private final SelenideElement inputLoginButton = $("input.login-button");
    private final SelenideElement showEmailTitle = $("a[href='/customer/info']");
    private final SelenideElement errorMassageTitle = $("span.field-validation-error");

    @Step("Проверить что открылась страница авторизции/ ввода логина}")
    public WsLoginPage checkLoginPageOpened() {
        pageTitle.shouldHave(text("Welcome, Please Sign In!"));

        return this;
    }

    @Step("Ввести почту : {0}")
    public WsLoginPage enterEmail(String emailAddress) {
        inputEmailButton.setValue(emailAddress);

        return this;
    }

    @Step("Проверить что появилось сообщение об ошибки валидации почты")
    public WsLoginPage checkValidationError() {
        errorMassageTitle.shouldHave(text("Please enter a valid email address."));

        return this;
    }

    @Step("Ввести пароль : {0}")
    public WsLoginPage enterPassword(String password) {
        inputPasswordButton.setValue(password);

        return this;
    }

    @Step("Отметить чекбокс Запомнить меня ")
    public WsLoginPage checkRememberMe() {
        rememberMeButton.click();

        return this;
    }

    @Step("Подтвердить авторизацию")
    public WsLoginPage submitLogin() {
        inputLoginButton.click();

        return this;
    }

    @Step("Проверить что залогинились и почта логина : {0} отображается на странице")
    public WsLoginPage checkUserLoggedIn(String emailAddress) {
        showEmailTitle.shouldHave(text(emailAddress));

        return this;
    }


}
