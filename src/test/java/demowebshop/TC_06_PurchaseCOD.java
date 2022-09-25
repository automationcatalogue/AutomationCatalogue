package demowebshop;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import pages.*;
import utilities.BaseClass;
import utilities.*;

public class TC_06_PurchaseCOD {
    static WebDriver driver;
    public static String sExcelPath;
    public static int iRowNumber;
    public static String screenshotPath;
    public static String testcaseName;
    private DemoWebShop_LoginPage demoWebShop_loginPage;
    private DemoWebShop_HomePage demoWebShop_homePage;
    private DemoWebShop_ProductCataloguePage demoWebShop_productCataloguePage;
    private DemoWebShop_ProductDisplayPage demoWebShop_productDisplayPage;
    private DemoWebShop_ShoppingCartPage demoWebShop_shoppingCartPage;
    private DemoWebShop_CheckoutPage demoWebShop_checkoutPage;

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

        demoWebShop_productCataloguePage = new DemoWebShop_ProductCataloguePage(driver);
        demoWebShop_productCataloguePage = PageFactory.initElements(driver, DemoWebShop_ProductCataloguePage.class);

        demoWebShop_productDisplayPage = new DemoWebShop_ProductDisplayPage(driver);
        demoWebShop_productDisplayPage = PageFactory.initElements(driver, DemoWebShop_ProductDisplayPage.class);

        demoWebShop_shoppingCartPage=new DemoWebShop_ShoppingCartPage(driver);
        demoWebShop_shoppingCartPage=PageFactory.initElements(driver, DemoWebShop_ShoppingCartPage.class);

        demoWebShop_checkoutPage=new DemoWebShop_CheckoutPage(driver);
        demoWebShop_checkoutPage=PageFactory.initElements(driver, DemoWebShop_CheckoutPage.class);

        new BaseClass(driver);
        String url = YamlUtils.getYamlData(yamlPath,"demoWebShopURL");
        DriverUtils.loadURL(url);

        sExcelPath = path+"\\src\\main\\resources\\TestData.xlsx";
        ExcelUtils.setExcelFile(path+"\\src\\main\\resources\\TestData.xlsx");
        iRowNumber = ExcelUtils.getRowNumber(testId, "PurchaseCOD");
    }
    @Test
    public void purchaseCOD() throws Exception {

        demoWebShop_homePage.clickLoginLink();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_1_DemoWebShop_HomePage");

        demoWebShop_loginPage.login_DemoWebShop(iRowNumber);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_2_DemoWebShop_HomePage");

        demoWebShop_homePage.clickProductsLink();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_3_DemoWebShop_ProductPage");

        demoWebShop_productCataloguePage.selectProduct();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_4_DemoWebShop_ProductCataloguePage");

        demoWebShop_productDisplayPage.clickAddToCart(screenshotPath, testcaseName);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_5_DemoWebShop_ProductDisplayPage");

        demoWebShop_shoppingCartPage.clickCheckout();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_6_DemoWebShop_ShoppingCartPage");

        demoWebShop_checkoutPage.placeOrder(iRowNumber,"PurchaseCOD", sExcelPath);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_7_DemoWebShop_OrderConfirmationPage");

        demoWebShop_loginPage.logout_DemoWebShop();
        DriverUtils.closeBrowser();

    }

    @AfterClass
    public void tearDown(){
        DriverUtils.closeBrowser();
    }
}
