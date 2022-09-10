package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CommonMethods_demoWebShop extends BaseClass{
    public CommonMethods_demoWebShop(WebDriver driver) {
        super(driver);
    }

    /**
     * This method is used to login into DemoWebShop
     * @param email
     * @param password
     * @author anitha
     */
    public static void login_DemoWebShop(String email,String password){
        //DemoWebShop login
        getDriver().findElement(By.xpath("//a[text()='Log in']")).click();
        System.out.println("Click action is performed on the Login button");
        getDriver().findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
        System.out.println( email+" is entered as Email");
        getDriver().findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
        System.out.println(password+" is entered as password");
        getDriver().findElement(By.xpath("//input[@value='Log in']")).click();
        System.out.println("Click action is performed on Login Button");
    }

    /**
     * This method is used to logout from DemoWebShop
     * @author anitha
     */
    public static void logout_DemoWebShop(){
        //DemoWebShop logout
        getDriver().findElement(By.xpath("//a[text()='Log out']")).click();
        System.out.println("Click action is performed on Logout");
    }
}
