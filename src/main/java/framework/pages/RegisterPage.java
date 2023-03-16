package framework.pages;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;


import framework.helpers.SignUpHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage{
    private final By noAccountButtonLocator = By.xpath("//div[@class='no-account']/a");
    private final By socialRadioButtonMrLocator = By.xpath("//input[@id='field-id_gender-1']");
    private final By socialRadioButtonMrsLocator = By.xpath("//input[@id='field-id_gender-2']");
    private final By firstNameFieldLocator = By.xpath("//input[@id='field-firstname']");
    private final By lastNameFieldLocator = By.xpath("//input[@id='field-lastname']");
    private final By emailFieldLocator = By.xpath("//input[@id='field-email']");
    private final By passwordFieldLocator = By.xpath("//input[@id='field-password']");
    private final By birthdayFieldLocator = By.xpath("//input[@id='field-birthday']");
    private final By receiveOffersFromOurPartnersCheckBoxLocator = By.xpath("//label/input[@name='optin']");
    private final By customerDataPrivacyCheckBoxLocator = By.xpath("//label/input[@name='customer_privacy']");
    private final By signUpForOurNewsletterCheckBoxLocator = By.xpath("//label/input[@name='newsletter']");
    private final By agreeToTheTermsAndConditionsCheckBoxLocator = By.xpath("//label/input[@name='psgdpr']");
    private final By saveButtonLocator = By.xpath("//button[@data-link-action='save-customer']");
    private  final By alertFirstNameLocator = By.xpath("//div[@class='help-block']//li[@class='alert alert-danger']");

    public RegisterPage clickOnNoAccountButton(){
        find(noAccountButtonLocator).click();
        return this;
    }

    public RegisterPage selectSocialTitle(){
        By[] locators = new By[]{socialRadioButtonMrLocator, socialRadioButtonMrsLocator};
        Random rand = new Random();
        int index = rand.nextInt(locators.length);
        By randomLocator = locators[index];
        find(randomLocator).click();
        return this;
    }
    public String setFirstNameField(){
        WebElement firstNameLocator = find(firstNameFieldLocator);
        String firstName = SignUpHelper.generateValidFirstName();
        firstNameLocator.sendKeys(firstName);
        return firstName;
    }
    public String setLastNameField(){
        WebElement lastNameLocator = find(lastNameFieldLocator);
        String lastName = SignUpHelper.generateValidLastName();
        lastNameLocator.sendKeys(lastName);
        return lastName;
    }
    public RegisterPage setValidLastNameField(){
        find(lastNameFieldLocator).sendKeys(SignUpHelper.generateValidLastName());
        return this;
    }
    public RegisterPage setEmailField(){
        find(emailFieldLocator).sendKeys(SignUpHelper.generateValidEmail());
        return this;
    }
    public RegisterPage setPasswordField(){
        find(passwordFieldLocator).sendKeys(SignUpHelper.generateValidPassword());
        return this;
    }

    public RegisterPage setBirthdayField(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        find(birthdayFieldLocator).sendKeys(formatter.format(SignUpHelper.generateValidBirthday()));
        return this;
    }
    public RegisterPage chooseReceiveOffersFromOurPartnersCheckBox(){
        find(receiveOffersFromOurPartnersCheckBoxLocator).click();
        return this;
    }
    public RegisterPage chooseCustomerDataPrivacyCheckBox(){
        find(customerDataPrivacyCheckBoxLocator).click();
        return this;
    }
    public RegisterPage chooseSignUpForOurNewsletterCheckBox(){
        find(signUpForOurNewsletterCheckBoxLocator).click();
        return this;
    }
    public RegisterPage chooseAgreeToTheTermsAndConditionsCheckBox(){
        find(agreeToTheTermsAndConditionsCheckBoxLocator).click();
        return this;
    }
    public MainPage clickOnSaveButton(){
        find(saveButtonLocator).click();
        return new MainPage();
    }

    public RegisterPage setInvalidFirstName(){
        find(firstNameFieldLocator).sendKeys("James8");
        return this;
    }

    public String checkThatFirstNameFieldMarkedAsRed(){
        return find(firstNameFieldLocator).getCssValue("outline");
    }
    public String getFirstNameAlert(){
        return find(alertFirstNameLocator).getText();
    }













}
