package steps;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.User;

public class UserSteps {

    private static final String REGISTER_ENDPOINT = "/api/auth/register";
    private static final String USER_ENDPOINT = "/api/auth/user";
    private static final String LOGIN_ENDPOINT = "/api/auth/login";

    @Step("Создать нового пользователя")
    public Response createUser(User user) {
        return given()
                .body(user)
                .when()
                .post(REGISTER_ENDPOINT);
    }

    @Step("Авторизовать пользователя и получить токен")
    public Response loginUser(User user) {
        return given()
                .body(user)
                .when()
                .post(LOGIN_ENDPOINT);
    }

    @Step("Удалить пользователя")
    public Response deleteUser(User user) {
        return given()
                .header("Authorization", user.getAccessToken())
                .when()
                .delete(USER_ENDPOINT);
    }

    @Step("Получить accessToken пользователя")
    public String getAccessToken(User user) {
        return loginUser(user)
                .then()
                .extract()
                .path("accessToken");
    }
}