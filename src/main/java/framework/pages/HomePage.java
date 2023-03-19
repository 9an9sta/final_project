package framework.pages;

import framework.helpers.SortByHelpers;
import framework.pages.components.HomeProductComponent;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Log4j2
public class HomePage extends BasePage{
    private final By sortByDropdownMenuButtonLocator = By.xpath("//button[@aria-label='Sort by selection']");
    public static By homeProductsComponentsLocator = By.xpath("//div[@class='products row']/div");
    private final By pageLoadLocator = By.xpath("//div[@class='faceted-overlay']");

@Step("Click on Sort By dropdown menu Button and select the option")
    public void clickOnSortByDropdownMenuButton(SortByHelpers.SortByFilter sortByValue){
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
        getDriver().findElement(By.xpath("//div[contains(@class,'products-sort-order')]/div/a[contains(text(), '" + sortBy +"')]")).click();
    waitUntilPageAreLoading(pageLoadLocator);

    }
    @Step("Get sorted By Name list from A to Z")
    public List<String> getSortedListByNameFromAToZ(){
    log.info("Get sorted By Name list from A to Z");
        List<String> actualProductNamesList = HomeProductComponent.getProductsName();
        List<String> productsName = new ArrayList<>();
        for (String product : actualProductNamesList) {
            productsName.add(product);
        }
        productsName.sort(String.CASE_INSENSITIVE_ORDER);
        return productsName;
    }
    @Step("Get sorted By Name list from Z to A")
    public List<String> getSortedListByNameFromZToA(){
    log.info("Get sorted By Name list from Z to A");
        List<String> actualProductNamesList = HomeProductComponent.getProductsName();
        List<String> productsName = new ArrayList<>();
        for (String product : actualProductNamesList) {
            productsName.add(product);
        }
        Comparator<String> reverseOrder = Comparator.reverseOrder();
        productsName.sort(reverseOrder);
        return productsName;
    }
    @Step("Get sorted By Price list from Low to High")
    public List<Double> getSortedListPriceToLowToHigh(){
    log.info("Get sorted By Price list from Low to High");
        List<Double> actualProductPriceList = HomeProductComponent.getProductPrice();
        List<Double> productsPrice = new ArrayList<>();
        for (Double product : actualProductPriceList) {
            productsPrice.add(product);
        }
        Collections.sort(productsPrice);

        return productsPrice;
    }
    @Step("Get sorted By Price list from High to Low")
    public List<Double> getSortedListPriceToHighToLow(){
    log.info("Get sorted By Price list from High to Low");
        List<Double> actualProductPriceList = HomeProductComponent.getProductPrice();
        List<Double> productsPrice = new ArrayList<>();
        for (Double product : actualProductPriceList) {
            productsPrice.add(product);
        }

        Collections.sort(productsPrice);
        Collections.reverse(productsPrice);
        return productsPrice;
    }






}


