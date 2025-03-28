package pages;

import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderPage {
    private WebDriver driver;
    private By personalAreaButtonLocator = By.xpath(".//p[text()='Личный Кабинет']");
    private By constructorButtonLocator = By.xpath(".//p[text()='Конструктор']");
    private By feedOrderButtonLocator = By.xpath(".//p[text()='Лента заказов']");
    private By logoLocator = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnPersonalAreaButton() {
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(personalAreaButtonLocator)).click();
    }

    public void clickOnConstructorButton() {
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(constructorButtonLocator)).click();
    }

    public void clickOnLogo() {
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(logoLocator)).click();
    }
}