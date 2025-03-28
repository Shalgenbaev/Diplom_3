package pages;

import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;

    private By signUpLinkLocator = By.xpath(".//a[text()='Зарегистрироваться']");
    private By signInButtonLocator = By.xpath(".//button[text()='Войти']");
    private By emailFieldLocator = By.xpath(".//label[text()='Email']/parent::div/input[@class = 'text input__textfield text_type_main-default']");
    private By passwordFieldLocator = By.xpath(".//label[text()='Пароль']/parent::div/input[@class = 'text input__textfield text_type_main-default']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public By getSignUpLinkLocator() {
        return signUpLinkLocator;
    }

    public By getSignInButtonLocator() {
        return signInButtonLocator;
    }

    public By getEmailFieldLocator() {
        return emailFieldLocator;
    }

    public By getPasswordFieldLocator() {
        return passwordFieldLocator;
    }

    public void clickSignUpLink(By element) {
        driver.findElement(signUpLinkLocator).click();
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void clickSignInButton(By element) {
        driver.findElement(signInButtonLocator).click();
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public boolean isLoginPage() {
        return driver.findElement(signInButtonLocator).isDisplayed();
    }

    public void fillClientDataForLogin(String email, String password) {
        driver.findElement(emailFieldLocator).sendKeys(email);
        driver.findElement(passwordFieldLocator).sendKeys(password);
    }

}
