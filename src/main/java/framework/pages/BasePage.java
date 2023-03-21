package framework.pages;

import java.time.Duration;
import java.util.List;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class BasePage {

  private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

  public static ThreadLocal<WebDriver> getDriverThreadLocal() {
    return DRIVER_THREAD_LOCAL;
  }

  public static void setDriverThreadLocal(WebDriver driver) {
    DRIVER_THREAD_LOCAL.set(driver);
  }

  public static WebDriver getDriver() {
    return DRIVER_THREAD_LOCAL.get();
  }
  public static By loaderLocator = By.id("loadingMessage");
  static String mainPageFrame = "framelive";
  public static WebDriverWait wait;


  protected static WebElement find(By locator) {
    return getDriver().findElement(locator);
  }

  public static List<WebElement> findAll(By locator) {
    return getDriver().findElements(locator);
  }

  public void submitByLocator(By locator) {
    WebElement element = find(locator);

    JavascriptExecutor executor = (JavascriptExecutor) getDriver();
    executor.executeScript("arguments[0].click()", element);
  }

  public void submitByWebElement(WebElement element) {
    JavascriptExecutor executor = (JavascriptExecutor) getDriver();
    executor.executeScript("arguments[0].click()", element);

  }

  public static void switchToFrameByLocator(String name){
    log.info("Switch to Frame");
    getDriver().switchTo().frame(name);
  }


  public static void waitUntilElementToBeClickable(By locator, int seconds) {
    log.info("Wait until element to be Clickable");
     new WebDriverWait(getDriver(), Duration.ofSeconds(seconds))
        .until(ExpectedConditions.elementToBeClickable(locator));
  }
  //public WebElement waitUntilVisible(By locator, int seconds) {
  //    return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds))
  //        .until(ExpectedConditions.presenceOfElementLocated(locator));
  //  }


  public void moveToTheElementByLocator(By locator){
    Actions actions = new Actions(getDriver());
    actions.moveToElement((WebElement) locator);
    actions.build().perform();

  }

  public void scrollToTheElementByLocatorUsingJS(By element){
    JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
  }

  public void hoverToElementByLocator(WebElement locator){
    Actions actions = new Actions(getDriver());
    actions.moveToElement(locator).perform();
  }
  public static void waitUntilPageAreLoading(By loaderLocator) {
    log.info("Wait until page loader was hide");
    WebElement loader = find(loaderLocator);
    wait.until(ExpectedConditions.invisibilityOf(loader));
  }
  public static void switchToFrame() {
    log.info("Switch to Home page frame");
    switchToFrameByLocator(mainPageFrame);
  }


}
