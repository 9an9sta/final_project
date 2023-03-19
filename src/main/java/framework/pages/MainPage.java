package framework.pages;

import framework.pages.components.MainProductComponents;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MainPage extends BasePage {
  private final By footerNewsTextLocator = By.xpath("//footer//p[@id='block-newsletter-label']");
  private final By footerUnsubscribeMessageLocator = By.xpath("//footer//form/div//p");
  private final By footerSubscribeButtonLocator = By.xpath("//footer//form//input[@value='Subscribe']");
  String mainPageFrame = "framelive";
  private final By headerLanguageSelectorLocator = By.xpath("//header//ul[@aria-labelledby='language-selector-label']/li");
  private final By headerLanguageSelectorValueLocator = By.xpath("//header//ul[@aria-labelledby='language-selector-label']/li/a");
  private final By headerLanguageButtonLocator = By.xpath("//button[@aria-label='Language dropdown']");
  private final By currentSelectedLanguageInHeaderSelectorLocator = By.xpath("//header//ul[@aria-labelledby='language-selector-label']/li/a[text()]");
  private final By signInButtonLocator = By.xpath("//div[@class='user-info']/a");
  private final By currentSignUpUserNameLocator = By.xpath("//div[@class='user-info']//span");
  private final By clothesMenuCategoryLocator = By.xpath("//li[@id='category-3']/a");
  private final By clothesMenuCategoryItemsLocator = By.xpath("//li[@id='category-3']/div/ul/li/a");
  private final By accessoriesMenuCategoryLocator = By.xpath("//li[@id='category-6']/a");
  private final By accessoriesMenuCategoryItemsLocator = By.xpath("//li[@id='category-6']/div/ul/li/a");
  private final By artMenuCategoryLocator = By.xpath("//li[@id='category-9']/a");
  private final By artMenuCategoryItemsLocator = By.xpath("//li[@id='category-9']/div/ul/li/a");
  public static final By mainProductsComponentsLocator = By.xpath("//div[@class='thumbnail-container reviews-loaded']");
  private final By footerPricesDropButtonLocator = By.xpath("//footer//li/a[@id='link-product-page-prices-drop-1']");
  private final By allProductsButtonLocator = By.xpath("//a[contains(@class, 'all-product-link')]");
  @Step("Switch to Home page frame")
  public MainPage switchToFrame() {
    log.info("Switch to Home page frame");
    switchToFrameByLocator(mainPageFrame);
    return this;
  }
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
  public List<String> getAllLanguagesFromLanguageSelector(){
  log.info("Checking that Ukrainian language are present in language selector");
    find(headerLanguageButtonLocator).click();
    List<WebElement> actualLanguage = findAll(headerLanguageSelectorValueLocator);
    List<String> language = new ArrayList<>();
    for (WebElement getLanguage : actualLanguage) {
      language.add(getLanguage.getText());
    }
    return language;
  }
  @Step("Checking if languages selector has Ukraine language")
  public Boolean checkIfLanguagesSelectorHasUkraineLanguage(){
  log.info("Checking if languages selector has Ukraine language");
  List<String> languages = getAllLanguagesFromLanguageSelector();
    for (String currentLanguage : languages) {
      if(currentLanguage.equals("Українська")){
        return true;
      }
    }
    return false;
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
  @Step("Get items from submenu")
  public List<String> getItemsFromCategory(By hoverToElementLocator, By getMenuCategoryLocator) {
  log.info("Get items from submenu");
    Actions actions = new Actions(BasePage.getDriver());
    actions.moveToElement(find(hoverToElementLocator));
    actions.build().perform();
    List<WebElement> clothesItems = findAll(getMenuCategoryLocator);
    List<String> items = new ArrayList<String>();
    for (WebElement element : clothesItems) {
      String text = element.getText();
      items.add(text);
    }
    return items;

  }
  @Step("Get submenu item from Clothes category")
  public List<String> getItemsFromClothesCategory(){
  log.info("Get submenu item from Clothes category");
    return getItemsFromCategory(clothesMenuCategoryLocator, clothesMenuCategoryItemsLocator);
  }
  @Step("Get submenu item from Accessories category")
  public List<String> getItemsFromAccessoriesCategory(){
  log.info("Get submenu item from Accessories category");
    return getItemsFromCategory(accessoriesMenuCategoryLocator, accessoriesMenuCategoryItemsLocator);
  }
  @Step("Get submenu item from Art category")
  public List<String> getItemsFromArtCategory(){
  log.info("Get submenu item from Art category");
    return getItemsFromCategory(artMenuCategoryLocator, artMenuCategoryItemsLocator);
  }
  @Step("Click on Footer Prices Drop")
  public PricesDropPage clickOnFooterPricesDropButton(){
  log.info("Click on Footer Prices Drop");
    find(footerPricesDropButtonLocator).click();
    return new PricesDropPage();
  }
  @Step("Click on All Products button")
  public HomePage clickOnAllProductsButton(){
  log.info("Click on All Products button");
    find(allProductsButtonLocator).click();
    return new HomePage();
  }

  @Step("Checking that all prices is above zero")
  public List<Double> checkThatPriceFromMainProductsIsAboveZero(){
    log.info("Checking that all prices is above zero");
    List<MainProductComponents> products = MainProductComponents.getComponentsFromPage(mainProductsComponentsLocator);
    List<Double> productsPrice = new ArrayList<>();
    for (MainProductComponents product : products) {

      if (Double.parseDouble(product.getPrice().replace("€","")) < 0.00) {
        return null;

      } else {
        productsPrice.add(Double.parseDouble(product.getPrice().replace("€","")));
      }
    }
    return productsPrice;
  }















}