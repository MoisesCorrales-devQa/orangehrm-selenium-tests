package pageObjects.personnellnfoManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BasePage;

public abstract class PersonnelInfoBasePage extends BasePage {

    public PersonnelInfoBasePage(WebDriver driver) {
        super(driver);
    }


    //Selectors
    private By configButton = By.xpath("//span[contains(text(), 'Configuration')]");
    private By employeeListButton = By.xpath("//a[contains(text(), 'Employee List')]");
    private By addEmployeeButton = By.xpath("//a[contains(text(), 'Add Employee')]");
    private By reportsButton = By.xpath("//a[contains(text(), 'Reports')]");
    private By optionalFields = By.xpath("//ul[contains(@class,'oxd-dropdown-menu')]//a[text()='Optional Fields']");
    private By customFields = By.xpath("//ul[contains(@class,'oxd-dropdown-menu')]//a[text()='Custom Fields']");

    public void clickConfiguration() {
        click(configButton);
    }

    public void goToOptionalFields() {
        click(optionalFields);
    }

    public void clickEmployeeListButton() {
        click(employeeListButton);
    }

}
