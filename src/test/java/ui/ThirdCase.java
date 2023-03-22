package ui;

import com.github.javafaker.Faker;
import framework.pages.MainPage;
import framework.pages.RegisterPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Date;

public class ThirdCase extends BaseTest {
    private static final Faker faker = new Faker();
    private final MainPage mainPage = new MainPage();
    private final RegisterPage registerPage = new RegisterPage();

    @Test
    public void registrationWithValidData() {
        String expectedFirstName = faker.name().firstName();
        String expectedLastName = faker.name().lastName();
        String emailAddress = faker.internet().emailAddress();
        String password = faker.internet().password();
        Date dateOfBirthday = faker.date().birthday();
        mainPage.goToSignInPage()
                .clickOnNoAccountButton()
                .selectSocialTitle()
                .setFirstNameField(expectedFirstName)
                .setValidLastNameField(expectedLastName)
                .setEmailField(emailAddress)
                .setPasswordField(password)
                .setBirthdayField(dateOfBirthday)
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
