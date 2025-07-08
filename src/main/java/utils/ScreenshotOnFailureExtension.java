// ScreenshotOnFailureExtension.java (sin constructor)
package utils;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class ScreenshotOnFailureExtension implements AfterTestExecutionCallback {

    public static WebDriver driver;  // Campo estático para que puedas asignarlo en el test

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent() && driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
                System.out.println("[Allure] Screenshot on Failure taken");
            } catch (Exception e) {
                System.err.println("[Allure] Error taking screenshot: " + e.getMessage());
            }
        }
    }
}
