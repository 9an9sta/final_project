package framework.pages;

import framework.helpers.ListHelper;
import framework.pages.components.MainProductComponents;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class PricesDropPage extends BasePage {

    @Step("Get all Regular price from Page")
    public List<String> getProductOldPriceFromPage() {
        log.info("Get all product Old price from Page");
        List<MainProductComponents> products = MainProductComponents.getProductsFromPage();
        List<String> productsPrice = new ArrayList<>();
        for (MainProductComponents product : products) {
            if (product.getRegularPrice() == null) {
                return null;
            } else {
                productsPrice.add(product.getRegularPrice().replace("€", ""));
            }
        }
        return productsPrice;
    }

    @Step("Get calculated product price")
    public List<String> getExpectedProductPriceWithDiscount() {
        log.info("Get calculated product price");
        List<Double> RegularPriceList = MainProductComponents.getProductRegularPriceFromPage();
        List<String> RegualrPriseStringList = ListHelper.restoreDoubleValueAsString(RegularPriceList);
        List<String> expectedPriceWithDiscount = new ArrayList<>();
        for (String regularPrice : RegualrPriseStringList) {
            BigDecimal result = (new BigDecimal(regularPrice).multiply(new BigDecimal(100 - 20)).divide(new BigDecimal(100), RoundingMode.HALF_DOWN));
            expectedPriceWithDiscount.add(String.valueOf(result).substring(0, 5));
        }
        return expectedPriceWithDiscount;
    }


}
