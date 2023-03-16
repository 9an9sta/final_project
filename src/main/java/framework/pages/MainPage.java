package framework.pages;

import framework.pages.components.MainProductComponents;
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
  private final By currentSelectedLanguageInHeaderSelectorLocator = By.xpath("//header//ul[@aria-labelledby='language-selector-label']/li/a[text()]");
  private final By signInButtonLocator = By.xpath("//div[@class='user-info']/a");
  private final By currentSignUpUserNameLocator = By.xpath("//div[@class='user-info']//span");
  private final By clothesMenuCategoryLocator = By.xpath("//li[@id='category-3']/a");
  private final By clothesMenuCategoryItemsLocator = By.xpath("//li[@id='category-3']/div/ul/li/a");
  private final By accessoriesMenuCategoryLocator = By.xpath("//li[@id='category-6']/a");
  private final By accessoriesMenuCategoryItemsLocator = By.xpath("//li[@id='category-6']/div/ul/li/a");
  private final By artMenuCategoryLocator = By.xpath("//li[@id='category-9']/a");
  private final By artMenuCategoryItemsLocator = By.xpath("//li[@id='category-9']/div/ul/li/a");
  private final By mainProductsComponentsLocator = By.xpath("//div[@class='thumbnail-container reviews-loaded']");

  public MainPage switchToFrame() {
    switchToFrameByLocator(mainPageFrame);
    return this;
  }

  public String getFooterNewsTextLocator() {
    log.info("Getting footer news text from [Main page]");
    waitUntilVisible(footerNewsTextLocator, 10);
    return find(footerNewsTextLocator).getText();
  }

  public String getFooterUnsubscribeMessageLocator() {
    log.info("Getting footer unsubscribe message from [Main page]");
    return find(footerUnsubscribeMessageLocator).getText();
  }

  public String getFooterSubscribeButtonValue() {
    log.info("Getting footer subscribe button value from [Main page]");
    return find(footerSubscribeButtonLocator).getAttribute("value");
  }

  public String getFooterSubscribeButtonText() {
    log.info("Getting footer subscribe button text from [Main page]");
    return find(footerSubscribeButtonLocator).getCssValue("text-transform");
  }

  public int getHeaderLanguageSelectValue() {
    log.info("Getting header select value from [Main page]");
    waitUntilVisible(headerLanguageSelectorLocator, 10);
    return findAll(headerLanguageSelectorLocator).size();
  }

  public String checkThatUkraineLanguageIsPresentInHeaderSelect() {
    WebElement language = find(currentSelectedLanguageInHeaderSelectorLocator);
    return null;

  }

  public RegisterPage goToSignInPage() {
    find(signInButtonLocator).click();
    return new RegisterPage();
  }

  public String getCurrentSignUpUserName() {
    return find(currentSignUpUserNameLocator).getText();
  }

  public List<String> getItemsFromCategory(By hoverToElementLocator, By getMenuCategoryLocator) {
    Actions actions = new Actions(BasePage.getDriver());
    actions.moveToElement(find(hoverToElementLocator));

    List<WebElement> clothesItems = findAll(getMenuCategoryLocator);
    actions.perform();
    List<String> items = new ArrayList<String>();
    for (WebElement element : clothesItems) {
      String text = element.getText();
      items.add(text);
    }
    return items;

  }

  public List<String> getItemsFromClothesCategory(){
    return getItemsFromCategory(clothesMenuCategoryLocator, clothesMenuCategoryItemsLocator);
  }

  public List<String> getItemsFromAccessoriesCategory(){
    return getItemsFromCategory(accessoriesMenuCategoryLocator, accessoriesMenuCategoryItemsLocator);
  }

  public List<String> getItemsFromArtCategory(){
    return getItemsFromCategory(artMenuCategoryLocator, artMenuCategoryItemsLocator);
  }

  public List<MainProductComponents> getMainComponents() {
    log.info("Get all components on Main page");
    List<MainProductComponents> products = new ArrayList<>();
    List<WebElement> containers = findAll(mainProductsComponentsLocator);
    for (WebElement container : containers) {
      MainProductComponents mainComponents = new MainProductComponents(container);
      products.add(mainComponents);
    }
    return products;
  }

  public List<String> getNameFromMainProducts(){
    List<MainProductComponents> products = getMainComponents();
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

  public List<String> getPriceFromMainProducts(){
    List<MainProductComponents> products = getMainComponents();
    List<String> productsPrice = new ArrayList<>();
    for (MainProductComponents product : products) {
      if (product.getPrice() == null) {
        return null;
      } else {
        productsPrice.add(product.getPrice());
      }
    }
    return productsPrice;
  }

  public List<Double> checkThatPriceFromMainProductsIsAboveZero(){
    List<MainProductComponents> products = getMainComponents();
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