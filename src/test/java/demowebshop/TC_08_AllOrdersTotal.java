package demowebshop;
import individual.miscellaneous.selenium_IndividualScripts.javaPrograms.Demo1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DemoWebShop_LoginPage;
import pages.DemoWebShop_OrdersPage;
import utilities.BaseClass;
import utilities.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TC_08_AllOrdersTotal {
    static WebDriver driver;
    public static String sExcelPath;
    public static int iRowNumber;
    public static String screenshotPath;
    public static String testcaseName;
    private DemoWebShop_LoginPage demoWebShop_loginPage;
    private DemoWebShop_OrdersPage demoWebShop_ordersPage;

    @Parameters("testId")
    @BeforeClass
    public void beforeAllOrdersTotal(@Optional(Constant.testId) String testId) throws Exception {
        testcaseName=Thread.currentThread().getStackTrace()[1].getClassName().substring(Thread.currentThread().getStackTrace()[1].getClassName().indexOf('.')+1);
        System.out.println("TestId for the AllOrdersTotal testcase is :" + testId);
        String path = System.getProperty("user.dir");
        System.out.println("Project Path is :" + path);
        screenshotPath = Utils.createScreenshotsFolder(path);
        String yamlPath = path + "\\src\\main\\resources\\Config.yaml";

        String browserName = YamlUtils.getYamlData(yamlPath, "browser");
        driver = Utils.launchBrowser(browserName);
        demoWebShop_loginPage=new DemoWebShop_LoginPage(driver);
        demoWebShop_loginPage= PageFactory.initElements(driver,DemoWebShop_LoginPage.class);
        demoWebShop_ordersPage=new DemoWebShop_OrdersPage(driver);
        demoWebShop_ordersPage=PageFactory.initElements(driver,DemoWebShop_OrdersPage.class);
        new BaseClass(driver);

        String url = YamlUtils.getYamlData(yamlPath, "demoWebShopURL");
        DriverUtils.loadURL(url);

        sExcelPath = path+"\\src\\main\\resources\\TestData.xlsx";
        ExcelUtils.setExcelFile(path+"\\src\\main\\resources\\TestData.xlsx");
        iRowNumber = ExcelUtils.getRowNumber(testId, "AllOrdersTotal");
    }
    @Test
    public void allOrdersTotal() throws Exception{
        //DemoWebShop Login

        demoWebShop_loginPage.login_DemoWebShop(iRowNumber);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_DemoWebShopLogin");
        System.out.println("DemoWebShop Login Screenshot is captured for "+testcaseName);

        //OrderPage
        demoWebShop_ordersPage.ordersPage();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_ordersPage");
        //AllOrdersTotal
        demoWebShop_ordersPage.allOrdersTotal();
        //DayWiseTotal
        demoWebShop_ordersPage.dayWiseOrdersTotal();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_OrdersDayWise");

        demoWebShop_loginPage.logout_DemoWebShop();

        DriverUtils.closeBrowser();

    }
}
