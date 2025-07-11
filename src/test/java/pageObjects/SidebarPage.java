package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BasePage;

public class SidebarPage extends BasePage {

    public SidebarPage(WebDriver driver) {
        super(driver);
    }

    //Selectors
    By pimMenu = By.xpath("//a[@href='/web/index.php/pim/viewPimModule']");
    By userIcon = By.cssSelector(".oxd-userdropdown-img");
    By logoutSelector = By.xpath("//ul[contains(@class,'oxd-dropdown-menu')]//a[text()='Logout']");

    public void goToPIM() {
        click(pimMenu);
    }

    public void clickUserIcon() {
        click(userIcon);
    }

    public void clickLogout() {
        click(logoutSelector);
    }
}
