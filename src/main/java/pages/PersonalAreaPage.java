package pages;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAreaPage {
    private final WebDriver driver;
    private final By profileLink = By.xpath(".//a[text()='Профиль']");
    private final By logOutButton = By.xpath(".//button[text()='Выход']");
    private final By personalAreaTitle = By.xpath(".//h2[contains(text(), 'персональные данные')]");

    public PersonalAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPageOpen() {
        try {
            new WebDriverWait(driver, ofSeconds(15))
                    .until(visibilityOfElementLocated(profileLink));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogOutButton() {
        new WebDriverWait(driver, ofSeconds(15))
                .until(elementToBeClickable(logOutButton)).click();
    }
}