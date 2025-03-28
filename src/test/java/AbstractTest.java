import static config.UrlConstants.BASE_URI;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractTest {

    @Before
    public void setUpForAllTests() {
        // Настройка спецификации для API-запросов
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URI)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        // Дополнительные общие настройки могут быть добавлены здесь
    }

    @After
    public void tearDown() {
        // Общий метод для очистки после тестов
        // Может быть переопределен в наследниках при необходимости
    }
}