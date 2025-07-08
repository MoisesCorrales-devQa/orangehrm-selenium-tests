package utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserInfo {
    public static String getBrowserVersion(WebDriver driver) {
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        return caps.getBrowserVersion();
    }

    public static String getBrowserName(WebDriver driver) {
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        return caps.getBrowserName();
    }
}
