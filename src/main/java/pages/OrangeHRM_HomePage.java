package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.BaseClass;

public class OrangeHRM_HomePage extends BaseClass {
    WebDriver driver;

    @FindBy(xpath = "//i[@class='material-icons'][text()='oxd_home_menu']")
    WebElement element_loginSuccess;

    @FindBy(xpath = "(//span[text()='HR Administration'])[1]")
    WebElement element_HRAdministration;

    public OrangeHRM_HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void verifyLogin() throws Exception{
        boolean isLoginSuccessful= element_loginSuccess.isDisplayed();
        if(isLoginSuccessful){
            System.out.println("Login is successful");
        }else{
            System.out.println("Login is not successful");
            throw new Exception();
        }
    }

    public void clickHRAdministration(){
        element_HRAdministration.click();
        System.out.println("HR Administration link is clicked");
    }
}
