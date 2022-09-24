package demowebshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DemoWebShop_LoginPage;
import pages.DemoWebShop_OrdersPage;
import pages.DemoWebShop_PaymentPage;
import utilities.BaseClass;
import utilities.*;

public class TC_07_ReOrder {
    static WebDriver driver;
    public static String sExcelPath;
    public static int iRowNumber;
    public static String screenshotPath;
    public static String testcaseName;
    private DemoWebShop_LoginPage demoWebShop_loginPage;
    private DemoWebShop_OrdersPage demoWebShop_ordersPage;
    private DemoWebShop_PaymentPage demoWebShop_paymentPage;

    @Parameters("testId")
    @Test
    public  void beforeReOrder(@Optional(Constant.testId) String testId) throws Exception {
        testcaseName=Thread.currentThread().getStackTrace()[1].getClassName().substring(Thread.currentThread().getStackTrace()[1].getClassName().indexOf('.')+1);
        System.out.println("TestId for the ReOrder testcase is :" + testId);
        String path = System.getProperty("user.dir");
        screenshotPath = Utils.createScreenshotsFolder(path);
        System.out.println("Project Path is :" + path);

        String yamlPath = path + "\\src\\main\\resources\\Config.yaml";

        String browserName = YamlUtils.getYamlData(yamlPath, "browser");
        driver = Utils.launchBrowser(browserName);
        demoWebShop_loginPage=new DemoWebShop_LoginPage(driver);
        demoWebShop_loginPage= PageFactory.initElements(driver,DemoWebShop_LoginPage.class);
        demoWebShop_ordersPage=new DemoWebShop_OrdersPage(driver);
        demoWebShop_ordersPage=PageFactory.initElements(driver,DemoWebShop_OrdersPage.class);
        demoWebShop_paymentPage=new DemoWebShop_PaymentPage(driver);
        demoWebShop_paymentPage=PageFactory.initElements(driver,DemoWebShop_PaymentPage.class);

        new BaseClass(driver);
        String url = YamlUtils.getYamlData(yamlPath, "demoWebShopURL");
        DriverUtils.loadURL(url);

        sExcelPath = path + "\\src\\main\\resources\\TestData.xlsx";
        ExcelUtils.setExcelFile(path + "\\src\\main\\resources\\TestData.xlsx");
        iRowNumber = ExcelUtils.getRowNumber(testId, "ReOrderCOD");
    }
    @Test
    public void reOrder() throws Exception{

        //DemoWebShop Login

        demoWebShop_loginPage.login_DemoWebShop(iRowNumber);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_1_DemoWebShopLogin");
        System.out.println("DemoWebShop Login Screenshot is captured for "+testcaseName);
        //Clicks on Orders and perform Reorder
        demoWebShop_ordersPage.ordersPage();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_2_allOrdersPage");
        demoWebShop_ordersPage.reOrder();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_3_CheckoutPage");
        //payment page
        demoWebShop_paymentPage.orders_payments();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_4_reOrder");
        demoWebShop_loginPage.logout_DemoWebShop();
        DriverUtils.closeBrowser();

    }
}
