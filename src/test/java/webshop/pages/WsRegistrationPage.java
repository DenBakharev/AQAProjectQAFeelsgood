package webshop.pages;

import com.codeborne.selenide.SelenideElement;

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

    public WsRegistrationPage verifyRegistrationOpened() {
        pageTitle.shouldHave(text("Register"));

        return this;
    }

    public WsRegistrationPage selectMailGender() {
        selectMailRadioButton.click();

        return this;
    }

    public WsRegistrationPage enterFirstName(String firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    public WsRegistrationPage enterLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    public WsRegistrationPage enterEmail(String email) {
        emailInput.setValue(email);

        return this;
    }

    public WsRegistrationPage enterPassword(String password) {
        passwordInput.setValue(password);

        return this;
    }

    public WsRegistrationPage confirmPassword(String password) {
        confirmPasswordInput.setValue(password);

        return this;
    }

    public WsRegistrationPage submitRegistration() {
        submitRegistrationButton.click();

        return this;
    }

    public WsRegistrationPage checkResult() {
        checkResultButton.shouldHave(text("Your registration completed "));

        return this;
    }

    public WsRegistrationPage checkUserLoggedIn(String emailAddress) {
        showEmailButton.shouldHave(text(emailAddress));

        return this;
    }

}
