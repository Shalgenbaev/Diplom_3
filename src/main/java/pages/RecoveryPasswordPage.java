package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoveryPasswordPage {
    private final WebDriver driver;
    private final By signInLinkLocator = By.xpath(".//a[text()='Войти']");
    private final By signUpLinkLocator = By.xpath(".//button[text()='Восстановить']");

    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать на ссылку 'Войти' на странице восстановления пароля")
    public void clickOnSignInLink() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(signInLinkLocator))
                .click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("login"));
    }
}