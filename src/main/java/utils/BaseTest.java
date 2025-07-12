package utils;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@TestMethodOrder(MethodOrderer.MethodName.class)
@ExtendWith(ScreenshotOnFailureExtension.class)
public abstract class BaseTest {

    protected WebDriver driver;
    private static boolean environmentWritten = false;

    @BeforeEach
    void launchBrowser() {
        String browser = ConfigReader.get("browser");
        String baseUrl = ConfigReader.get("baseUrl");
        boolean minimize = Boolean.parseBoolean(ConfigReader.get("minimize"));

        driver = DriverFactory.createInstance(browser);
        ScreenshotOnFailureExtension.driver = driver;

        if (minimize){
            driver.manage().window().minimize();
        } else {
            driver.manage().window().maximize();
        }

        driver.get(baseUrl);

        if (!environmentWritten) {
            EnvironmentWriter.write(driver);
            environmentWritten = true;
        }
    }


    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
