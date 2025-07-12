package pageObjects.personnellnfoManagement.configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.personnellnfoManagement.PersonnelInfoBasePage;
import utils.TestProperties;
import utils.VisualHelper;

import java.time.Duration;

public class CustomFieldsPage extends PersonnelInfoBasePage {

    public CustomFieldsPage(WebDriver driver) {
        super(driver);
    }

    //Selectors
    private By fieldNameGroup = By.xpath("//label[normalize-space()='Field Name']/ancestor::div[contains(@class, 'oxd-input-group')]");
    private By typeGroup = By.xpath("//label[normalize-space()='Type']/ancestor::div[contains(@class, 'oxd-input-group')]");
    private By screenGroup = By.xpath("//label[normalize-space()='Screen']/ancestor::div[contains(@class, 'oxd-input-group')]");
    private By fieldOptionsGroup = By.xpath("//label[normalize-space()='Select Options']/ancestor::div[contains(@class, 'oxd-input-group')]");

    private By inputSelector = (By.cssSelector("input.oxd-input"));
    private By dropDownSelector = (By.cssSelector(".oxd-select-text.oxd-select-text--active"));

    private By addButton = By.xpath("//button[contains(@class,'oxd-button') and contains(., 'Add')]");
    private By saveButton = By.xpath("//button[contains(@class,'oxd-button') and contains(., 'Save')]");
    private By trashIcon = By.cssSelector(".oxd-icon.bi-trash");
    private By editIcon = By.cssSelector(".oxd-icon.bi-pencil-fill");

    private By confirmButton = By.cssSelector("button.oxd-button--label-danger");
    private By customFieldsAmount = By.xpath("//span[contains(@class, 'oxd-text') and contains(., '10')]");
    private By customFieldsTitle = By.xpath("//h6[contains(@class, 'oxd-text') and contains(., 'Custom Fields')]");


    //Strings
    private String baseOption = "//div[@role='option' and .//span[text()='%s']]";
    private String baseCustomFieldGroup = "//div[contains(@class, 'oxd-table-row') and .//div[normalize-space(text()) = '%s']]";

    public void clickAdd() {
        click(addButton);
    }

    public void clickSave() {
        click(saveButton);
    }

    public void clickEdit(){
        click(editIcon);
    }

    public void fillFieldName(String fieldName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement parentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldNameGroup));

        sendKeysFromParent(parentElement, inputSelector, fieldName);
    }

    public void selectType(String type){
        By optionSelector = By.xpath(String.format(baseOption, type));
        selectFromDropdown(typeGroup, dropDownSelector, optionSelector);
    }

    public void selectScreen(String screen){
        By optionSelector = By.xpath(String.format(baseOption, screen));
        selectFromDropdown(screenGroup, dropDownSelector, optionSelector);
    }

    public void fillSelectOptions(String options){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement parentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldOptionsGroup));

        sendKeysFromParent(parentElement, inputSelector, options);
    }

    public void clickDeleteIcon(String fieldName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By selector = By.xpath(String.format(baseCustomFieldGroup, fieldName));
        WebElement parentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));

        clickInsideParent(parentElement, trashIcon);
    }

    public void clickEditIcon(String fieldName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By selector = By.xpath(String.format(baseCustomFieldGroup, fieldName));
        WebElement parentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));

        clickInsideParent(parentElement, editIcon);
    }

    public void confirmDelete(){
        click(confirmButton);
    }

    public boolean customFieldsLimitReached() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(customFieldsTitle));
        boolean limitReached = waitForInvisibility(addButton, 3);

        System.out.println(limitReached);
        return limitReached;
    }

    public boolean maxCapacityReached() {
       return isVisible(customFieldsAmount, 10);
    }
}
