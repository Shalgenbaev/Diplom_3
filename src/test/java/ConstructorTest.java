import static org.junit.Assert.assertTrue;
import static config.UrlConstants.BASE_URI;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import config.WebDriverFactory;
import pages.MainPage;

@Feature("Навигация по конструктору")
public class ConstructorTest extends AbstractTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = WebDriverFactory.getWebDriver();
        mainPage = new MainPage(driver);
        driver.get(BASE_URI);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Проверка активной вкладки по умолчанию")
    @Description("По умолчанию должна быть активна вкладка 'Булки'")
    public void defaultTabShouldBeBuns() {
        assertTrue("По умолчанию должна быть активна вкладка 'Булки'",
                mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход на вкладку 'Соусы'")
    @Description("При переходе на вкладку 'Соусы' она должна стать активной")
    public void shouldActivateSaucesTab() {
        mainPage.clickSaucesTab();
        assertTrue("Вкладка 'Соусы' должна быть активной после перехода",
                mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход на вкладку 'Начинки'")
    @Description("При переходе на вкладку 'Начинки' она должна стать активной")
    public void shouldActivateFillingsTab() {
        mainPage.clickFillingsTab();
        assertTrue("Вкладка 'Начинки' должна быть активной после перехода",
                mainPage.isFillingsTabActive());
    }

    @Test
    @DisplayName("Возврат на вкладку 'Булки'")
    @Description("При возврате на вкладку 'Булки' она должна снова стать активной")
    public void shouldReturnToBunsTab() {
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        assertTrue("Вкладка 'Булки' должна быть активной после возврата",
                mainPage.isBunsTabActive());
    }
}