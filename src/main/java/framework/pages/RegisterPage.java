package framework.pages;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Log4j2
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

    @Step("Click on No account button")
    public RegisterPage clickOnNoAccountButton(){
        log.info("Click on No account button");
        find(noAccountButtonLocator).click();
        return this;
    }
    @Step("Selecting social title on [Register] page")
    public RegisterPage selectSocialTitle(){
        log.info("Selecting social title on [Register] page");
        By[] locators = new By[]{socialRadioButtonMrLocator, socialRadioButtonMrsLocator};
        Random rand = new Random();
        int index = rand.nextInt(locators.length);
        By randomLocator = locators[index];
        find(randomLocator).click();
        return this;
    }
    @Step("Set first name field")
    public RegisterPage setFirstNameField(String firstName){
        log.info("Set first name field");
        WebElement firstNameLocator = find(firstNameFieldLocator);
        firstNameLocator.sendKeys(firstName);
        return this;
    }
    @Step("Set last name field")
    public RegisterPage setLastNameField(String lastName){
        log.info("Set last name field");
        WebElement lastNameLocator = find(lastNameFieldLocator);
        lastNameLocator.sendKeys(lastName);
        return this;
    }
    @Step("Set valid last name field")
    public RegisterPage setValidLastNameField(String lastName){
        log.info("Set valid last name field");
        WebElement lastNameLocator = find(lastNameFieldLocator);
        lastNameLocator.sendKeys(lastName);
        return this;
    }
    @Step("Set email field")
    public RegisterPage setEmailField(String email){
        log.info("Set email field");
        find(emailFieldLocator).sendKeys(email);
        return this;
    }
    @Step("Set password field")
    public RegisterPage setPasswordField(String password){
        log.info("Set password field");
        find(passwordFieldLocator).sendKeys(password);
        return this;
    }
    @Step("Set Birthday field")
    public RegisterPage setBirthdayField(Date birthday){
        log.info("Set Birthday field");
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        find(birthdayFieldLocator).sendKeys(formatter.format(birthday));
        return this;
    }
    @Step("Choose option [Receive Offers from our partners]")
    public RegisterPage chooseReceiveOffersFromOurPartnersCheckBox(){
        log.info("Choose option [Receive Offers from our partners]");
        find(receiveOffersFromOurPartnersCheckBoxLocator).click();
        return this;
    }
    @Step("Choose option [Customer data Privacy]")
    public RegisterPage chooseCustomerDataPrivacyCheckBox(){
        log.info("Choose option [Customer data Privacy]");
        find(customerDataPrivacyCheckBoxLocator).click();
        return this;
    }
    @Step("Choose option [Sign Up for our news letter]")
    public RegisterPage chooseSignUpForOurNewsletterCheckBox(){
        log.info("Choose option [Sign Up for our news letter]");
        find(signUpForOurNewsletterCheckBoxLocator).click();
        return this;
    }
    @Step("Choose option [Agree to the Terms and conditions]")
    public RegisterPage chooseAgreeToTheTermsAndConditionsCheckBox(){
        log.info("Choose option [Agree to the Terms and conditions]");
        find(agreeToTheTermsAndConditionsCheckBoxLocator).click();
        return this;
    }
    @Step("Click on Save button")
    public MainPage clickOnSaveButton(){
        log.info("Click on Save button");
        find(saveButtonLocator).click();
        return new MainPage();
    }
    @Step ("Set invalid Firs Name")
    public RegisterPage setInvalidFirstName(){
        log.info("Set invalid Firs Name");
        find(firstNameFieldLocator).sendKeys("James8");
        return this;
    }
    @Step("Checking that First Name field marked as red")
    public String checkThatFirstNameFieldMarkedAsRed(){
        log.info("Checking that First Name field marked as red");
        return find(firstNameFieldLocator).getCssValue("outline");
    }
    @Step("Get Firs Name Alert")
    public String getFirstNameAlert(){
        log.info("Get Firs Name Alert");
        return find(alertFirstNameLocator).getText();
    }













}
