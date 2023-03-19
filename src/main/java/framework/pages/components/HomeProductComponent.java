package framework.pages.components;

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
@Getter
@Log4j2
public class HomeProductComponent {
    private String name;
    private String price;

    public HomeProductComponent(WebElement container){
        try {
            this.name = container.findElement(By.xpath(".//h2[@class='h3 product-title']/a")).getText();
        } catch(org.openqa.selenium.StaleElementReferenceException e){
            this.name = container.findElement(By.xpath(".//h2[@class='h3 product-title']/a")).getText();
        } catch (NoSuchElementException e) {
            this.name = null;
        }

        try {
            this.price = container.findElement(By.xpath(".//span[@class='price']")).getText();
        } catch(org.openqa.selenium.StaleElementReferenceException e){
            this.price = container.findElement(By.xpath(".//span[@class='price']")).getText();
        } catch (NoSuchElementException e) {
            this.price = null;
        }

    }
    @Step("Get Components from Home Page")
    public static List<HomeProductComponent> getComponentsFromHomePage(By locator) {
        log.info("Get Components from Home Page");
        List<HomeProductComponent> products = new ArrayList<>();
        List<WebElement> containers = BasePage.findAll(locator);
        for (WebElement container : containers) {
            HomeProductComponent mainComponents = new HomeProductComponent(container);
            products.add(mainComponents);
        }
        return products;
    }

    @Step("Get products name from Home Page")
    public static List<String> getProductsName(){
        log.info("Get products name from Home Page");
        MainPage mainPage = new MainPage();
        mainPage.waitUntilElementToBeClickable(HomePage.homeProductsComponentsLocator,10);
        List<HomeProductComponent> products = getComponentsFromHomePage(HomePage.homeProductsComponentsLocator);
        List<String> productsName = new ArrayList<>();
        for (HomeProductComponent product : products) {
            productsName.add(product.getName());
        }
        return productsName;
    }
    @Step("Get product price from Home Page")
    public static List<Double> getProductPrice(){
        log.info("Get product price from Home Page");
        MainPage mainPage = new MainPage();
        mainPage.waitUntilElementToBeClickable(HomePage.homeProductsComponentsLocator,10);
        List<HomeProductComponent> products = getComponentsFromHomePage(HomePage.homeProductsComponentsLocator);
        List<Double> productsPrice = new ArrayList<>();
        for (HomeProductComponent product : products) {
            productsPrice.add(Double.parseDouble(product.getPrice().replace("€","")));
        }
        return productsPrice;

    }




}
