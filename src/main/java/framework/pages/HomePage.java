package framework.pages;

import framework.helpers.SortByHelpers;
import framework.pages.components.MainProductComponents;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.util.*;

@Log4j2
public class HomePage extends BasePage {
    public static By homeProductsComponentsLocator = By.xpath("//div[@class='products row']/div");
    private final By sortByDropdownMenuButtonLocator = By.xpath("//button[@aria-label='Sort by selection']");
    private final By pageLoadLocator = By.xpath("//div[@class='faceted-overlay']");

    @Step("Click on Sort By dropdown menu Button and select the option")
    public void clickOnSortByDropdownMenuButton(SortByHelpers.SortByFilter sortByValue) {
        log.info("Click on Sort By dropdown menu Button and select the option");
        find(sortByDropdownMenuButtonLocator).click();
        String sortBy = null;
        switch (sortByValue) {
            case BEST_SELLERS:
                sortBy = "Best sellers";
                break;
            case RELEVANCE:
                sortBy = "Relevance";
                break;
            case NAME_A_TO_Z:
                sortBy = "Name, A to Z";
                break;
            case NAME_Z_TO_A:
                sortBy = "Name, Z to A";
                break;
            case PRICE_lOW_TO_HIGH:
                sortBy = "Price, low to high";
                break;
            case PRICE_HIGH_TO_LOW:
                sortBy = "Price, high to low";
                break;
        }
        getDriver().findElement(By.xpath("//div[contains(@class,'products-sort-order')]/div/a[contains(text(), '" + sortBy + "')]")).click();
        waitUntilPageAreLoading(pageLoadLocator);

    }

    @Step("Get sorted By Name list from A to Z")
    public List<String> getSortedListByNameFromAToZ() {
        log.info("Get sorted By Name list from A to Z");
        List<String> actualProductNamesList = MainProductComponents.getProductNameFromPage();
        List<String> productsNameFromAToZ = new ArrayList<>();
        for (String product : Objects.requireNonNull(actualProductNamesList)) {
            productsNameFromAToZ.add(Objects.requireNonNull(product));
        }
        productsNameFromAToZ.sort(String.CASE_INSENSITIVE_ORDER);
        return productsNameFromAToZ;
    }

    @Step("Get sorted By Name list from Z to A")
    public List<String> getSortedListByNameFromZToA() {
        log.info("Get sorted By Name list from Z to A");
        List<String> actualProductNamesList = MainProductComponents.getProductNameFromPage();
        List<String> productsNameFromZToA = new ArrayList<>();
        for (String product : Objects.requireNonNull(actualProductNamesList)) {
            productsNameFromZToA.add(Objects.requireNonNull(product));
        }
        Comparator<String> reverseOrder = Comparator.reverseOrder();
        productsNameFromZToA.sort(reverseOrder);
        return productsNameFromZToA;
    }

    @Step("Get sorted By Price list from Low to High")
    public List<Double> getSortedListPriceToLowToHigh() {
        log.info("Get sorted By Price list from Low to High");
        List<Double> actualProductPriceList = MainProductComponents.getProductPriceFromHomePage();
        List<Double> productsPriceFromLowToHigh = new ArrayList<>();
        for (Double product : actualProductPriceList) {
            productsPriceFromLowToHigh.add(Objects.requireNonNull(product));
        }
        Collections.sort(productsPriceFromLowToHigh);

        return productsPriceFromLowToHigh;
    }

    @Step("Get sorted By Price list from High to Low")
    public List<Double> getSortedListPriceToHighToLow() {
        log.info("Get sorted By Price list from High to Low");
        List<Double> productPriceFromHighToLow = MainProductComponents.getProductRegularPriceFromPage();
        Collections.sort(productPriceFromHighToLow);
        Collections.reverse(productPriceFromHighToLow);
        return productPriceFromHighToLow;
    }

}


