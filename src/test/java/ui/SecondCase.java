package ui;

import framework.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class SecondCase extends BaseTest {
    private final MainPage mainPage = new MainPage();

    public void checkLanguages() {
        int actualLanguageCountInHeaderSelect = mainPage.getHeaderLanguageSelectValue();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualLanguageCountInHeaderSelect)
                .as("Current Language count in header select is not 44")
                .isEqualTo(44);
        List<String> currentLanguage = mainPage.getAllLanguagesFromLanguageSelector();
        softly.assertThat(currentLanguage)
                .as("Ukrainian language aren't present")
                .contains("Українська");
        softly.assertAll();
    }

}
