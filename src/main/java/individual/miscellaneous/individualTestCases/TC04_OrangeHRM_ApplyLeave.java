package individual.miscellaneous.individualTestCases;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class TC04_OrangeHRM_ApplyLeave {
    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver","C:\\Anitha\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver_win32_1\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String path = System.getProperty("user.dir");
        System.out.println("Project Path is :" + path);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println("Implicit timeout added for 20 seconds");
        driver.manage().window().maximize();

        driver.get("https://automationcatalogue-trials76.orangehrmlive.com/");
        System.out.println("OrangeHRM url loaded");
        //OrangeHRM Login
        driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
        System.out.println("Admin is entered as username");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Admin@123");
        System.out.println("Admin@123 is Entered as password");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("Login button is clicked");

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
        String expectedLeaveType="Sick Leave - US";
        List<WebElement> elements_leaveTypes = driver.findElements(By.xpath("//div[@id='leaveType_inputfileddiv']/div/ul/li"));
        for(WebElement element_leave:elements_leaveTypes){
            String leaveType=element_leave.getText();
            if(leaveType.equalsIgnoreCase(expectedLeaveType)){
                element_leave.click();
                System.out.println(expectedLeaveType+" is selected as LeaveType");
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Check Leave Balance']")));

        //leave from Date
        String from_date="11-June-2022";
        String fd_expectedYear=from_date.split("-")[2];
        String fd_expectedMonth=from_date.split("-")[1];
        String fd_expectedDate=from_date.split("-")[0];

        driver.findElement(By.xpath("(//i[text()='date_range'])[1]")).click();
        System.out.println("Click action is performed on From date menu");
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//div[@class='select-wrapper picker__select--year'])[1]")).click();
        System.out.println("Year drop-down is clicked");
        List<WebElement> elements_years=driver.findElements(By.xpath("//div[@class='select-wrapper picker__select--year']/ul/li"));
        for(WebElement element_year:elements_years){
            String actualYear=element_year.getText();
            if(actualYear.equalsIgnoreCase(fd_expectedYear)){
                element_year.click();
                System.out.println("Year is selected for the leave from date");
                break;
            }
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='select-wrapper picker__select--month'])[1]")));
        driver.findElement(By.xpath("(//div[@class='select-wrapper picker__select--month'])[1]")).click();
        System.out.println("Month drop-down is clicked");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select-wrapper picker__select--month']/ul/li")));
        List<WebElement> elements_months=driver.findElements(By.xpath("//div[@class='select-wrapper picker__select--month']/ul/li"));
        for(WebElement element_month:elements_months){
            String actualMonth=element_month.getText();
            if(actualMonth.equalsIgnoreCase(fd_expectedMonth)){
                element_month.click();
                System.out.println("Month is selected for the the leave from date");
                break;
            }
        }

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
        String to_dob="16-June-2022";
        String to_expectedYear=to_dob.split("-")[2];
        String to_expectedMonth=to_dob.split("-")[1];
        String to_expectedDate=to_dob.split("-")[0];

        driver.findElement(By.xpath("(//i[text()='date_range'])[2]")).click();
        System.out.println("Click action is performed on to date menu");

        driver.findElement(By.xpath("(//div[@class='select-wrapper picker__select--year'])[1]")).click();
        System.out.println("Year drop-down is clicked");

        List<WebElement> to_elements_years=driver.findElements(By.xpath("//div[@class='select-wrapper picker__select--year']/ul/li"));
        for(WebElement element_year:to_elements_years){
            String actualYear=element_year.getText();
            if(actualYear.equalsIgnoreCase(to_expectedYear)){
                element_year.click();
                System.out.println("Year is selected for the to date");
                break;
            }
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='select-wrapper picker__select--month']/input")));

        WebElement element_ToDate_Month=driver.findElement(By.xpath("//div[@class='select-wrapper picker__select--month']/input"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",element_ToDate_Month);
        System.out.println("Month drop-down is clicked");

        List<WebElement> to_elements_months=driver.findElements(By.xpath("//div[@class='select-wrapper picker__select--month']/ul/li"));
        for(WebElement element_month:to_elements_months){
            String actualMonth=element_month.getText();
            if(actualMonth.equalsIgnoreCase(to_expectedMonth)){
                element_month.click();
                System.out.println("Month is selected for the to date");
                break;
            }
        }

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

        driver.findElement(By.xpath("//span[text()='Log Out']")).click();
        System.out.println("Logged out from the OrangeHRM application");

        driver.quit();

    }

}
