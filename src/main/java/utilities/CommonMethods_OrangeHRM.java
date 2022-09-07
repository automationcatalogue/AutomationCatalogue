package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonMethods_OrangeHRM extends BaseClass{
    public CommonMethods_OrangeHRM(WebDriver driver) {
        super(driver);
    }

    /**
     * This method is used to Login into OrangeHRM
     * @param userName
     * @param password
     * @author sagar
     */
    public static void login_OrangeHRM(String userName, String password){
        //OrangeHRM Login
        getDriver().findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(userName);
        System.out.println("Admin is entered as username");
        getDriver().findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
        System.out.println("Admin@123 is Entered as password");
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("Login button is clicked");
    }

    /**
     * This method is used to Logout from OrangeHRM
     * @author sagar
     */
    public static void logout_orangeHRM(){
        //OrangeHRM Logout
        getDriver().findElement(By.xpath("//span[text()='Log Out']")).click();
        System.out.println("Logged out from the OrangeHRM application");
    }


}
