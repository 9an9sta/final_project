package framework.pages;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

@Log4j2
public class MainPage extends BasePage{
  private final By footerNewsText = By.xpath("//footer//p[@id='block-newsletter-label']");
  private final By footerUnsubscribeMessage = By.xpath("//footer//form/div//p");
  private final By footerSubscribeButton = By.xpath("//footer//form//input[@value='Subscribe']");
  String mainPageFrame = "framelive";
  private final By headerLanguageSelector = By.xpath("//header//ul[@aria-labelledby='language-selector-label']/li");

  public MainPage switchToFrame(){
    switchToFrameByLocator(mainPageFrame);
    return this;
  }
  public String getFooterNewsText() {
    log.info("Getting footer news text from [Main page]");
    waitUntilVisible(footerNewsText, 10);
    return find(footerNewsText).getText();
  }

  public String getFooterUnsubscribeMessage(){
    log.info("Getting footer unsubscribe message from [Main page]");
    return find(footerUnsubscribeMessage).getText();
  }
  public String getFooterSubscribeButtonValue(){
    log.info("Getting footer subscribe button value from [Main page]");
    return find(footerSubscribeButton).getAttribute("value");
  }

  public String getFooterSubscribeButtonText(){
    log.info("Getting footer subscribe button text from [Main page]");
    return find(footerSubscribeButton).getCssValue("text-transform");
  }

  public int getHeaderLanguageSelectValue(){
    log.info("Getting header select value from [Main page]");
    waitUntilVisible(headerLanguageSelector, 10);
    return findAll(headerLanguageSelector).size();
  }






}
