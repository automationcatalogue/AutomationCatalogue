package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseClass;

import java.time.Duration;

public class OrangeHRM_UsersPage extends BaseClass {

    WebDriver driver;
    public OrangeHRM_UsersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public By locator_firstRowData = By.xpath("//div[@id='systemUserDiv']//table//tbody//tr[1]");

    @FindBy(xpath = "//i[text()='add']")
    WebElement element_AddUser;

    public void clickAddUser(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator_firstRowData));

        element_AddUser.click();
        System.out.println(" + Add user icon clicked ");
    }
}
