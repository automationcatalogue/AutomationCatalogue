package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BaseClass;
import utilities.Constant;
import utilities.ExcelUtils;

public class OrangeHRM_LoginPage extends BaseClass {
    WebDriver driver;

    public OrangeHRM_LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

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

        getDriver().findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(sUserName);
        System.out.println("Admin is entered as username");
        getDriver().findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(sPassword);
        System.out.println("Admin@123 is Entered as password");
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("Login button is clicked");
    }

    /**
     * This method is used to Logout from OrangeHRM
     * @author sagar
     */
    public void logout_orangeHRM(){
        //OrangeHRM Logout
        getDriver().findElement(By.xpath("//span[text()='Log Out']")).click();
        System.out.println("Logged out from the OrangeHRM application");
    }

}
