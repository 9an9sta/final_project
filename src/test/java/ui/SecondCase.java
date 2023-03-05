package ui;

import framework.pages.MainPage;
import org.testng.annotations.Test;

@Test
public class SecondCase extends BaseTest{
  private final MainPage mainPage = new MainPage();
  public void checkLanguages(){
    mainPage.switchToFrame();
    System.out.println(mainPage.getHeaderLanguageSelectValue());




  }


}
