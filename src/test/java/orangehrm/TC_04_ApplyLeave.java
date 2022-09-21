package orangehrm;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.BaseClass;
import utilities.*;
import java.time.Duration;
import java.util.List;

public class TC_04_ApplyLeave {
    static WebDriver driver;
    public static String browserName;
    public static String yamlPath;
    public static int iRowNumber;
    public static String sExcelPath;
    public static String screenshotPath;
    public static String testcaseName;

    @Parameters("testId")
    @BeforeClass
    public void beforeApplyLeave(@Optional(Constant.testId) String testId) throws Exception{
        testcaseName=Thread.currentThread().getStackTrace()[1].getClassName().substring(Thread.currentThread().getStackTrace()[1].getClassName().indexOf('.')+1);
        System.out.println("TestId for the ApplyLeave testcase is :"+testId);
        String path=System.getProperty("user.dir");
        System.out.println("Project Path is :"+path);
        screenshotPath = Utils.createScreenshotsFolder(path);
        yamlPath = path+"\\src\\main\\resources\\Config.yaml";
        browserName = YamlUtils.getYamlData(yamlPath,"browser");
        driver= Utils.launchBrowser(browserName);
        new BaseClass(driver);
        String url = YamlUtils.getYamlData(yamlPath,"orangeHRMURL");
        DriverUtils.loadURL(url);
        sExcelPath = path+"\\src\\main\\resources\\TestData.xlsx";
        ExcelUtils.setExcelFile(path+"\\src\\main\\resources\\TestData.xlsx");
        iRowNumber = ExcelUtils.getRowNumber(testId,"AddEmployee");
    }
    @Test
    public void applyLeave() throws Exception{
        //OrangeHRM Login
        String sUserName = ExcelUtils.getCellData(iRowNumber, Constant.sUserName_OrangeHRM,"ApplyLeave");
        System.out.println("UserName from the Excel Sheet is :"+sUserName);
        String sPassword = ExcelUtils.getCellData(iRowNumber, Constant.sUserPassword_OrangeHRM,"ApplyLeave");
        System.out.println("Password from the Excel Sheet is :"+sPassword);
        CommonMethods_OrangeHRM.login_OrangeHRM(sUserName,sPassword);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_OrangeHRMLogin");
        System.out.println("OrangeHRM login Screenshot is captured for : "+testcaseName);

        //Login verification
        boolean isLoginSuccessful = driver.findElement(By.xpath("//i[@class='material-icons'][text()='oxd_home_menu']")).isDisplayed();
        if (isLoginSuccessful) {
            System.out.println("Login is successful");
        } else {
            System.out.println("Login is not successful");
            throw new Exception();
        }

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.findElement(By.xpath("(//span[text()='Leave'])[1]")).click();
        System.out.println("Click action is performed on Leave menu");

        driver.findElement(By.xpath("//a[@data-automation-id='menu_leave_applyLeave']")).click();
        System.out.println("Click action is performed on Apply under leave menu");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='leaveType_inputfileddiv']/div/input")));
        driver.findElement(By.xpath("//div[@id='leaveType_inputfileddiv']/div/input")).click();
        System.out.println("Click action is performed on Leave Type");
        Thread.sleep(1000);
        String expectedLeaveType=ExcelUtils.getCellData(iRowNumber,Constant.sApplyLeave_LeaveType,"ApplyLeave");
        By locator_leaveTypes=By.xpath("//div[@id='leaveType_inputfileddiv']/div/ul/li");
        Utils.selectDropdown_withoutSelectTag(locator_leaveTypes,expectedLeaveType);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Check Leave Balance']")));

        //leave from Date
        String from_date=ExcelUtils.getCellData(iRowNumber,Constant.sApplyLeave_LeaveFromDate,"ApplyLeave");
        System.out.println(from_date+" is selected as from date");
        String fd_expectedYear=from_date.split("-")[2];
        String fd_expectedMonth=from_date.split("-")[1];
        String fd_expectedDate=from_date.split("-")[0];

        WebElement element_FromDate = driver.findElement(By.xpath("(//i[text()='date_range'])[1]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",element_FromDate);
        System.out.println("Click action is performed on From date menu");
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//div[@class='select-wrapper picker__select--year'])[1]")).click();
        System.out.println("Year drop-down is clicked");
        By locator_fdYears=By.xpath("//div[@class='select-wrapper picker__select--year']/ul/li");
        Utils.selectDropdown_withoutSelectTag(locator_fdYears,fd_expectedYear);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='select-wrapper picker__select--month'])[1]")));
        driver.findElement(By.xpath("(//div[@class='select-wrapper picker__select--month'])[1]")).click();
        System.out.println("Month drop-down is clicked");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select-wrapper picker__select--month']/ul/li")));
        By locator_fdMonths=By.xpath("//div[@class='select-wrapper picker__select--month']/ul/li");
        Utils.selectDropdown_withoutSelectTag(locator_fdMonths,fd_expectedMonth);


        List<WebElement> elements_rows_dates=driver.findElements(By.xpath("//label[text()='From Date']//following-sibling::span[1]//table//tbody/tr"));
        outer: for(WebElement ele_row_date:elements_rows_dates){
            List<WebElement> elements_columns_dates=ele_row_date.findElements(By.xpath("./td/div[contains(@class,'infocus')]"));
            inner: for(WebElement ele_col_date:elements_columns_dates){
                String actualDate=ele_col_date.getText();
                if(actualDate.equalsIgnoreCase(fd_expectedDate)){
                    ele_col_date.click();
                    System.out.println("Date is selected for the leave from date");
                    break outer;
                }
            }
        }

        Thread.sleep(2000);

        //leave to Date
        String to_Date=ExcelUtils.getCellData(iRowNumber,Constant.sApplyLeave_LeaveToDate,"ApplyLeave");
        System.out.println(to_Date+" Is selected as to date");
        String to_expectedYear=to_Date.split("-")[2];
        String to_expectedMonth=to_Date.split("-")[1];
        String to_expectedDate=to_Date.split("-")[0];

        driver.findElement(By.xpath("(//i[text()='date_range'])[2]")).click();
        System.out.println("Click action is performed on to date menu");

        driver.findElement(By.xpath("(//div[@class='select-wrapper picker__select--year'])[1]")).click();
        System.out.println("Year drop-down is clicked");
        By locator_tdYears=By.xpath("//div[@class='select-wrapper picker__select--year']/ul/li");
        Utils.selectDropdown_withoutSelectTag(locator_tdYears,to_expectedYear);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='select-wrapper picker__select--month']/input")));

        WebElement element_ToDate_Month=driver.findElement(By.xpath("//div[@class='select-wrapper picker__select--month']/input"));

        js.executeScript("arguments[0].click();",element_ToDate_Month);
        System.out.println("Month drop-down is clicked");
        By locator_tdMonths= By.xpath("//div[@class='select-wrapper picker__select--month']/ul/li");
        Utils.selectDropdown_withoutSelectTag(locator_tdMonths,to_expectedMonth);


        List<WebElement> to_elements_rows_dates=driver.findElements(By.xpath("//label[text()='To Date']//following-sibling::span[1]//table//tbody/tr"));
        outer: for(WebElement ele_row_date:to_elements_rows_dates){
            List<WebElement> elements_columns_dates=ele_row_date.findElements(By.xpath("./td/div[contains(@class,'infocus')]"));
            inner: for(WebElement ele_col_date:elements_columns_dates){
                String actualDate=ele_col_date.getText();
                if(actualDate.equalsIgnoreCase(to_expectedDate)){
                    ele_col_date.click();
                    System.out.println("Date is selected for the to date");
                    break outer;
                }
            }
        }

        driver.findElement(By.xpath("//textarea[@id='comment']")).sendKeys("Not well");
        System.out.println("Not well is entered under comment Textarea");

        driver.findElement(By.xpath("//button[text()='Apply']")).click();
        System.out.println("Clicked on apply button");

        try{
            //Insufficient Balance page
            driver.findElement(By.xpath("//div[@id='application_balance_modal']//a[text()='Ok']")).click();
            System.out.println("Click action is performed on Ok button");

            driver.findElement(By.partialLinkText("Leave List")).click();
            System.out.println("Leave List link is clicked");

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='viewLeaveEntitlement']//table/tbody/tr[1]")));
            driver.navigate().refresh();
            System.out.println("Leave List Webpage is refreshed");
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='viewLeaveEntitlement']//table/tbody/tr[1]")));
            List<WebElement> leaveEntitlements_allrows=driver.findElements(By.xpath("//div[@id='viewLeaveEntitlement']//table/tbody/tr"));
            boolean found=false;
            for(WebElement leaveEntitlement_row:leaveEntitlements_allrows){

                String leaveData=leaveEntitlement_row.findElement(By.xpath("./td[2]")).getText();
                String leaveType=leaveEntitlement_row.findElement(By.xpath("./td[3]")).getText();
                if(leaveData.contains(fd_expectedYear) && leaveData.contains(fd_expectedDate) && leaveData.contains(fd_expectedMonth.substring(0,3))
                        && leaveData.contains(to_expectedYear) && leaveData.contains(to_expectedDate) && leaveData.contains(to_expectedMonth.substring(0,3))
                        && leaveType.equalsIgnoreCase(expectedLeaveType)){
                    found =true;
                    break;
                }
            }

            if(found){
                System.out.println("Leave is successfully applied");
            }else{
                System.out.println("Leave is successfully not applied");
                throw new Exception();
            }
        }catch(NoSuchElementException e){
            //Overlapping page
            System.out.println("Overlap Leave Request pop-up is appeared, please change the leave dates");
            driver.findElement(By.xpath("//a[text()='Close']")).click();
            System.out.println("Clicked on close for overlapping page");
        }

        System.out.println("Apply Leave testcase execution is successful");

        CommonMethods_OrangeHRM.logout_orangeHRM();

        DriverUtils.closeBrowser();

    }

}
