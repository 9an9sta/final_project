package framework.pages;

import framework.helpers.MenuCategoryHelper;
import framework.pages.components.MainProductComponents;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class MainPage extends BasePage {
    public static final By mainProductsComponentsLocator = By.xpath("//div[contains(@class, 'js-product')]");
    private final By footerNewsTextLocator = By.xpath("//footer//p[@id='block-newsletter-label']");
    private final By footerUnsubscribeMessageLocator = By.xpath("//footer//form/div//p");
    private final By footerSubscribeButtonLocator = By.xpath("//footer//form//input[@value='Subscribe']");
    private final By headerLanguageSelectorLocator = By.xpath("//header//ul[@aria-labelledby='language-selector-label']/li");
    private final By headerLanguageSelectorValueLocator = By.xpath("//header//ul[@aria-labelledby='language-selector-label']/li/a");
    private final By headerLanguageButtonLocator = By.xpath("//button[@aria-label='Language dropdown']");
//    private final By headerLanguageElementWaiterLocator = By.xpath("//li/a[contains(text(), 'македонски јазик')]");
    private final By signInButtonLocator = By.xpath("//div[@class='user-info']/a");
    private final By currentSignUpUserNameLocator = By.xpath("//div[@class='user-info']//span");
    private final By clothesMenuCategoryLocator = By.xpath("//li[@id='category-3']/a");
    private final By clothesMenuCategoryItemsLocator = By.xpath("//li[@id='category-3']/div/ul/li/a");
    private final By accessoriesMenuCategoryLocator = By.xpath("//li[@id='category-6']/a");
    private final By accessoriesMenuCategoryItemsLocator = By.xpath("//li[@id='category-6']/div/ul/li/a");
    private final By artMenuCategoryLocator = By.xpath("//li[@id='category-9']/a");
    private final By artMenuCategoryItemsLocator = By.xpath("//li[@id='category-9']/div/ul/li/a");
    private final By footerPricesDropButtonLocator = By.xpath("//footer//li/a[@id='link-product-page-prices-drop-1']");
    private final By allProductsButtonLocator = By.xpath("//a[contains(@class, 'all-product-link')]");

    @Step("Getting footer news text from [Main page]")
    public String getFooterNewsTextLocator() {
        log.info("Getting footer news text from [Main page]");
        return find(footerNewsTextLocator).getText();
    }

    @Step("Getting footer unsubscribe message from [Main page]")
    public String getFooterUnsubscribeMessageLocator() {
        log.info("Getting footer unsubscribe message from [Main page]");
        return find(footerUnsubscribeMessageLocator).getText();
    }

    @Step("Getting footer subscribe button value from [Main page]")
    public String getFooterSubscribeButtonValue() {
        log.info("Getting footer subscribe button value from [Main page]");
        return find(footerSubscribeButtonLocator).getAttribute("value");
    }

    @Step("Getting footer subscribe button text from [Main page]")
    public String getFooterSubscribeButtonText() {
        log.info("Getting footer subscribe button text from [Main page]");
        return find(footerSubscribeButtonLocator).getCssValue("text-transform");
    }

    @Step("Getting header select value from [Main page]")
    public int getHeaderLanguageSelectValue() {
        log.info("Getting header select value from [Main page]");
        return findAll(headerLanguageSelectorLocator).size();
    }

    @Step("Checking that Ukrainian language are present in language selector")
    public List<String> getAllLanguagesFromLanguageSelector() {
        log.info("Checking that Ukrainian language are present in language selector");
        find(headerLanguageButtonLocator).click();
        waitUntilElementToBeClickable(headerLanguageSelectorValueLocator, 10);
        List<WebElement> actualLanguage = findAll(headerLanguageSelectorValueLocator);
        List<String> language = new ArrayList<>();
        for (WebElement getLanguage : actualLanguage) {
            language.add(getLanguage.getText());
        }
        return language;
    }

    @Step("Go to [Sign In] Page")
    public RegisterPage goToSignInPage() {
        log.info("Go to [Sign In] Page");
        find(signInButtonLocator).click();
        return new RegisterPage();
    }

    @Step("Get current Sign Up username")
    public String getCurrentSignUpUserName() {
        log.info("Get current Sign Up username");
        return find(currentSignUpUserNameLocator).getText();
    }

    @Step("Click on Footer Prices Drop")
    public PricesDropPage clickOnFooterPricesDropButton() {
        log.info("Click on Footer Prices Drop");
        find(footerPricesDropButtonLocator).click();
        return new PricesDropPage();
    }

    @Step("Click on All Products button")
    public HomePage clickOnAllProductsButton() {
        log.info("Click on All Products button");
        find(allProductsButtonLocator).click();
        return new HomePage();
    }

    @Step("Checking that all prices is above zero")
    public List<Double> checkThatPriceFromMainProductsIsAboveZero() {
        log.info("Checking that all prices is above zero");
        List<MainProductComponents> products = MainProductComponents.getProductsFromPage();
        List<Double> productsPrice = new ArrayList<>();
        for (MainProductComponents product : products) {

            if (Double.parseDouble(product.getPrice().replace("€", "")) < 0.00) {
                return null;

            } else {
                productsPrice.add(Double.parseDouble(product.getPrice().replace("€", "")));
            }
        }
        return productsPrice;
    }

    public List<String> getCategoryFromHeaderMenu(MenuCategoryHelper.MenuCategory categoryMenu) {
        List<String> itemList = null;
        switch (categoryMenu) {
            case CLOTHES:
                hoverToElement(clothesMenuCategoryLocator);
                log.info("Get items from from [Clothes] submenu");
                itemList = getItemsFromCategory(clothesMenuCategoryItemsLocator);
                break;
            case ACCESSORIES:
                hoverToElement(accessoriesMenuCategoryLocator);
                log.info("Get items from from [Accessories] submenu");
                itemList = getItemsFromCategory(accessoriesMenuCategoryItemsLocator);
                break;
            case ART:
                hoverToElement(artMenuCategoryLocator);
                log.info("Get items from from [Art] submenu");
                itemList = getItemsFromCategory(artMenuCategoryItemsLocator);
                break;
        }
        return itemList;
    }

    public List<String> expectedCategoryItemsFromHeaderMenu(MenuCategoryHelper.MenuSubCategory menuSubCategory) {
        List<String> expectedItemList = null;
        switch (menuSubCategory) {
            case CLOTHES_ITEMS:
                log.info("Get items from from [Clothes] submenu");
                expectedItemList = new ArrayList<>(Arrays.asList("MEN", "WOMEN"));
                break;
            case ACCESSORIES_ITEMS:
                log.info("Get items from from [Accessories] submenu");
                expectedItemList = new ArrayList<>(Arrays.asList("STATIONERY", "HOME ACCESSORIES"));
                break;
        }
        return expectedItemList;
    }

    @Step("Hover to Menu element")
    public void hoverToElement(By categoryMenu) {
        log.info("Hover to Menu element");
        Actions actions = new Actions(BasePage.getDriver());
        actions.moveToElement(find(categoryMenu));
        actions.build().perform();
    }

    @Step("Get items from submenu")
    public List<String> getItemsFromCategory(By getMenuCategoryLocator) {
        List<WebElement> clothesItems = findAll(getMenuCategoryLocator);
        List<String> items = new ArrayList<String>();
        for (WebElement element : clothesItems) {
            String text = element.getText();
            items.add(text);
        }
        return items;
    }

}