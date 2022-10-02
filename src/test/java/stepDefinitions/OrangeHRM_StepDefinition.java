package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.OrangeHRM_AddEmployeePage;
import pages.OrangeHRM_EmployeeListPage;
import pages.OrangeHRM_HomePage;
import pages.OrangeHRM_LoginPage;
import utilities.*;

public class OrangeHRM_StepDefinition extends BaseClass {
    static WebDriver driver;
    private OrangeHRM_LoginPage orangeHRM_loginPage;
    private OrangeHRM_HomePage orangeHRM_homePage;
    private OrangeHRM_EmployeeListPage orangeHRM_employeeListPage;
    private OrangeHRM_AddEmployeePage orangeHRM_addEmployeePage;
    public static String yamlPath;
    public static String sExcelPath;
    public static int iRowNumber;

    public OrangeHRM_StepDefinition(WebDriver driver) {
        super(driver);
    }

    @Given("^User is loaded the OrangeHRM Website$")
    public void loadOrangeHRM() throws Exception{
        String path=System.getProperty("user.dir");
        driver = getDriver();
        orangeHRM_loginPage = new OrangeHRM_LoginPage(driver);
        orangeHRM_loginPage = PageFactory.initElements(driver,OrangeHRM_LoginPage.class);
        orangeHRM_homePage = new OrangeHRM_HomePage(driver);
        orangeHRM_homePage = PageFactory.initElements(driver,OrangeHRM_HomePage.class);
        orangeHRM_employeeListPage = new OrangeHRM_EmployeeListPage(driver);
        orangeHRM_employeeListPage=PageFactory.initElements(driver,OrangeHRM_EmployeeListPage.class);
        orangeHRM_addEmployeePage = new OrangeHRM_AddEmployeePage(driver);
        orangeHRM_addEmployeePage=PageFactory.initElements(driver,OrangeHRM_AddEmployeePage.class);

        yamlPath = path+"\\src\\main\\resources\\Config.yaml";
        String url = YamlUtils.getYamlData(yamlPath,"orangeHRMURL");
        DriverUtils.loadURL(url);

        sExcelPath = path+"\\src\\main\\resources\\TestData.xlsx";
        ExcelUtils.setExcelFile(path+"\\src\\main\\resources\\TestData.xlsx");
        iRowNumber = ExcelUtils.getRowNumber(Constant.testId, "AddEmployee");
    }

    @When("^User is Logged in with Credentials$")
    public void orangeHRMLogin() throws Exception{
        orangeHRM_loginPage.login_OrangeHRM(iRowNumber);
        Utils.captureScreenshot(CucumberHooks.screenshotPath,"AddEmployee_1_OrangeHRM_LoginPage");
    }

    @Then("^Verify OrangeHRM Login$")
    public void verifyOrangeHRMLogin() throws Exception{
        orangeHRM_homePage.verifyLogin();
        Utils.captureScreenshot(CucumberHooks.screenshotPath,"AddEmployee_2_OrangeHRM_HomePage");
    }

    @And("^User Clicks on AddEmployee in EmployeeList$")
    public void clickAddEmployee() throws Exception{
        orangeHRM_employeeListPage.clickAddEmployee();
        Utils.captureScreenshot(CucumberHooks.screenshotPath,"AddEmployee_3_OrangeHRM_EmployeeList");
    }

    @When("^User performs the AddEmployee$")
    public void addEmployee() throws Exception{
        orangeHRM_addEmployeePage.addEmployee(iRowNumber,sExcelPath, CucumberHooks.screenshotPath, "AddEmployee");
        Utils.captureScreenshot(CucumberHooks.screenshotPath,"AddEmployee_4_OrangeHRM_AddEmployee");
    }

    @Then("^User Verifies AddEmployeeData$")
    public void verifyAddEmployee() throws Exception{
        orangeHRM_employeeListPage.verifyAddEmployeeData(iRowNumber, sExcelPath);
        Utils.captureScreenshot(CucumberHooks.screenshotPath,"AddEmployee_5_OrangeHRM_EmployeeVerification");
    }

    @And("^User log out from the OrangeHRM$")
    public void logout_OrangeHRM() throws Exception{
        orangeHRM_loginPage.logout_orangeHRM();
        Utils.captureScreenshot(CucumberHooks.screenshotPath,"AddEmployee_6_OrangeHRM_Logout");
    }

    @And("^User Closes Browser$")
    public void closeBrowser(){
        DriverUtils.closeBrowser();
    }


}
