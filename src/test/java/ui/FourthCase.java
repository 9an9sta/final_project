package ui;

import com.github.javafaker.Faker;
import framework.pages.MainPage;
import framework.pages.RegisterPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.Date;


public class FourthCase extends BaseTest {
    private static final Faker faker = new Faker();
    private final MainPage mainPage = new MainPage();
    private final RegisterPage registerPage = new RegisterPage();

    @Test
    public void registrationWithInvalidData() {
        String lastName = faker.name().lastName();
        String emailAddress = faker.internet().emailAddress();
        String password = faker.internet().password();
        Date dateOfBirthday = faker.date().birthday();

        mainPage.goToSignInPage()
                .clickOnNoAccountButton()
                .selectSocialTitle()
                .setInvalidFirstName()
                .setLastNameField(lastName)
                .setEmailField(emailAddress)
                .setPasswordField(password)
                .setBirthdayField(dateOfBirthday)
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
