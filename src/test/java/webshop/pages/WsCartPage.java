package webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;

public class WsCartPage {
    private final SelenideElement choiceCompInfo = $(".product-name");
    private final SelenideElement qantityCompInfo = $("input.qty-input");
    private final SelenideElement fullCostOfCartinfo = $("span.product-subtotal");

    @Step("Проверить что выбрали компьютер : {0}")
    public WsCartPage checkChoiceComp(String itemName) {
        choiceCompInfo.shouldHave(text(itemName));

        return this;
    }

    @Step("Проверить что выбрали количество компьютеров равное : {0}")
    public WsCartPage checkQantityCompInfo(int itemQantity) {
        qantityCompInfo.shouldHave(value(String.valueOf(itemQantity)));

        return this;
    }

    @Step("Проверить общую стоимость товаров в корзине : {0}")
    public WsCartPage checkFullCostOfCart(float total) {
        fullCostOfCartinfo.shouldHave(text(String.valueOf(total)));

        return this;
    }

}
