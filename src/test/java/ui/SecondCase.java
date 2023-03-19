package ui;

import framework.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

@Test
public class SecondCase extends BaseTest{
  private final MainPage mainPage = new MainPage();
  public void checkLanguages(){
    mainPage.switchToFrame();
    int actualLanguageCountInHeaderSelect = mainPage.getHeaderLanguageSelectValue();
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(actualLanguageCountInHeaderSelect)
            .as("Current Language count in header select is not 44")
            .isEqualTo(44);
    Boolean IsUkrainianLanguageIsPresent = mainPage.checkIfLanguagesSelectorHasUkraineLanguage();
    softly.assertThat(IsUkrainianLanguageIsPresent)
            .as("Ukrainian language aren't present")
            .isTrue();

    softly.assertAll();




  }


}
