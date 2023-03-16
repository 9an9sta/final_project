package ui;

import framework.pages.MainPage;
import framework.pages.RegisterPage;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;


public class FourthCase extends BaseTest{
    private final MainPage mainPage = new MainPage();
    private final RegisterPage registerPage = new RegisterPage();
    @Test
    public void registrationWithInvalidData(){
        mainPage.switchToFrame()
                .goToSignInPage()
                .clickOnNoAccountButton()
                .selectSocialTitle()
                .setInvalidFirstName()
                .setValidLastNameField()
                .setEmailField()
                .setPasswordField()
                .setBirthdayField()
                .chooseReceiveOffersFromOurPartnersCheckBox()
                .chooseCustomerDataPrivacyCheckBox()
                .chooseSignUpForOurNewsletterCheckBox()
                .chooseAgreeToTheTermsAndConditionsCheckBox()
                .clickOnSaveButton();
        SoftAssertions softly = new SoftAssertions();
        String actualFirstNameFieldMarkedAsRed = registerPage.checkThatFirstNameFieldMarkedAsRed();
        softly.assertThat(actualFirstNameFieldMarkedAsRed)
                .as("That 'First name' highlighted but another color, except red")
                .contains("rgb(255, 76, 76)");
        String actualFirstNameAlertText = registerPage.getFirstNameAlert();
        softly.assertThat(actualFirstNameAlertText)
                .as("Pop-up with text 'Invalid name' not appear under field")
                .isEqualTo("Invalid format.");
        softly.assertAll();

    }
}
