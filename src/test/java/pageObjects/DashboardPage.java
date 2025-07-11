package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;

import java.time.Duration;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    //Selectors
    private By dashboardHeader = By.cssSelector(".oxd-topbar-header-breadcrumb-module");

    public boolean checkDashboardPageIsDisplayed() {
        String dashboardUrl = "dashboard";
        String dashboardTitle = "Dashboard";

        return pageIsDisplayed(dashboardUrl, dashboardHeader, dashboardTitle);
    }

    public void navigateBack(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
        driver.navigate().back();
    }

    public void refresh() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
        driver.navigate().refresh();
    }
}
