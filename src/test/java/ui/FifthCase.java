package ui;

import framework.helpers.MenuCategoryHelper;
import framework.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;
public class FifthCase extends BaseTest{
    private final MainPage mainPage = new MainPage();
    @Test
    public void checkCategories(){
        List<String> actualClothesCategoryItem = mainPage.getCategoryFromHeaderMenu(MenuCategoryHelper.MenuCategory.CLOTHES);
        List<String> expectedClothesCategoryItem =  mainPage.expectedCategoryItemsFromHeaderMenu(MenuCategoryHelper.MenuSubCategory.CLOTHES_ITEMS);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualClothesCategoryItem)
                .as("'MEN' and 'WOMEN' sub menu items not appears in [Clothes] category")
                .containsExactlyElementsOf(expectedClothesCategoryItem);

        List<String> actualAccessoriesCategoryItem = mainPage.getCategoryFromHeaderMenu(MenuCategoryHelper.MenuCategory.ACCESSORIES);
        List<String> expectedAccessoriesCategoryItem =  mainPage.expectedCategoryItemsFromHeaderMenu(MenuCategoryHelper.MenuSubCategory.ACCESSORIES_ITEMS);
        softly.assertThat(actualAccessoriesCategoryItem)
                .as("'STATIONERY' and 'ACCESSORIES' sub menu items not appears in [Accessories] category")
                .containsExactlyElementsOf(expectedAccessoriesCategoryItem);
        List<String> actualArtCategoryItem = mainPage.getCategoryFromHeaderMenu(MenuCategoryHelper.MenuCategory.ART);
        softly.assertThat(actualArtCategoryItem)
                .as(" that no any sub category appears in [Art]")
                .isEmpty();
        softly.assertAll();

    }
}
