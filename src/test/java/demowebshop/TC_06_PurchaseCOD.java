package demowebshop;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DemoWebShop_CartPage;
import pages.DemoWebShop_HomePage;
import pages.DemoWebShop_LoginPage;
import pages.DemoWebShop_PaymentPage;
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
    private DemoWebShop_LoginPage demoWebShop_loginPage;
    private DemoWebShop_HomePage demoWebShop_homePage;
    private DemoWebShop_CartPage demoWebShop_cartPage;
    private DemoWebShop_PaymentPage demoWebShop_paymentPage;

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

        demoWebShop_loginPage=new DemoWebShop_LoginPage(driver);
        demoWebShop_loginPage= PageFactory.initElements(driver,DemoWebShop_LoginPage.class);
        demoWebShop_homePage=new DemoWebShop_HomePage(driver);
        demoWebShop_homePage=PageFactory.initElements(driver,DemoWebShop_HomePage.class);
        demoWebShop_cartPage=new DemoWebShop_CartPage(driver);
        demoWebShop_cartPage=PageFactory.initElements(driver,DemoWebShop_CartPage.class);
        demoWebShop_paymentPage=new DemoWebShop_PaymentPage(driver);
        demoWebShop_paymentPage=PageFactory.initElements(driver,DemoWebShop_PaymentPage.class);

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
        demoWebShop_loginPage.login_DemoWebShop(iRowNumber);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_1_DemoWebShopLogin");

        //Selecting Blue Jeans and Add to Cart
        demoWebShop_homePage.addToCart_DemoWebShop(screenshotPath,testcaseName);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_2_shoppingCart");

        //Cart page
        demoWebShop_cartPage.cartPage_checkout();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_3_Checkout");

        //payment page
        demoWebShop_paymentPage.orders_payments();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_4_Payment");

        demoWebShop_loginPage.logout_DemoWebShop();
        DriverUtils.closeBrowser();

    }
}
