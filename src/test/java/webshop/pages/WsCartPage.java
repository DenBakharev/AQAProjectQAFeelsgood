package webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;

public class WsCartPage {
    private final SelenideElement choiceCompInfo = $(".product-name");
    private final SelenideElement qantityCompInfo = $("input.qty-input");
    private final SelenideElement fullCostOfCartinfo = $("span.product-subtotal");

    public WsCartPage checkChoiceComp(String itemName) {
        choiceCompInfo.shouldHave(text(itemName));

        return this;
    }

    public WsCartPage checkQantityCompInfo(int itemQantity) {
        qantityCompInfo.shouldHave(value(String.valueOf(itemQantity)));

        return this;
    }
    public WsCartPage checkFullCostOfCart(float total) {
        fullCostOfCartinfo.shouldHave(text(String.valueOf(total)));

        return this;
    }

}
