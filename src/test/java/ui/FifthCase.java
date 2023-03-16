package ui;

import framework.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class FifthCase extends BaseTest{
    private final MainPage mainPage = new MainPage();
    @Test
    public void checkCategories(){
        mainPage.switchToFrame();
        List<String> actualClothesCategoryItem = mainPage.getItemsFromClothesCategory();
        List<String> expectedClothesCategoryItem =  Arrays.asList("MEN", "WOMEN");
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualClothesCategoryItem)
                .as("'MEN' and 'WOMEN' sub menu items not appears in [Clothes] category")
                .containsExactlyElementsOf(expectedClothesCategoryItem);

        List<String> actualAccessoriesCategoryItem = mainPage.getItemsFromAccessoriesCategory();
        List<String> expectedAccessoriesCategoryItem =  Arrays.asList("STATIONERY", "HOME ACCESSORIES");
        softly.assertThat(actualAccessoriesCategoryItem)
                .as("'STATIONERY' and 'ACCESSORIES' sub menu items not appears in [Accessories] category")
                .containsExactlyElementsOf(expectedAccessoriesCategoryItem);
        List<String> actualArtCategoryItem = mainPage.getItemsFromArtCategory();
        softly.assertThat(actualArtCategoryItem)
                .as(" that no any sub category appears in [Art]")
                .isEmpty();
        softly.assertAll();


    }
}
