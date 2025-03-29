import static org.junit.Assert.assertTrue;
import static config.UrlConstants.BASE_URI;
import static config.UrlConstants.RECOVERY_PASSWORD_URI;
import static config.UrlConstants.SIGN_UP_URI;

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
import pages.HeaderPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RecoveryPasswordPage;
import pages.SignUpPage;
import steps.UserSteps;

@Feature("Login user")
public class LoginUserTest extends AbstractTest {

    private User user;
    private UserSteps userSteps = new UserSteps();
    private WebDriver webDriver;
    private HeaderPage headerPage;
    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private MainPage mainPage;
    private RecoveryPasswordPage recoveryPasswordPage;

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

        webDriver = WebDriverFactory.getWebDriver();
        headerPage = new HeaderPage(webDriver);
        loginPage = new LoginPage(webDriver);
        signUpPage = new SignUpPage(webDriver);
        mainPage = new MainPage(webDriver);
        recoveryPasswordPage = new RecoveryPasswordPage(webDriver);

        webDriver.get(BASE_URI);
    }

    @Test
    @DisplayName("Login via main page button")
    @Description("Successful login using 'Log in to account' button on main page")
    public void loginOnMainPageByCreateOrderButton() {
        mainPage.clickLoginToAccountButton();
        loginPage.waitForSignInButtonVisible();

        loginPage.fillClientDataForLogin(user.getEmail(), user.getPassword());
        loginPage.clickSignInButton();
        mainPage.waitForCreateOrderButtonVisible();

        assertTrue("Create order button should be visible after login", mainPage.isAuthorizeMode());
    }

    @Test
    @DisplayName("Login via personal area button")
    @Description("Successful login using personal area button in header")
    public void loginOnHeaderByPersonalAreaButton() {
        headerPage.clickOnPersonalAreaButton();
        loginPage.waitForSignInButtonVisible();

        loginPage.fillClientDataForLogin(user.getEmail(), user.getPassword());
        loginPage.clickSignInButton();
        mainPage.waitForCreateOrderButtonVisible();

        assertTrue("Create order button should be visible after login", mainPage.isAuthorizeMode());
    }

    @Test
    @DisplayName("Login via sign up page link")
    @Description("Successful login using 'Sign in' link on registration page")
    public void loginOnSignUpFormBySignInLink() {
        webDriver.get(SIGN_UP_URI);
        signUpPage.clickOnSignInLink();
        loginPage.waitForSignInButtonVisible();

        loginPage.fillClientDataForLogin(user.getEmail(), user.getPassword());
        loginPage.clickSignInButton();
        mainPage.waitForCreateOrderButtonVisible();

        assertTrue("Create order button should be visible after login", mainPage.isAuthorizeMode());
    }

    @Test
    @DisplayName("Login via password recovery page link")
    @Description("Successful login using 'Sign in' link on password recovery page")
    public void loginOnPasswordRecoveryPageBySignInLink() {
        webDriver.get(RECOVERY_PASSWORD_URI);
        recoveryPasswordPage.clickOnSignInLink();
        loginPage.waitForSignInButtonVisible();

        loginPage.fillClientDataForLogin(user.getEmail(), user.getPassword());
        loginPage.clickSignInButton();
        mainPage.waitForCreateOrderButtonVisible();

        assertTrue("Create order button should be visible after login", mainPage.isAuthorizeMode());
    }

    @After
    public void tearDown() {
        webDriver.quit();

        if (user.getAccessToken() != null) {
            userSteps.deleteUser(user);
        }
    }
}