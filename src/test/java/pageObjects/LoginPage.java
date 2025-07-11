package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;
import utils.VisualHelper;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Selectors
    private By usernameInput = By.name("username");
    private By passwordInput = By.name("password");
    private By loginButton = By.className("orangehrm-login-button");

    private By loginTitle = By.cssSelector(".oxd-text.oxd-text--h5.orangehrm-login-title");
    private By errorMessage = By.cssSelector(".oxd-text.oxd-text--p.oxd-alert-content-text");

    private final By groups = By.cssSelector(".oxd-input-group.oxd-input-field-bottom-space");
    private final By userLabel = By.cssSelector(".oxd-label");
    private final By requiredLabel = By.cssSelector(".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message");

    //Error Messages
    private String INVALID_CREDENTIALS = "Invalid credentials";
    private String LOCKED_USER = "Account disabled";

    Duration timeout = Duration.ofSeconds(5);

    public void loginAs(String username, String password) {
        sendKeys(usernameInput, username, 10);

        sendKeys(passwordInput, password, 10);

        click(loginButton);

        VisualHelper.pause(1000); // espera tras login
    }

    public boolean isInvalidCredentialsErrorVisible() {
        return isVisibleWithText(errorMessage, INVALID_CREDENTIALS, 10);
    }

    public boolean isLockedUserErrorVisible() {
        return isVisibleWithText(errorMessage, LOCKED_USER, 10);
    }

    public boolean isNoUserErrorVisible() {
        return isRequiredLabelVisible("Username");
    }

    public boolean isNoPsswdErrorVisible() {
        return isRequiredLabelVisible("Password");
    }

    public boolean isRequiredLabelVisible(String parent) {
        List<WebElement> groupsComponents = new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(groups));
        for (WebElement group : groupsComponents) {
            WebElement label = group.findElement(userLabel);
            if (label.getText().trim().equalsIgnoreCase(parent)) {
                try {
                    WebElement requiredLabelElement = new WebDriverWait(driver, timeout)
                            .until(ExpectedConditions.visibilityOf(group.findElement(requiredLabel)));
                    return requiredLabelElement.getText().trim().equals("Required");
                } catch (TimeoutException e) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean areBothRequiredLabelsVisible() {
        List<WebElement> groupsComponents = new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(groups));

        if (groupsComponents.size() < 2) return false;
        for (int i = 0; i < 2; i++) {
            try {
                WebElement requiredLabelElement = new WebDriverWait(driver, timeout)
                        .until(ExpectedConditions.visibilityOf(
                                groupsComponents.get(i).findElement(requiredLabel)
                        ));
                if (!requiredLabelElement.getText().trim().equals("Required")) {
                    return false;
                }
            } catch (TimeoutException | NoSuchElementException e) {
                return false;
            }
        }
        return true;
    }

    public boolean checkLoginPageIsDisplayed() {
        String loginUrl = "login";
        String loginTitleText = "Login";

        return pageIsDisplayed(loginUrl, loginTitle, loginTitleText);
    }


    public void refresh() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginTitle));
        driver.navigate().refresh();
    }
}

