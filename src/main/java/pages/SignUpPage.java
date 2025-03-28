package pages;

import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {

    private WebDriver driver;

    private By signUpButtonLocator = By.xpath(".//button[text()='Зарегистрироваться']");
    private By signInLinkLocator = By.xpath(".//a[text()='Войти']");
    private By nameFieldLocator = By.xpath(".//label[text()='Имя']/parent::div/input[@class = 'text input__textfield text_type_main-default']");
    private By emailFieldLocator = By.xpath(".//label[text()='Email']/parent::div/input[@class = 'text input__textfield text_type_main-default']");
    private By passwordFieldLocator = By.xpath(".//label[text()='Пароль']/parent::div/input[@class = 'text input__textfield text_type_main-default']");
    private By incorrectPasswordMessageLocator = By.xpath(".//p[text()='Некорректный пароль']");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public By getSignUpButtonLocator() {
        return signUpButtonLocator;
    }

    public By getSignInLinkLocator() {
        return signInLinkLocator;
    }

    public By getNameFieldLocator() {
        return nameFieldLocator;
    }

    public By getEmailFieldLocator() {
        return emailFieldLocator;
    }

    public By getPasswordFieldLocator() {
        return passwordFieldLocator;
    }

    public By getIncorrectPasswordMessageLocator() {
        return incorrectPasswordMessageLocator;
    }

    public void fillClientDataForRegistration(String name, String email, String password) {
        driver.findElement(nameFieldLocator).sendKeys(name);
        driver.findElement(emailFieldLocator).sendKeys(email);
        driver.findElement(passwordFieldLocator).sendKeys(password);
    }

    public boolean isIncorrectPasswordMessageLocator() {
        return driver.findElement(incorrectPasswordMessageLocator).isDisplayed();
    }

    public void clickOnSignUpButton(By element) {
        driver.findElement(signUpButtonLocator).click();
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void clickOnSignInLink(By element) {
        driver.findElement(signInLinkLocator).click();
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
