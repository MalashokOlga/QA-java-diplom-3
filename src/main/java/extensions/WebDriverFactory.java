package extensions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

import static config.AppConfig.URL;
import static config.WebDriverConfig.WAIT_SECONDS_TIMEOUT;

public class WebDriverFactory {
    public static WebDriver get() {
        String browserName = System.getenv().get("browser");

        WebDriver driver;
        if (browserName == null) {browserName = "yandex";}

        switch (browserName) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandexdriver.exe");
                ChromeOptions optionsYa = new ChromeOptions();
                optionsYa.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(optionsYa);
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default: throw new RuntimeException("Browser " + browserName + " not exist");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_SECONDS_TIMEOUT));
        driver.navigate().to(URL);
        return driver;
    }
}
