package pageObjects.personnellnfoManagement.configuration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.personnellnfoManagement.PersonnelInfoBasePage;

import java.time.Duration;

public class OptionalFieldsPage extends PersonnelInfoBasePage {

    //Selectors
    By deprecatedFieldParentSelector = By.xpath("//div[contains(@class, 'orangehrm-optional-field-row')][.//p[contains(., 'Show Nick Name')]]");
    By ssnFieldParentSelector = By.xpath("//div[contains(@class, 'orangehrm-optional-field-row')][.//p[contains(., 'Show SSN field')]]");
    By sinFieldParentSelector = By.xpath("//div[contains(@class, 'orangehrm-optional-field-row')][.//p[contains(., 'Show SIN field in Personal Details')]]");
    By usTaxExemptionsFieldParentSelector = By.xpath("//div[contains(@class, 'orangehrm-optional-field-row')][.//p[contains(., 'Show US Tax Exemptions menu')]]");

    By deprecatedCheckboxParentselector = By.xpath("//p[contains(., 'Show Nick Name')]");
    By ssnCheckboxParentselector = By.xpath("//p[contains(., 'Show SSN field')]");
    By sinCheckboxParentSelector = By.xpath("//p[contains(., 'Show SIN field in Personal Details')]");
    By usTaxExemptionsCheckboxParentSelector = By.xpath("//p[contains(., 'Show US Tax Exemptions menu')]");

    By checkboxSelector = By.xpath("./following-sibling::div//input");
    By switchLabel = By.cssSelector(".oxd-switch-wrapper label");
    By saveButton = By.xpath("//button[normalize-space()='Save']");

    public OptionalFieldsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDeprecatedSwitchChecked() {
        return isSwitchChecked(deprecatedCheckboxParentselector);
    }

    public boolean isSSNSwitchChecked() {
        return isSwitchChecked(ssnCheckboxParentselector);
    }

    public boolean isSINSwitchChecked() {
        return isSwitchChecked(sinCheckboxParentSelector);
    }

    public boolean isUSTaxExemptionsSwitchChecked() {
        return isSwitchChecked(usTaxExemptionsCheckboxParentSelector);
    }

    public void turnOnDeprecatedSwitch() {
        if (!isDeprecatedSwitchChecked()) {
            clickSwitch(deprecatedFieldParentSelector);
        }
    }

    public void turnOffDeprecatedSwitch() {
        if (isDeprecatedSwitchChecked()) {
            clickSwitch(deprecatedFieldParentSelector);
        }
    }

    public void turnOnSSNSwitch() {
        if (!isSSNSwitchChecked()) {
            clickSwitch(ssnFieldParentSelector);
        }
    }

    public void turnOffSSNSwitch() {
        if (isSSNSwitchChecked()) {
            clickSwitch(ssnFieldParentSelector);
        }
    }

    public void turnOnSINSwitch() {
        if (!isSINSwitchChecked()) {
            clickSwitch(sinFieldParentSelector);
        }
    }

    public void turnOffSINSwitch() {
        if (isSINSwitchChecked()) {
            clickSwitch(sinFieldParentSelector);
        }
    }

    public void turnOnUSTaxExemptionsSwitch() {
        if (!isUSTaxExemptionsSwitchChecked()) {
            clickSwitch(usTaxExemptionsFieldParentSelector);
        }
    }

    public void turnOffUSTaxExemptionsSwitch() {
        if (isUSTaxExemptionsSwitchChecked()) {
            clickSwitch(usTaxExemptionsFieldParentSelector);
        }
    }

    public void clickSaveButton() {
        click(saveButton);
    }

    // --- GENERIC METHODS ---

    public boolean isSwitchChecked(By parentSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement parent = wait.until(ExpectedConditions.visibilityOfElementLocated(parentSelector));
        WebElement input = parent.findElement(checkboxSelector);
        return input.isSelected();
    }

    public void clickSwitch(By parentSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement parent = wait.until(ExpectedConditions.visibilityOfElementLocated(parentSelector));
        clickInsideParent(parent, switchLabel);
    }
}