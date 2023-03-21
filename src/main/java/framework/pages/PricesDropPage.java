package framework.pages;

import framework.pages.components.MainProductComponents;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Log4j2
public class PricesDropPage extends BasePage{
    public static final By priceDropComponentLocator = By.xpath("//div/article[contains(@class, 'product-miniature')]");
    @Step("Checking that all products has old and new price")
    public List<String> checkThatEveryProductHasOldAndNewPrice(){
        log.info("Checking that all products has old and new price");
        List<MainProductComponents> products = MainProductComponents.getProductsFromPage();
        List<String> productsPrice = new ArrayList<>();
        for (MainProductComponents product : products) {
            if (product.getPrice() == null || product.getRegularPrice() == null) {
                return null;
            } else {
                productsPrice.add(product.getPrice());
            }
        }
        return productsPrice;
    }
    @Step("Get calculated product price")
    public List <String> getExpectedProductPriceWithDiscount() {
        log.info("Get calculated product price");
        List<String> RegularPriceList = MainProductComponents.getRegularPriceFromComponents();
        List<String> expectedPriceWithDiscount = new ArrayList<>();

        for (String regularPrice : RegularPriceList) {
            BigDecimal result = new BigDecimal(regularPrice).multiply(new BigDecimal(100 - 20).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_DOWN));
            expectedPriceWithDiscount.add(String.valueOf(result).substring(0, 5));
        }
        return expectedPriceWithDiscount;

    }





}
