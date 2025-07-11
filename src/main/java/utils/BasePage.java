package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        VisualHelper.highlight(driver, element);
        element.click();
        VisualHelper.pause(1000);
    }

    public void clickInsideParent(WebElement parent, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(driver -> {
            try {
                WebElement child = parent.findElement(locator);
                return (child.isDisplayed() && child.isEnabled()) ? child : null;
            } catch (Exception e) {
                return null;
            }
        });

        VisualHelper.highlight(driver, element);
        element.click();
        VisualHelper.pause(1000);
    }

    public boolean isVisible(By locator, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean isVisibleInsideParent(WebElement parent, By locator, int timeoutSeconds) {
        try {

            VisualHelper.highlight(driver, parent);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

            WebElement element = wait.until(ExpectedConditions.visibilityOf(
                    parent.findElement(locator)
            ));

            VisualHelper.highlight(driver, element);
            VisualHelper.pause(1000);

            System.out.println(element);
            System.out.println(element.isDisplayed());
            return element.isDisplayed();

        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean waitForInvisibility(By locator, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isVisibleWithText(By locator, String expectedText, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String actualText = element.getText();
            return element.isDisplayed() && actualText.contains(expectedText);
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void sendKeys(By locator, String text, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        VisualHelper.highlight(driver, element);
        element.clear();
        element.sendKeys(text);
        VisualHelper.pause(500);
    }

    public boolean checkUrl(String urlPartial) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.urlContains(urlPartial));
            return driver.getCurrentUrl().contains(urlPartial);
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean pageIsDisplayed(String urlPartial, By selector,String tittleText) {

        boolean isUrlValidated = checkUrl(urlPartial);
        boolean isTittleDisplayed = isVisibleWithText(selector, tittleText, 10);

        return isUrlValidated && isTittleDisplayed;
    }

    public void scrollBy(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }

    public void scrollByHorizontal(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(arguments[0], 0);", pixels);
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }


}