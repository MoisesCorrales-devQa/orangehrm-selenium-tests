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
    private By taxExemptionsMenu = By.xpath("//a[contains(@class,'orangehrm-tabs-item') and text()='Tax Exemptions']");
    private By contactDetailsMenu = By.xpath("//a[contains(@class,'orangehrm-tabs-item') and text()='Contact Details']");
    private By dropDownSelector = By.cssSelector(".oxd-select-text.oxd-select-text--active");
    private By labelSelector = By.cssSelector("label.oxd-label");
    private By inputSelector = By.cssSelector("input.oxd-input");
    private By checkboxSelector = By.cssSelector(".oxd-checkbox-wrapper");
    private By pageTitle = By.cssSelector(".oxd-text.oxd-text--h6.orangehrm-main-title");

    //Strings
    private String baseGroup = "//label[normalize-space()='%s']/ancestor::div[contains(@class, 'oxd-input-group')]";

    public EmployeeDetailPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkTaxExemptionsMenuIsVisible() {
        return isVisible(taxExemptionsMenu, 10);
    }

    public void goToContactDetailsMenu(){
        click(contactDetailsMenu);
    }

    public boolean checkTaxExemptionsMenuIsHidden() {
        return checkInvisibilityAfterLoading(pageTitle, taxExemptionsMenu, 10, 10);
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
        return checkInvisibilityAfterLoading(pageTitle, nicknameGroup, 10, 3);
    }

    public boolean checkMilitaryServiceGroupIsHidden() {
        return checkInvisibilityAfterLoading(pageTitle, militaryServiceGroup, 10, 3);
    }

    public boolean checkSmokerGroupIsHidden() {
        return checkInvisibilityAfterLoading(pageTitle, smokerGroup, 10, 3);
    }

    public boolean checkSSNGroupIsHidden() {
        return checkInvisibilityAfterLoading(pageTitle, ssnGroup, 10, 3);
    }

    public boolean checkSINGroupIsHidden() {
        return checkInvisibilityAfterLoading(pageTitle, sinGroup, 10, 3);
    }

    public boolean checkCustomInputGroupIsVisible(String customFieldName){
        By optionSelector = By.xpath(String.format(baseGroup, customFieldName));
        return checkDataFieldsAreVisible(optionSelector, inputSelector);
    }

    public boolean checkCustomGroupIsHidden(String customFieldName) {
        By optionSelector = By.xpath(String.format(baseGroup, customFieldName));
        return checkInvisibilityAfterLoading(pageTitle, optionSelector, 10, 5);
    }

    public boolean checkCustomDropDownGroupIsVisible(String customFieldName){
        By optionSelector = By.xpath(String.format(baseGroup, customFieldName));
        return checkDataFieldsAreVisible(optionSelector, dropDownSelector);
    }

    private boolean checkInvisibilityAfterLoading(By visibleSelector, By targetSelector, int visibleTimeout, int invisibleTimeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(visibleTimeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(visibleSelector));
        } catch (Exception e) {

        }
        return waitForInvisibility(targetSelector, invisibleTimeout);
    }
}