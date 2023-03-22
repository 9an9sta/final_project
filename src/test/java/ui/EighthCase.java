package ui;

import framework.helpers.SortByHelpers.SortByFilter;
import framework.pages.HomePage;
import framework.pages.MainPage;
import framework.pages.components.MainProductComponents;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

public class EighthCase extends BaseTest {
    MainPage mainPage = new MainPage();
    HomePage homePage = new HomePage();

    @Test
    public void sortingCheck() {
        mainPage.clickOnAllProductsButton();
        homePage.clickOnSortByDropdownMenuButton(SortByFilter.NAME_A_TO_Z);
        List<MainProductComponents> componentsFromPage = MainProductComponents.getProductsFromPage();
        List<String> actualProductNamesListFromAToZ = MainProductComponents.getProductNameFromPage();
        List<String> expectedProductListAToZ = homePage.getSortedListByNameFromAToZ();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualProductNamesListFromAToZ)
                .as("Sort products as 'Name, A to Z' wasn't correctly")
                .containsExactlyElementsOf(expectedProductListAToZ);
        homePage.clickOnSortByDropdownMenuButton(SortByFilter.NAME_Z_TO_A);
        List<String> actualProductNamesListNameFromZToA = MainProductComponents.getProductNameFromPage();
        List<String> expectedProductListNameFromZToA = homePage.getSortedListByNameFromZToA();
        softly.assertThat(actualProductNamesListNameFromZToA)
                .as("Sort products as 'Name, Z to A' wasn't correctly")
                .containsExactlyElementsOf(expectedProductListNameFromZToA);
        homePage.clickOnSortByDropdownMenuButton(SortByFilter.PRICE_lOW_TO_HIGH);
        List<Double> actualProductListPriceFromLowToHigh = MainProductComponents.getProductPriceFromHomePage();
        List<Double> expectedProductListPriceFromLowToHigh = homePage.getSortedListPriceToLowToHigh();
        softly.assertThat(actualProductListPriceFromLowToHigh)
                .as("Sort products as 'Price, low to high' wasn't correctly")
                .containsExactlyElementsOf(expectedProductListPriceFromLowToHigh);
        homePage.clickOnSortByDropdownMenuButton(SortByFilter.PRICE_HIGH_TO_LOW);
        List<Double> actualProductListPriceFromHighToLow = MainProductComponents.getProductRegularPriceFromPage();
        List<Double> expectedProductListPriceFromHighToLow = homePage.getSortedListPriceToHighToLow();
        softly.assertThat(actualProductListPriceFromHighToLow)
                .as("Sort products as 'Price, high to low' wasn't correctly")
                .containsExactlyElementsOf(expectedProductListPriceFromHighToLow);
        softly.assertAll();
    }

}

