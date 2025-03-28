package tests;

import static config.UrlConstants.BASE_URI;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.Before;

public abstract class AbstractTest {

    @Before
    public void setUpForAllRequests() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON) // Устанавливаем тип содержимого
                .setBaseUri(BASE_URI)              // Устанавливаем базовый URI
                .build();                          // Создаем спецификацию запроса
    }
}
