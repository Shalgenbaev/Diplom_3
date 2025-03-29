import static org.junit.Assert.assertTrue;
import static config.UrlConstants.SIGN_IN_URI;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import java.util.Locale;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import config.WebDriverFactory;
import model.User;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HeaderPage;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAreaPage;
import steps.UserSteps;

@Feature("Personal area")
public class PersonalAreaTest extends AbstractTest {

    private User user;
    private UserSteps userSteps = new UserSteps();
    private WebDriver driver;
    private WebDriverWait wait;

    private HeaderPage headerPage;
    private LoginPage loginPage;
    private MainPage mainPage;
    private PersonalAreaPage personalAreaPage;

    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        Faker faker = new Faker(new Locale("en-GB"));

        user = new User();
        user.setName(faker.name().firstName());
        user.setPassword(faker.internet().password(6, 10));
        user.setEmail(faker.internet().emailAddress());

        String accessToken = userSteps
                .createUser(user)
                .then()
                .extract().body().path("accessToken");
        user.setAccessToken(accessToken);

        driver = WebDriverFactory.getWebDriver();
        wait = new WebDriverWait(driver, ofSeconds(15));

        headerPage = new HeaderPage(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        personalAreaPage = new PersonalAreaPage(driver);

        // Авторизация пользователя
        driver.get(SIGN_IN_URI);
        wait.until(urlContains(SIGN_IN_URI));

        loginPage.fillClientDataForLogin(user.getEmail(), user.getPassword());
        loginPage.clickSignInButton();

        // Ожидание успешной авторизации
        wait.until(visibilityOfElementLocated(mainPage.getCreateOrderButtonLocator()));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        if (user.getAccessToken() != null) {
            userSteps.deleteUser(user);
        }
    }

    @Test
    @DisplayName("Переход в личный кабинет через кнопку в хедере")
    @Description("Проверка перехода в личный кабинет авторизованного пользователя")
    public void transferToPersonalAreaByButtonInHeader() {
        headerPage.clickOnPersonalAreaButton();
        wait.until(urlContains("/account/profile"));
        assertTrue("Должна отображаться страница личного кабинета",
                personalAreaPage.isPageOpen());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор через кнопку 'Конструктор'")
    @Description("Проверка перехода из ЛК в конструктор через кнопку в хедере")
    public void transferToMainPageByConstructorButton() {
        headerPage.clickOnPersonalAreaButton();
        wait.until(urlContains("/account/profile"));

        headerPage.clickOnConstructorButton();
        wait.until(visibilityOfElementLocated(mainPage.getCreateOrderButtonLocator()));

        assertTrue("Должен отображаться конструктор в авторизованном режиме",
                mainPage.isAuthorizeMode());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор через логотип")
    @Description("Проверка перехода из ЛК в конструктор через клик на логотип")
    public void transferToMainPageByLogo() {
        headerPage.clickOnPersonalAreaButton();
        wait.until(urlContains("/account/profile"));

        headerPage.clickOnLogo();
        wait.until(visibilityOfElementLocated(mainPage.getCreateOrderButtonLocator()));

        assertTrue("Должен отображаться конструктор в авторизованном режиме",
                mainPage.isAuthorizeMode());
    }

    @Test
    @DisplayName("Выход из аккаунта через кнопку 'Выйти'")
    @Description("Проверка выхода из аккаунта через личный кабинет")
    public void logOutOnPersonalArea() {
        headerPage.clickOnPersonalAreaButton();
        wait.until(urlContains("/account/profile"));

        personalAreaPage.clickLogOutButton();
        wait.until(urlContains(SIGN_IN_URI));

        assertTrue("Должна отображаться страница входа",
                loginPage.isLoginPageDisplayed());
    }
}