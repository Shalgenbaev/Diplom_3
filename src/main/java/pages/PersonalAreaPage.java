package pages;

import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAreaPage {

    private WebDriver driver;

    private By profileLinkLocator = By.xpath(".//a[text()='Профиль']");
    private By logOutButtonLocator = By.xpath(".//button[text()='Выход']");

    public PersonalAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public By getProfileLinkLocator() {
        return profileLinkLocator;
    }

    public By getLogOutButtonLocator() {
        return logOutButtonLocator;
    }

    public boolean isPersonalAreaPage() {
        return driver.findElement(profileLinkLocator).isDisplayed();
    }

    public void clickOnLogOutButton(By element) {
        driver.findElement(logOutButtonLocator).click();
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

}
