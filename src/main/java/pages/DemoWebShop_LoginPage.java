package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseClass;
import utilities.Constant;
import utilities.ExcelUtils;

import java.time.Duration;

public class DemoWebShop_LoginPage extends BaseClass {
    WebDriver driver;
    public DemoWebShop_LoginPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }


    @FindBy(how = How.XPATH, using="//input[@id='Email']")
    static
    WebElement element_Email;

    @FindBy(how = How.XPATH, using="//input[@id='Password']")
    static
    WebElement element_Password;

    @FindBy(xpath = "//input[@value='Log in']")
    static
    WebElement element_submitLogin;

    @FindBy(xpath = "//a[text()='Log out']")
    static
    WebElement element_logout;

    /**
     * This method is used to login into DemoWebShop
     * @param iRowNumber
     * @author anitha
     */
    public void login_DemoWebShop(int iRowNumber) throws Exception {
        //DemoWebShop login
        String sUserName = ExcelUtils.getCellData(iRowNumber, Constant.sDemoWebShop_LoginEmail,"PurchaseCOD");
        System.out.println("UserName from the Excel Sheet is :"+sUserName);
        String sPassword = ExcelUtils.getCellData(iRowNumber, Constant.sDemoWebShop_Password,"PurchaseCOD");
        System.out.println("Password from the Excel Sheet is :"+sPassword);

        element_Email.sendKeys(sUserName);
        System.out.println( sUserName+" is entered as Email");

        element_Password.sendKeys(sPassword);
        System.out.println(sPassword+" is entered as password");

        element_submitLogin.click();
        System.out.println("Click action is performed on Login Button");
    }

    /**
     * This method is used to logout from DemoWebShop
     * @author anitha
     */
    public static void logout_DemoWebShop(){
        //DemoWebShop logout
        //getDriver().findElement(By.xpath()).click();
        element_logout.click();
        System.out.println("Click action is performed on Logout");
    }
}

