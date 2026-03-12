package webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsSelectCompPage {
    private String itemName;
    private String priceValue;
    private final ElementsCollection selectNeededComp = $$("div.item-box");
    private final SelenideElement selectSlowButton = $("input#product_attribute_72_5_18_52"); //выбираем slow
    private final SelenideElement selectMediumButton = $("input#product_attribute_72_5_18_53"); //выбираем medium
    private final SelenideElement selectFastButton = $("input#product_attribute_72_5_18_65"); //выбираем fast
    private final SelenideElement itemNameInfo = $("div.product-name");
    private final SelenideElement priceValueInfo = $("[itemprop=price]");
    private final SelenideElement setQuantityCompsButton = $("input#addtocart_72_EnteredQuantity");
    private final SelenideElement addCompToCartButton = $("input#add-to-cart-button-72");
    private final SelenideElement textAboutSuccessAdded =  $(".content");
    private final SelenideElement checkQuantityCompsInCart = $("span.cart-qty");
    private final SelenideElement goToCartButton = $("span.cart-label");


    public WsSelectCompPage selectNeededComp() {
        selectNeededComp.get(0).click();

        return this;
    }

    public WsSelectCompPage selectProcessor(int choice) {
        switch (choice) {
            case 1 -> selectSlowButton.click();
            case 2 -> selectMediumButton.click();
            case 3 -> selectFastButton.click();
        }
        return this;
    }

    public WsSelectCompPage getInfoAboutSelectComp() {
        itemName = itemNameInfo.getText();
        priceValue = priceValueInfo.getText();

        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public WsSelectCompPage setQuantityComps(int itemQantity) {
        setQuantityCompsButton.setValue(String.valueOf(itemQantity));

        return this;
    }

    public WsSelectCompPage addCompToCart() {
        addCompToCartButton.click();

        return this;
    }

    public WsSelectCompPage checkSuccessAddToCart() {
        textAboutSuccessAdded.shouldHave(text("The product has been added to your"));

        return this;
    }
    public WsSelectCompPage checkQuantityCompsInCart(int itemQantity) {
        checkQuantityCompsInCart.shouldHave(text(String.valueOf(itemQantity)));

        return this;
    }
    public WsCartPage goToCart() {
        goToCartButton.click();

        return new WsCartPage();
    }
}
