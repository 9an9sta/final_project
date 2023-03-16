package ui;

import framework.pages.BasePage;
import framework.pages.MainPage;
import framework.pages.RegisterPage;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ThirdCase extends BaseTest {
    private final MainPage mainPage = new MainPage();
    private final RegisterPage registerPage = new RegisterPage();
    @Test
    public void registrationWithValidData(){
        mainPage.switchToFrame()
                .goToSignInPage()
                .clickOnNoAccountButton()
                .selectSocialTitle();
        String expectedFirstName = registerPage.setFirstNameField();
        String expectedLastName = registerPage.setLastNameField();
        registerPage.setEmailField()
                .setPasswordField()
                .setBirthdayField()
                .chooseReceiveOffersFromOurPartnersCheckBox()
                .chooseCustomerDataPrivacyCheckBox()
                .chooseSignUpForOurNewsletterCheckBox()
                .chooseAgreeToTheTermsAndConditionsCheckBox()
                .clickOnSaveButton();
        Assertions.assertThat(mainPage.getCurrentSignUpUserName())
                .as("check")
                .isEqualTo(expectedFirstName + " " + expectedLastName);

    }


}
