package ui;

import framework.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class FirstCase extends BaseTest{
  private final MainPage mainPage = new MainPage();
  @Test
  public void subscribeWithInvalidEmail(){
    mainPage.switchToFrame();
    String actualFooterNewsText = mainPage.getFooterNewsTextLocator();
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(actualFooterNewsText)
        .as("Footer news text on Main page is wrong")
        .isEqualTo("Get our latest news and special sales");
    String actualFooterUnsubscribeMessage = mainPage.getFooterUnsubscribeMessageLocator();
    softly.assertThat(actualFooterUnsubscribeMessage)
        .as("Footer unsubscribe message on Main page is wrong")
        .isEqualTo("You may unsubscribe at any moment. For that purpose, please find our contact info in the legal notice.");
    String actualFooterSubscribeButtonValue = mainPage.getFooterSubscribeButtonValue();
    softly.assertThat(actualFooterSubscribeButtonValue)
        .as("Footer subscribe button value on Main page is wrong")
        .isEqualTo("Subscribe");
    String actualFooterSubscribeButtonText = mainPage.getFooterSubscribeButtonText();
    softly.assertThat(actualFooterSubscribeButtonText)
        .as("Footer subscribe button text on Main page is wrong")
        .isEqualTo("uppercase");
    softly.assertAll();
  }

}
