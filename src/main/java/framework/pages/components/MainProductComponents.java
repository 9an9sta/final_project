package framework.pages.components;

import framework.helpers.ListHelper;
import framework.pages.BasePage;
import framework.pages.HomePage;
import framework.pages.MainPage;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static framework.pages.BasePage.waitUntilElementToBeClickable;

@Getter
@Log4j2
public class MainProductComponents {
    private String name;
    private String regularPrice;
    private String price;
    private String discount;

    public MainProductComponents(WebElement container) {
        try {
            this.name = container.findElement(By.xpath(".//div[@class='product-description']//a")).getText();
        } catch (NoSuchElementException e) {
            this.name = null;
        }

        try {
            this.regularPrice = container.findElement(By.xpath(".//span[@class='regular-price']")).getText();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            this.price = container.findElement(By.xpath(".//span[@class='regular-price']")).getText();
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

    @Step("Get all products")
    public static List<MainProductComponents> getProductsFromPage() {
        log.info("Get all components");
        List<MainProductComponents> products = new ArrayList<>();
        List<WebElement> containers = BasePage.findAll(MainPage.mainProductsComponentsLocator);
        for (WebElement container : containers) {
            MainProductComponents mainComponents = new MainProductComponents(container);
            products.add(mainComponents);
        }
        return products;
    }

    @Step("Get all product name in product components")
    public static List<String> getProductNameFromPage() {
        List<MainProductComponents> products = getProductsFromPage();
        log.info("Get all product name in product components");
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
    public static List<String> getProductPriceFromPage() {
        log.info("Get all product price in product components");
        List<MainProductComponents> products = getProductsFromPage();
        List<String> productsPrice = new ArrayList<>();
        for (MainProductComponents product : products) {
            if (product.getPrice() == null) {
                return null;
            } else {
                productsPrice.add(product.getPrice().replace("€", ""));
            }
        }
        return productsPrice;
    }

    @Step("Get product price from Home Page")
    public static List<Double> getProductRegularPriceFromPage() {
        log.info("Get product price from Home Page");
        waitUntilElementToBeClickable(HomePage.homeProductsComponentsLocator, 10);
        List<MainProductComponents> products = getProductsFromPage();
        List<String> productsPrice = new ArrayList<>();
        for (MainProductComponents product : products) {
            if (product.getRegularPrice() != null) {
                productsPrice.add(product.getRegularPrice().replace("€", ""));
            } else {
                productsPrice.add(product.getPrice().replace("€", ""));
            }
        }
        return ListHelper.restoreStringValueAsDouble(productsPrice);
    }

    @Step("Get product price from Home Page")
    public static List<Double> getProductPriceFromHomePage() {
        log.info("Get product price from Home Page");
        waitUntilElementToBeClickable(HomePage.homeProductsComponentsLocator, 10);
        List<MainProductComponents> products = getProductsFromPage();
        List<Double> productsPrice = new ArrayList<>();
        for (MainProductComponents product : products) {
            productsPrice.add(Double.parseDouble(product.getPrice().replace("€", "")));
        }
        return productsPrice;
    }

}
