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
    private By emailFieldLocator = By.xpath(".//label[text()='Email']/parent::div/input");
    private By passwordFieldLocator = By.xpath(".//label[text()='Пароль']/parent::div/input");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignUpLink() {
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(signUpLinkLocator)).click();
    }

    public void clickSignInButton() {
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(signInButtonLocator)).click();
    }

    public boolean isLoginPageDisplayed() {
        return new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(signInButtonLocator)).isDisplayed();
    }

    public void fillClientDataForLogin(String email, String password) {
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(emailFieldLocator)).sendKeys(email);
        driver.findElement(passwordFieldLocator).sendKeys(password);
    }

    public void waitForSignInButtonVisible() {
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(signInButtonLocator));
    }
}