package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utilities.BaseClass;
import utilities.Constant;
import utilities.ExcelUtils;

public class OrangeHRM_LoginPage extends BaseClass {
    WebDriver driver;

    public OrangeHRM_LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id='txtUsername']")
    WebElement element_UserName;

    @FindBy(how = How.XPATH, using="//input[@id='txtPassword']")
    WebElement element_Password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement element_login;

    @FindBy(xpath = "//span[text()='Log Out']")
    WebElement element_logout;


    /**
     * This method is used to Login into OrangeHRM
     * @param iRowNumber
     * @author sagar
     */
    public void login_OrangeHRM(int iRowNumber) throws Exception{
        String sUserName = ExcelUtils.getCellData(iRowNumber, Constant.sUserName_OrangeHRM,"AddEmployee");
        System.out.println("UserName from the Excel Sheet is :"+sUserName);
        String sPassword = ExcelUtils.getCellData(iRowNumber, Constant.sUserPassword_OrangeHRM,"AddEmployee");
        System.out.println("Password from the Excel Sheet is :"+sPassword);

        element_UserName.sendKeys(sUserName);
        System.out.println("Admin is entered as username");
        element_Password.sendKeys(sPassword);
        System.out.println("Admin@123 is Entered as password");
        element_login.click();
        System.out.println("Login button is clicked");
    }

    /**
     * This method is used to Logout from OrangeHRM
     * @author sagar
     */
    public void logout_orangeHRM(){
        //OrangeHRM Logout
        element_logout.click();
        System.out.println("Logged out from the OrangeHRM application");
    }

}
