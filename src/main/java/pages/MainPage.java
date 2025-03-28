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

    public WebDriver getDriver() {
        return driver;
    }

    public By getLoginToAccountButtonLocator() {
        return loginToAccountButtonLocator;
    }

    public By getCreateOrderButtonLocator() {
        return createOrderButtonLocator;
    }

    public By getBunsTabLocator() {
        return bunsTabLocator;
    }

    public By getSaucesTabLocator() {
        return saucesTabLocator;
    }

    public By getFillingsTabLocator() {
        return fillingsTabLocator;
    }

    public By getActiveTab() {
        return activeTab;
    }

    public void clickLoginToAccountButton(By element) {
        driver.findElement(loginToAccountButtonLocator).click();
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public boolean isAuthorizeMode() {
        return driver.findElement(createOrderButtonLocator).isDisplayed();
    }

    public boolean isNonAuthorizeMode() {
        return driver.findElement(loginToAccountButtonLocator).isDisplayed();
    }

    public void clickBunsTab() {
        driver.findElement(bunsTabLocator).click();
    }

    public boolean isBunsTabActive() {
        return driver.findElement(activeTab).getText().equals("Булки");
    }

    public void clickSaucesTab() {
        driver.findElement(saucesTabLocator).click();
    }

    public boolean isSaucesTabActive() {
        return driver.findElement(activeTab).getText().equals("Соусы");
    }

    public void clickFillingsTab() {
        driver.findElement(fillingsTabLocator).click();
    }

    public boolean isFillingsTabActive() {
        return driver.findElement(activeTab).getText().equals("Начинки");
    }

}
