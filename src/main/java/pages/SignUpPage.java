package pages;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
    private final WebDriver driver;
    private final By signUpButton = By.xpath(".//button[contains(text(), 'Зарегистрироваться')]");
    private final By signInLink = By.xpath(".//a[contains(text(), 'Войти')]");
    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By incorrectPasswordMessage = By.xpath(".//p[contains(text(), 'Некорректный пароль')]");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillClientDataForRegistration(String name, String email, String password) {
        new WebDriverWait(driver, ofSeconds(10))
                .until(elementToBeClickable(nameField)).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }

    public boolean isIncorrectPasswordMessageDisplayed() {
        return new WebDriverWait(driver, ofSeconds(10))
                .until(visibilityOfElementLocated(incorrectPasswordMessage))
                .isDisplayed();
    }

    public void clickOnSignUpButton() {
        new WebDriverWait(driver, ofSeconds(10))
                .until(elementToBeClickable(signUpButton))
                .click();
    }

    public void clickOnSignInLink() {
        new WebDriverWait(driver, ofSeconds(10))
                .until(elementToBeClickable(signInLink))
                .click();

        // Явное ожидание перехода на страницу входа
        new WebDriverWait(driver, ofSeconds(10))
                .until(urlContains("/login"));
    }
}