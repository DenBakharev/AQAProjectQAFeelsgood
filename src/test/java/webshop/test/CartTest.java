package webshop.test;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import webshop.BaseTest;
import webshop.pages.WsSelectCompPage;
import webshop.pages.WsWelcomePage;
import webshop.steps.AuthSteps;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static webshop.config.Config.WEB_SHOP_URL;

public class CartTest extends BaseTest {

    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        authSteps.registerNewUser();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @Tag("positive")
    @Epic("Покупки")
    @Feature("Покупка компьютеров")
    @DisplayName("Успешное добавление компьютеров в корзину")
    @Severity(NORMAL)
    @Owner("Denis")
    @Link(name = "TASK-121")
    void addItemToCartTest(int proc) {
        float total;
        int itemQantity = 2;

        WsSelectCompPage wsSelectCompPage = open(WEB_SHOP_URL, WsWelcomePage.class)
                .selectComp()
                .selectNeededComp()
                .selectProcessor(proc)
                .getInfoAboutSelectComp()
                .setQuantityComps(itemQantity)
                .addCompToCart()
                .checkSuccessAddToCart()
                .checkQuantityCompsInCart(itemQantity);

        String itemName = wsSelectCompPage.getItemName();
        String priceValue = wsSelectCompPage.getPriceValue();
        switch (proc) {
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
