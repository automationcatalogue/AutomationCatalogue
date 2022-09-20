package demowebshop;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.BaseClass;
import utilities.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;

public class TC_06_PurchaseCOD {
    static WebDriver driver;
    public static String sExcelPath;
    public static int iRowNumber;
    public static String screenshotPath;
    public static String testcaseName;

    @Parameters("testId")
    @BeforeClass
    public void beforePurchaseCOD(@Optional(Constant.testId) String testId) throws Exception{
        testcaseName=Thread.currentThread().getStackTrace()[1].getClassName().substring(Thread.currentThread().getStackTrace()[1].getClassName().indexOf('.')+1);
        System.out.println("TestId for the PurchaseCOD testcase is :"+testId);
        String path=System.getProperty("user.dir");
        System.out.println("Project Path is :"+path);
        screenshotPath = Utils.createScreenshotsFolder(path);
        String yamlPath=path+"\\src\\main\\resources\\Config.yaml";

        String browserName= YamlUtils.getYamlData(yamlPath,"browser");
        driver= Utils.launchBrowser(browserName);

        new BaseClass(driver);
        String url = YamlUtils.getYamlData(yamlPath,"demoWebShopURL");
        DriverUtils.loadURL(url);

        sExcelPath = path+"\\src\\main\\resources\\TestData.xlsx";
        ExcelUtils.setExcelFile(path+"\\src\\main\\resources\\TestData.xlsx");
        iRowNumber = ExcelUtils.getRowNumber(testId, "PurchaseCOD");
    }
    @Test
    public void purchaseCOD() throws Exception {

        //Demo WebShop Login
        String sUserName = ExcelUtils.getCellData(iRowNumber, Constant.sDemoWebShop_LoginEmail,"PurchaseCOD");
        System.out.println("UserName from the Excel Sheet is :"+sUserName);
        String sPassword = ExcelUtils.getCellData(iRowNumber, Constant.sDemoWebShop_Password,"PurchaseCOD");
        System.out.println("Password from the Excel Sheet is :"+sPassword);
        CommonMethods_demoWebShop.login_DemoWebShop(sUserName,sPassword);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_DemoWebShopLogin");
        System.out.println("DemoWebShop Screenshot is captured");


        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));

        //Selecting Blue Jeans and Add to Cart
        driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys("Blue Jeans");
        System.out.println("Blue Jeans is Entered as Search data");
        driver.findElement(By.xpath("(//input[@value='Search'])[1]")).click();
        System.out.println("Click action is performed on Search button");
        driver.findElement(By.xpath("//input[@value='Add to cart']")).click();
        System.out.println("Click action is performed on Add to Cart");
        Utils.captureScreenshot(screenshotPath,testcaseName+"_AddToCart");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Shopping cart']")));
        WebElement element_CartBtn=driver.findElement(By.xpath("//span[text()='Shopping cart']"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",element_CartBtn);
        System.out.println("Click action is performed on the shopping cart ");
        Utils.captureScreenshot(screenshotPath,testcaseName+"_shoppingCart");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='termsofservice']")));
        driver.findElement(By.xpath("//input[@name='termsofservice']")).click();
        System.out.println("Click action performed on Terms of Service");
        driver.findElement(By.xpath("//button[@name='checkout']")).click();
        System.out.println("Click action performed on Checkout Button");
        driver.findElement(By.xpath("(//input[@title='Continue'])[1]")).click();
        System.out.println("Click action performed on Continue Button for billing address");
        driver.findElement(By.xpath("(//input[@title='Continue'])[2]")).click();
        System.out.println("Click action performed on Continue Button for shipping address");
        driver.findElement(By.xpath("//input[@onclick='ShippingMethod.save()']")).click();
        System.out.println("Click action performed on Continue Button for shipping method");
        driver.findElement(By.xpath("//input[@id='paymentmethod_0']")).click();
        System.out.println("Click action is performed on Cash On Delivery Radio Button");
        driver.findElement(By.xpath("//input[@onclick='PaymentMethod.save()']")).click();
        System.out.println("Click action is performed on Continue for payment method");
        driver.findElement(By.xpath("//input[@class='button-1 payment-info-next-step-button']")).click();
        System.out.println("Click action is performed on Continue for Payment Information");
        Utils.captureScreenshot(screenshotPath,testcaseName+"_Payment");

        List<WebElement> cartElements=driver.findElements(By.xpath ("//div[@class='cart-footer']/div[2]/div/table/tbody/tr/td[@class='cart-total-left']"));
        String shippingVal="Shipping: (Ground)",TotalVal="Total:";
        for(WebElement cartEle:cartElements){
            if(cartEle.getText()==shippingVal){
                String ShippingPrice=driver.findElement(By.xpath("//div[@class='cart-footer']/div/div/table/tbody/tr[2]/td[2]")).getText();
                System.out.println("Shipping cost is :"+ ShippingPrice);
            }
            if(cartEle.getText()==TotalVal){
                String TotalPrice=driver.findElement(By.xpath("//div[@class='cart-footer']/div/div/table/tbody/tr[5]/td[2]")).getText();
                System.out.println("Total price is :"+TotalPrice);
            }
        }

        driver.findElement(By.xpath("//input[@onclick='ConfirmOrder.save()']")).click();
        System.out.println("Click action is performed on Confirm Button for Confirm Order");

        String orderNumber=driver.findElement(By.xpath("//div[@class='section order-completed']/ul/li[1]")).getText();
        System.out.println(orderNumber);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_DemoWebShopOrder");

        CommonMethods_demoWebShop.logout_DemoWebShop();

        DriverUtils.closeBrowser();

    }
}
