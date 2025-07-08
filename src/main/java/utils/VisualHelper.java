package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VisualHelper {

    private static final boolean ENABLE_DEMO_MODE = Boolean.parseBoolean(ConfigReader.get("demo"));; // puedes desactivarlo en producción

    public static void pause(long millis) {
        if (ENABLE_DEMO_MODE) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void highlight(WebDriver driver, WebElement element) {
        if (ENABLE_DEMO_MODE) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='3px solid #FF5733'; arguments[0].style.transition='border 0.3s ease-in-out';", element);
        }
    }
}
