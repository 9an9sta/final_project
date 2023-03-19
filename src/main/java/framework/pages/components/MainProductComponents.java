package framework.pages.components;

import framework.pages.BasePage;
import framework.pages.MainPage;
import framework.pages.PricesDropPage;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Log4j2
public class MainProductComponents {
    private String name;
    private String regularPrice;
    private String price;
    private String discount;

    public MainProductComponents(WebElement container){
        try {
            this.name = container.findElement(By.xpath(".//h3[@class='h3 product-title']/a")).getText();
        } catch (NoSuchElementException e) {
            this.name = null;
        }

        try {
            this.regularPrice = container.findElement(By.xpath(".//div[@class='product-price-and-shipping']/span[@class='regular-price']")).getText();
        } catch (NoSuchElementException e) {
            this.regularPrice = null;
        }

        try {
            this.price = container.findElement(By.xpath(".//div[@class='product-price-and-shipping']/span[@class='price']")).getText();
        } catch (NoSuchElementException e) {
            this.price = null;
        }

        try {
            this.discount = container.findElement(By.xpath(".//span[@class='discount-percentage discount-product']")).getText();
        } catch (NoSuchElementException e) {
            this.discount = null;
        }
    }

    @Step("Get all components")
    public static List<MainProductComponents> getComponentsFromPage(By locator) {
        log.info("Get all components");
        List<MainProductComponents> products = new ArrayList<>();
        List<WebElement> containers = BasePage.findAll(locator);
        for (WebElement container : containers) {
            MainProductComponents mainComponents = new MainProductComponents(container);
            products.add(mainComponents);
        }
        return products;
    }
    @Step("Get all product name in product components")
    public static List<String> getNameFromComponents(){
        log.info("Get all product name in product components");
        List<MainProductComponents> products = getComponentsFromPage(MainPage.mainProductsComponentsLocator);
        List<String> productsName = new ArrayList<>();
        for (MainProductComponents product : products) {
            if (product.getName() == null) {
                return null;
            } else {
                productsName.add(product.getName());
            }
        }
        return productsName;
    }
    @Step("Get all product price in product components")
    public static List<String> getPriceFromComponents(){
        log.info("Get all product price in product components");
        List<MainProductComponents> products = getComponentsFromPage(MainPage.mainProductsComponentsLocator);
        List<String> productsPrice = new ArrayList<>();
        for (MainProductComponents product : products) {
            if (product.getPrice() == null) {
                return null;
            } else {
                productsPrice.add(product.getPrice().replace("€",""));
            }
        }
        return productsPrice;
    }
    @Step("Get all Regular prices in product components")
    public  static List<String> getRegularPriceFromComponents() {
        log.info("Get all Regular prices in product components");
        List<MainProductComponents> products = getComponentsFromPage(PricesDropPage.priceDropComponentLocator);
        List<String> productsRegularPrice = new ArrayList<>();
        for (MainProductComponents product : products) {
            productsRegularPrice.add(product.getRegularPrice().replace("€",""));
        }
        return productsRegularPrice;
    }


}
