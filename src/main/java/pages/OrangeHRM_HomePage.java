package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BaseClass;

public class OrangeHRM_HomePage extends BaseClass {
    WebDriver driver;

    public OrangeHRM_HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void verifyLogin() throws Exception{
        boolean isLoginSuccessful= driver.findElement(By.xpath("//i[@class='material-icons'][text()='oxd_home_menu']")).isDisplayed();
        if(isLoginSuccessful){
            System.out.println("Login is successful");
        }else{
            System.out.println("Login is not successful");
            throw new Exception();
        }
    }
}
