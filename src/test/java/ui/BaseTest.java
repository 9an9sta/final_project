package ui;

import framework.BrowserFactory;
import framework.pages.BasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Log4j2
public class BaseTest {

  @BeforeMethod(alwaysRun = true)
  public synchronized void setUp() {
    int width = Integer.parseInt(System.getProperty("browser.width"));
    int height = Integer.parseInt(System.getProperty("browser.height"));
    String browser = System.getProperty("browser.type");

    log.info("Tests will run at {}x{}", width, height);

    WebDriver driver = BrowserFactory.getBrowser(BrowserFactory.Browsers.valueOf(browser));
    log.info("Open website");
    driver.get("https://demo.prestashop.com/");
    driver.manage().window().setSize(new Dimension(width, height));
    BasePage.setDriverThreadLocal(driver);
  }

  @AfterMethod(alwaysRun = true)
  public void quite() {
    if (BasePage.getDriverThreadLocal() != null) {
      BasePage.getDriver().quit();
      BasePage.getDriverThreadLocal().remove();
    }
  }


}
