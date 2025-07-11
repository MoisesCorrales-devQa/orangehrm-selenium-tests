package pageObjects.personnellnfoManagement.employeeList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.personnellnfoManagement.PersonnelInfoBasePage;

public class EmployeeListPage extends PersonnelInfoBasePage {

    //Selectors
    private By employeeSelector = By.xpath("//div[text()='Jose Vazquez']");

    public EmployeeListPage(WebDriver driver) {
        super(driver);
    }

    public void clickEmployee(){
        click(employeeSelector);
    }

}



