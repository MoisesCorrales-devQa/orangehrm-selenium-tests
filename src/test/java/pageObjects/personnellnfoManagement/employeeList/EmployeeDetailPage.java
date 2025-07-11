package pageObjects.personnellnfoManagement.employeeList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;

import java.time.Duration;

public class EmployeeDetailPage extends BasePage {

    //Selectors
    private By nicknameGroup = By.xpath("//label[normalize-space()='Nickname']/ancestor::div[contains(@class, 'oxd-input-group')]");
    private By militaryServiceGroup = By.xpath("//label[normalize-space()='Military Service']/ancestor::div[contains(@class, 'oxd-input-group')]");
    private By smokerGroup = By.xpath("//label[normalize-space()='Smoker']/ancestor::div[contains(@class, 'oxd-input-group')]");
    private By ssnGroup = By.xpath("//label[normalize-space()='SSN Number']/ancestor::div[contains(@class, 'oxd-input-group')]");
    private By sinGroup = By.xpath("//label[normalize-space()='SIN Number']/ancestor::div[contains(@class, 'oxd-input-group')]");
    private By taxExemtionsMenu = By.xpath("//a[contains(@class,'orangehrm-tabs-item') and text()='Tax Exemptions']");


    private By labelSelector = By.cssSelector("label.oxd-label");
    private By inputSelector = (By.cssSelector("input.oxd-input"));
    private By checkboxSelector = (By.cssSelector(".oxd-checkbox-wrapper"));

    public EmployeeDetailPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkTaxExemtionsMenuIsVisible() {
        return isVisible(taxExemtionsMenu, 10);
    }

    public boolean checkTaxExemtionsMenuIsHidden() {
        return waitForInvisibility(taxExemtionsMenu, 10);
    }

    public boolean checkNicknameGroupIsVisible() {
        return checkDataFieldsAreVisible(nicknameGroup, inputSelector);
    }

    public boolean checkMilitaryServiceGroupIsVisible() { return checkDataFieldsAreVisible(militaryServiceGroup, inputSelector); }

    public boolean checkSmokerGroupIsVisible() {
        return checkDataFieldsAreVisible(smokerGroup, checkboxSelector);
    }

    public boolean checkSSNGroupIsVisible() { return checkDataFieldsAreVisible(ssnGroup, inputSelector); }

    public boolean checkSINGroupIsVisible() { return checkDataFieldsAreVisible(sinGroup, inputSelector); }

    public boolean checkDataFieldsAreVisible(By groupSelector, By fieldSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement parentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(groupSelector));

        boolean labelIsVisible = isVisibleInsideParent(parentElement, labelSelector, 10);
        boolean inputSelectorIsVisible = isVisibleInsideParent(parentElement, fieldSelector, 10);

        return labelIsVisible && inputSelectorIsVisible;
    }

    public boolean checkNicknameGroupIsHidden() {
        return waitForInvisibility(nicknameGroup, 3);
    }

    public boolean checkMilitaryServiceGroupIsHidden() {
        return waitForInvisibility(smokerGroup, 3);
    }

    public boolean checkSmokerGroupIsHidden() {
        return waitForInvisibility(smokerGroup, 3);
    }

    public boolean checkSSNGroupIsHidden() { return waitForInvisibility(ssnGroup, 3); }

    public boolean checkSINGroupIsHidden() { return waitForInvisibility(sinGroup, 3); }



}
