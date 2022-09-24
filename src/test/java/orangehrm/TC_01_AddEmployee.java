package orangehrm;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.OrangeHRM_AddEmployeePage;
import pages.OrangeHRM_EmployeeListPage;
import pages.OrangeHRM_HomePage;
import pages.OrangeHRM_LoginPage;
import testcases.TestConfig;
import utilities.BaseClass;
import utilities.*;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class TC_01_AddEmployee {
    static WebDriver driver;
    public static String browserName;
    public static String yamlPath;
    public static int iRowNumber;
    public static String sExcelPath;
    public static String screenshotPath;
    public static String testcaseName;
    private OrangeHRM_LoginPage orangeHRM_loginPage;
    private OrangeHRM_HomePage orangeHRM_homePage;
    private OrangeHRM_EmployeeListPage orangeHRM_employeeListPage;
    private OrangeHRM_AddEmployeePage orangeHRM_addEmployeePage;

    @Parameters("testId")
    @BeforeClass
    public void beforeAddEmployee(@Optional(Constant.testId) String testId) throws Exception{

        testcaseName=Thread.currentThread().getStackTrace()[1].getClassName().substring(Thread.currentThread().getStackTrace()[1].getClassName().indexOf('.')+1);
        System.out.println("TestId for the AddEmployee testcase is :"+testId);
        String path=System.getProperty("user.dir");
        System.out.println("Project Path is :"+path);
        screenshotPath = Utils.createScreenshotsFolder(path);

        yamlPath = path+"\\src\\main\\resources\\Config.yaml";
        browserName = YamlUtils.getYamlData(yamlPath,"browser");

        driver= Utils.launchBrowser(browserName);
        new BaseClass(driver);
        orangeHRM_loginPage = new OrangeHRM_LoginPage(driver);
        orangeHRM_loginPage = PageFactory.initElements(driver,OrangeHRM_LoginPage.class);
        orangeHRM_homePage = new OrangeHRM_HomePage(driver);
        orangeHRM_homePage = PageFactory.initElements(driver,OrangeHRM_HomePage.class);
        orangeHRM_employeeListPage = new OrangeHRM_EmployeeListPage(driver);
        orangeHRM_employeeListPage=PageFactory.initElements(driver,OrangeHRM_EmployeeListPage.class);
        orangeHRM_addEmployeePage = new OrangeHRM_AddEmployeePage(driver);
        orangeHRM_addEmployeePage=PageFactory.initElements(driver,OrangeHRM_AddEmployeePage.class);

        String url = YamlUtils.getYamlData(yamlPath,"orangeHRMURL");
        DriverUtils.loadURL(url);

        sExcelPath = path+"\\src\\main\\resources\\TestData.xlsx";
        ExcelUtils.setExcelFile(path+"\\src\\main\\resources\\TestData.xlsx");
        iRowNumber = ExcelUtils.getRowNumber(testId, "AddEmployee");
    }

    @Test
    public void addEmployee() throws Exception{

        orangeHRM_loginPage.login_OrangeHRM(iRowNumber);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_1_OrangeHRM_LoginPage");

        orangeHRM_homePage.verifyLogin();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_2_OrangeHRM_HomePage");

        orangeHRM_employeeListPage.clickAddEmployee();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_3_OrangeHRM_EmployeeList");

        orangeHRM_addEmployeePage.addEmployee(iRowNumber,sExcelPath, screenshotPath, testcaseName);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_4_OrangeHRM_AddEmployee");

        orangeHRM_employeeListPage.verifyAddEmployeeData(iRowNumber, sExcelPath);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_5_OrangeHRM_EmployeeVerification");

        orangeHRM_loginPage.logout_orangeHRM();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_6_OrangeHRM_Logout");
    }

    @AfterClass
    public void tearDown(){
        DriverUtils.closeBrowser();
    }

}
