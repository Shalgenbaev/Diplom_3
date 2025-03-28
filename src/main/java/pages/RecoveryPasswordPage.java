package pages;

import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecoveryPasswordPage {

    private WebDriver driver;
    private By signUpLinkLocator = By.xpath(".//button[text()='Восстановить']");
    private By signInLinkLocator = By.xpath(".//a[text()='Войти']");

    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public By getSignUpLinkLocator() {
        return signUpLinkLocator;
    }

    public By getSignInLinkLocator() {
        return signInLinkLocator;
    }

    public void clickOnSignInLink(By element) {
        driver.findElement(signInLinkLocator).click();
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

}
