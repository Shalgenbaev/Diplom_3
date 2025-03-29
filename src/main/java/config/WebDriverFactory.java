package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

    private static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            // Получаем browser из системного свойства, установленного при запуске
            String browserType = System.getProperty("browser", "chrome");

            switch (browserType.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().create();
                    return new ChromeDriver();
                case "yandex":
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/yandexdriver.exe");
                    return new ChromeDriver();
                default:
                    throw new RuntimeException("Unsupported browser: " + browserType);
            }
        }
        return webDriver;
    }
}
