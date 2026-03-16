package webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class WsRegistrationPage {

    private final SelenideElement selectMailRadioButton = $("#gender-male");
    private final SelenideElement pageTitle = $(".page-title");
    private final SelenideElement firstNameInput = $("input#FirstName");
    private final SelenideElement lastNameInput = $("input#LastName");
    private final SelenideElement emailInput = $("input#Email");
    private final SelenideElement passwordInput = $("input#Password");
    private final SelenideElement confirmPasswordInput = $("input#ConfirmPassword");
    private final SelenideElement submitRegistrationButton = $("input#register-button");
    private final SelenideElement checkResultButton = $("div.result");
    private final SelenideElement showEmailButton = $("a[href='/customer/info']");

    public WsRegistrationPage register(String firstName, String lastName, String emailAddress, String password) {
        verifyRegistrationOpened()
                .selectMailGender()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(emailAddress)
                .enterPassword(password)
                .confirmPassword(password)
                .submitRegistration()
                .checkResult();

        return this;
    }

    @Step("Проверить что открылась страница регистрации нового пользователя")
    public WsRegistrationPage verifyRegistrationOpened() {
        pageTitle.shouldHave(text("Register"));

        return this;
    }

    @Step("Выбрать пол покупателя")
    public WsRegistrationPage selectMailGender() {
        selectMailRadioButton.click();

        return this;
    }

    @Step("Ввести имя покупателя : {0}")
    public WsRegistrationPage enterFirstName(String firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    @Step("Ввести фамилию покупателя : {0}")
    public WsRegistrationPage enterLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    @Step("Ввести электронную почту : {0}")
    public WsRegistrationPage enterEmail(String email) {
        emailInput.setValue(email);

        return this;
    }

    @Step("Ввести пароль : {0}")
    public WsRegistrationPage enterPassword(String password) {
        passwordInput.setValue(password);

        return this;
    }

    @Step("Повторно ввести пароль : {0}")
    public WsRegistrationPage confirmPassword(String password) {
        confirmPasswordInput.setValue(password);

        return this;
    }

    @Step("Нажать кнопку подтверждения регистрации")
    public WsRegistrationPage submitRegistration() {
        submitRegistrationButton.click();

        return this;
    }

    @Step("Проверить что результат регистрации завершился успешно")
    public WsRegistrationPage checkResult() {
        checkResultButton.shouldHave(text("Your registration completed "));

        return this;
    }

    @Step("Проверить что отображается указанная при регистрации электронная почта : {0}")
    public WsRegistrationPage checkUserLoggedIn(String emailAddress) {
        showEmailButton.shouldHave(text(emailAddress));

        return this;
    }

}
