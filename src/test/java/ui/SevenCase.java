package ui;

import framework.pages.MainPage;
import framework.pages.PricesDropPage;
import framework.pages.components.MainProductComponents;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

public class SevenCase extends BaseTest {
    MainPage mainPage = new MainPage();
    PricesDropPage pricesDropPage = new PricesDropPage();

    @Test
    public void priceDropCheck() {
        mainPage.clickOnFooterPricesDropButton();
        List<String> actualProductNamesList = pricesDropPage.checkThatEveryProductHasOldAndNewPrice();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualProductNamesList)
                .as("Some product hasn't old or new price")
                .isNotNull();
        List<String> expectedProductPriceWithDiscount = pricesDropPage.getExpectedProductPriceWithDiscount();
        List<String> actualProductPriceWithDiscount = MainProductComponents.getProductPriceFromPage();
        softly.assertThat(actualProductPriceWithDiscount)
                .as("Promo price for every product calculates incorrect")
                .containsExactlyElementsOf(expectedProductPriceWithDiscount);
        softly.assertAll();
    }
}
