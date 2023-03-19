package ui;

import com.github.javafaker.Faker;
import framework.pages.BasePage;
import framework.pages.MainPage;
import framework.pages.RegisterPage;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ThirdCase extends BaseTest {
    private final MainPage mainPage = new MainPage();
    private final RegisterPage registerPage = new RegisterPage();
    private static final Faker faker = new Faker();
    @Test
    public void registrationWithValidData(){
        mainPage.switchToFrame()
                .goToSignInPage()
                .clickOnNoAccountButton()
                .selectSocialTitle();
        String expectedFirstName = registerPage.setFirstNameField(faker.name().firstName());
        String expectedLastName = registerPage.setValidLastNameField(faker.name().lastName());
        registerPage.setEmailField(faker.internet().emailAddress())
                .setPasswordField(faker.internet().password())
                .setBirthdayField(faker.date().birthday())
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
