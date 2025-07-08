package utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentWriter {

    public static void write(WebDriver driver) {
        Properties props = new Properties();

        try {
            Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();

            props.setProperty("Browser", caps.getBrowserName());
            props.setProperty("Browser.Version", caps.getBrowserVersion());
        } catch (Exception e) {
            props.setProperty("Browser", "Unknown");
            props.setProperty("Browser.Version", "Unknown");
        }

        props.setProperty("Environment", System.getenv("TEST_ENV") != null
                ? System.getenv("TEST_ENV")
                : "Local");

        props.setProperty("OS", System.getProperty("os.name"));
        props.setProperty("Java.Version", System.getProperty("java.version"));
        props.setProperty("User", System.getProperty("user.name"));

        File resultsDir = new File("target/allure-results");
        if (!resultsDir.exists()) {
            resultsDir.mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(
                new File(resultsDir, "environment.properties"))) {
            props.store(fos, "Allure Environment Info");
            System.out.println("[Allure] environment.properties generado.");
        } catch (IOException e) {
            System.err.println("[Allure] Error al generar environment.properties:");
            e.printStackTrace();
        }
    }
}
