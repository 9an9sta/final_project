package framework.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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


  protected WebElement find(By locator) {
    return getDriver().findElement(locator);
  }

  public List<WebElement> findAll(By locator) {
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

  public void switchToFrameByLocator(String name){
    getDriver().switchTo().frame(name);
  }


  public WebElement waitUntilVisible(By locator, int seconds) {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds))
        .until(ExpectedConditions.presenceOfElementLocated(locator));
  }


  public void moveToTheElementByLocator(By locator){
    Actions actions = new Actions(getDriver());
    actions.moveToElement((WebElement) locator);
    actions.build().perform();

  }

  public void scrollToTheElementByLocatorUsingJS(By element){
    JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
  }


}
