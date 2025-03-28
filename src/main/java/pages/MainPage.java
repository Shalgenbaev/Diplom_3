package pages;

import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    private By loginToAccountButtonLocator = By.xpath(".//button[text()='Войти в аккаунт']");
    private By createOrderButtonLocator = By.xpath(".//button[text()='Оформить заказ']");
    private By bunsTabLocator = By.xpath("//span[text()='Булки']");
    private By saucesTabLocator = By.xpath("//span[text()='Соусы']");
    private By fillingsTabLocator = By.xpath("//span[text()='Начинки']");
    private By activeTab = By.className("tab_tab_type_current__2BEPc");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Добавьте этот метод
    public By getCreateOrderButtonLocator() {
        return createOrderButtonLocator;
    }

    public void clickLoginToAccountButton() {
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginToAccountButtonLocator)).click();
    }

    public boolean isAuthorizeMode() {
        return new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButtonLocator)).isDisplayed();
    }

    public boolean isNonAuthorizeMode() {
        return new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginToAccountButtonLocator)).isDisplayed();
    }

    public void clickBunsTab() {
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(bunsTabLocator)).click();
    }

    public boolean isBunsTabActive() {
        return new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(activeTab)).getText().equals("Булки");
    }

    public void clickSaucesTab() {
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(saucesTabLocator)).click();
    }

    public boolean isSaucesTabActive() {
        return new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(activeTab)).getText().equals("Соусы");
    }

    public void clickFillingsTab() {
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(fillingsTabLocator)).click();
    }

    public boolean isFillingsTabActive() {
        return new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(activeTab)).getText().equals("Начинки");
    }

    public void waitForCreateOrderButtonVisible() {
        new WebDriverWait(driver, ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButtonLocator));
    }
}