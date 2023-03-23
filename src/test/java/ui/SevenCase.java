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
        List<String> actualProductOldPriceList = pricesDropPage.getProductOldPriceFromPage();
        List<String> actualProductNewPriceList = MainProductComponents.getProductPriceFromPage();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualProductOldPriceList)
                .as("Some product hasn't old price")
                .isNotNull();
        softly.assertThat(actualProductNewPriceList)
                .as("Some product hasn't new price")
                .isNotNull();
        List<String> expectedProductPriceWithDiscount = pricesDropPage.getExpectedProductPriceWithDiscount();
        List<String> actualProductPriceWithDiscount = MainProductComponents.getProductPriceFromPage();
        softly.assertThat(actualProductPriceWithDiscount)
                .as("Promo price for every product calculates incorrect")
                .containsExactlyElementsOf(expectedProductPriceWithDiscount);
        softly.assertAll();
    }
}
