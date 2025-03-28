import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertTrue;
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
import pages.SignUpPage;
import steps.UserSteps;

@Feature("Регистрация пользователя")
public class SignUpUserTest extends AbstractTest {

    private User user;
    private UserSteps userSteps = new UserSteps();
    private WebDriver driver;

    private HeaderPage headerPage;
    private LoginPage loginPage;
    private SignUpPage signUpPage;

    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        driver = WebDriverFactory.getWebDriver();
        headerPage = new HeaderPage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);

        Faker faker = new Faker(new Locale("en-GB"));
        user = new User();
        user.setName(faker.name().firstName());
        user.setPassword(faker.internet().password(6, 10));
        user.setEmail(faker.internet().emailAddress());
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
    @DisplayName("Регистрация нового пользователя (корректные данные)")
    @Description("Проверка успешной регистрации нового пользователя")
    public void registerUserWithCorrectData() {
        driver.get(SIGN_UP_URI);

        signUpPage.fillClientDataForRegistration(user.getName(), user.getEmail(), user.getPassword());
        signUpPage.clickOnSignUpButton();

        assertTrue("После регистрации должна отображаться страница входа",
                loginPage.isLoginPageDisplayed());

        String accessToken = userSteps
                .loginUser(user)
                .then()
                .extract().body().path("accessToken");
        user.setAccessToken(accessToken);
    }

    @Test
    @DisplayName("Регистрация с паролем короче 6 символов")
    @Description("Проверка валидации пароля при регистрации")
    public void registerUserWithIncorrectPassword() {
        user.setPassword(randomAlphabetic(5));
        driver.get(SIGN_UP_URI);

        signUpPage.fillClientDataForRegistration(user.getName(), user.getEmail(), user.getPassword());
        signUpPage.clickOnSignUpButton();

        assertTrue("Должно отображаться сообщение об ошибке пароля",
                signUpPage.isIncorrectPasswordMessageDisplayed());
    }
}