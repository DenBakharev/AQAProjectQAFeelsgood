package webshop.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webshop.pages.WsSelectCompPage;
import webshop.pages.WsWelcomePage;
import webshop.steps.AuthSteps;

import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selenide.*;
import static webshop.config.Config.WEB_SHOP_URL;

public class CartTest {

    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        authSteps.registerNewUser();
    }

    @Test
    void addItemToCartTest() {
        float total;
        int itemQantity = 2;
        int random = ThreadLocalRandom.current().nextInt(1, 4);

        WsSelectCompPage wsSelectCompPage = open(WEB_SHOP_URL, WsWelcomePage.class)
                .selectComp()
                .selectNeededComp()
                .selectProcessor(random)
                .getInfoAboutSelectComp()
                .setQuantityComps(itemQantity)
                .addCompToCart()
                .checkSuccessAddToCart()
                .checkQuantityCompsInCart(itemQantity);

        String itemName = wsSelectCompPage.getItemName();
        String priceValue = wsSelectCompPage.getPriceValue();
        switch (random) {
            case 1 -> total = (Float.parseFloat(priceValue) * itemQantity);
            case 2 -> total = 1630f;
            case 3 -> total = 1800f;
            default -> total = 0f;
        }
        wsSelectCompPage.goToCart()
                .checkChoiceComp(itemName)
                .checkQantityCompInfo(itemQantity)
                .checkFullCostOfCart(total);
    }
}
