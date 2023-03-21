package ui;

import framework.pages.MainPage;
import framework.pages.components.MainProductComponents;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

public class SixCase extends BaseTest{
    private final MainPage mainPage = new MainPage();
    @Test
    public void checkPopularProducts(){
        List<MainProductComponents> mainComponents = MainProductComponents.getProductsFromPage();
        int actualMainPageProductCount = mainComponents.size();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualMainPageProductCount)
                .as("More than 8 products exist in 'POPULAR PRODUCTS' section")
                .isEqualTo(8);
        List<String> actualProductNamesList = MainProductComponents.getNameFromComponents();
        softly.assertThat(actualProductNamesList)
                .as("Missing name in one product")
                .isNotNull();
        List<String> actualProductPriseList = MainProductComponents.getPriceFromComponents();
        softly.assertThat(actualProductPriseList)
                .as("Missing price in one product")
                .isNotNull();
        List<Double> actualProductPriseListIsAboveZero = mainPage.checkThatPriceFromMainProductsIsAboveZero();
        softly.assertThat(actualProductPriseListIsAboveZero)
                .as("Some prices Less than 0.00")
                .isNotNull();
        softly.assertAll();

    }
}
