package orangehrm;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
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

    @BeforeClass
    public void beforeAddEmployee() throws Exception{
        String path=System.getProperty("user.dir");
        System.out.println("Project Path is :"+path);

        yamlPath = path+"\\src\\main\\resources\\Config.yaml";
        browserName = YamlUtils.getYamlData(yamlPath,"browser");

        driver= Utils.launchBrowser(browserName);
        new BaseClass(driver);

        String url = YamlUtils.getYamlData(yamlPath,"orangeHRMURL");
        DriverUtils.loadURL(url);
    }

    @Test
    public void addEmployee() throws Exception{

        CommonMethods_OrangeHRM.login_OrangeHRM("Admin","Admin@123");

        //Login verification
        boolean isLoginSuccessful= driver.findElement(By.xpath("//i[@class='material-icons'][text()='oxd_home_menu']")).isDisplayed();
        if(isLoginSuccessful){
            System.out.println("Login is successful");
        }else{
            System.out.println("Login is not successful");
            throw new Exception();
        }
        //EmployeeList - Add Employee
        driver.findElement(By.linkText("Employee List")).click();
        System.out.println("EmployeeList link is clicked");
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[text()='add']")));
        driver.findElement(By.xpath("//i[text()='add']")).click();
        System.out.println("click action performed on Add Employee");
        //Add Employee
        String firstName="Test";
        String lastName="Catalogue";
        driver.findElement(By.xpath("//input[@id='first-name-box']")).sendKeys(firstName);
        System.out.println("FirstName is entered");
        driver.findElement(By.xpath("//input[@id='last-name-box']")).sendKeys(lastName);
        System.out.println("LastName is entered");
        driver.findElement(By.xpath("//i[text()='arrow_drop_down']")).click();
        System.out.println("Drop-down for location is clicked");
        String explocation="India Office";
        By locator_Locations = By.xpath("//label[text()='Location']//following-sibling::div//ul/li/a/span");
        Utils.selectDropdown_withoutSelectTag(locator_Locations, explocation);

        driver.findElement(By.xpath("//button[@id='modal-save-button']")).click();
        System.out.println("click action performed on next button");

        //Personal Details
        String dob="17-March-1986";
        String expectedYear=dob.split("-")[2];
        String expectedMonth=dob.split("-")[1];
        String expectedDate=dob.split("-")[0];

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Date of Birth']//following-sibling::span[1]/span/i")));
        driver.findElement(By.xpath("//label[text()='Date of Birth']//following-sibling::span[1]/span/i")).click();
        System.out.println("Date of Birth icon is clicked");

        driver.findElement(By.cssSelector(".select-wrapper.picker__select--year")).click();
        System.out.println("Year drop-down is clicked");
        By locator_Years = By.xpath("//div[@class='select-wrapper picker__select--year']/ul/li");
        Utils.selectDropdown_withoutSelectTag(locator_Years, expectedYear);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select-wrapper.picker__select--month")));
        driver.findElement(By.cssSelector(".select-wrapper.picker__select--month")).click();
        System.out.println("Month drop-down is clicked");
        Thread.sleep(2000);
        By locator_months = By.xpath("//div[@class='select-wrapper picker__select--month']/ul/li");
        Utils.selectDropdown_withoutSelectTag(locator_months, expectedMonth);

        List<WebElement> elements_rows_dates=driver.findElements(By.xpath("//label[text()='Date of Birth']//following-sibling::span[1]//table//tbody/tr"));
        outer: for(WebElement ele_row_date:elements_rows_dates){
            List<WebElement> elements_columns_dates=ele_row_date.findElements(By.xpath("./td/div[contains(@class,'infocus')]"));
            inner: for(WebElement ele_col_date:elements_columns_dates){
                String actualDate=ele_col_date.getText();
                if(actualDate.equalsIgnoreCase(expectedDate)){
                    ele_col_date.click();
                    System.out.println("Date is selected for the Date of Birth");
                    break outer;
                }
            }
        }

        WebElement element_nextBtn=driver.findElement(By.xpath("//button[text()='Next']"));
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element_nextBtn);
        js.executeScript("arguments[0].click();", element_nextBtn);
        System.out.println("Next button clicked in the Personal Details page");

        //Employment Details page
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//label[text()='Work Shift']"))));
        WebElement element_RegionDropDown=driver.findElement(By.xpath("//label[text()='Region']//following-sibling::div[1]/button/div/div/div"));
        js.executeScript("arguments[0].scrollIntoView(true);",element_RegionDropDown);
        element_RegionDropDown.click();
        System.out.println("Region drop-down is clicked");
        String expectedRegion="Region-2";
        By locator_Regions = By.xpath("//label[text()='Region']//following-sibling::div[1]/div//ul/li/a/span");
        Utils.selectDropdown_withoutSelectTag(locator_Regions, expectedRegion);

        WebElement element_FTEDropDown=driver.findElement(By.xpath("//label[text()='FTE']//following-sibling::div[1]/button/div/div/div"));
        element_FTEDropDown.click();
        System.out.println("FTE drop-down is clicked");
        String expectedFTE="0.75";
        By locator_ftes = By.xpath("//label[text()='FTE']//following-sibling::div[1]/div//ul/li/a/span");
        Utils.selectDropdown_withoutSelectTag(locator_ftes, expectedFTE);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Temporary Department']//following-sibling::div[1]/button/div/div/div")));
        WebElement element_TemporaryDepartmentDropDown=driver.findElement(By.xpath("//label[text()='Temporary Department']//following-sibling::div[1]/button/div/div/div"));
        element_TemporaryDepartmentDropDown.click();
        System.out.println("Temporary Department drop-down is clicked");
        String expectedTemporaryDepartment="Sub unit-3";
        By locator_TemporaryDepartments = By.xpath("//label[text()='Temporary Department']//following-sibling::div[1]/div//ul/li/a/span");
        Utils.selectDropdown_withoutSelectTag(locator_TemporaryDepartments, expectedTemporaryDepartment);

        //PersonalDetails - last page
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        System.out.println("save button is clicked");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='pimPersonalDetailsForm']//button[text()='Save']")));
        driver.findElement(By.xpath("//form[@id='pimPersonalDetailsForm']//button[text()='Save']")).click();
        System.out.println("Click action is performed on save button in Personal Details of last page");

        //Employee Management - Employee List
        driver.findElement(By.linkText("Employee Management")).click();
        System.out.println("Employee Management link is clicked");
        driver.findElement(By.linkText("Employee List")).click();
        System.out.println("Click action is performed on Employee List");
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[text()='add']")));

        driver.navigate().to("https://automationcatalogue-trials76.orangehrmlive.com/client/#/pim/employees");

        //Add Employee verification
        String completeName=firstName+" "+lastName;
        driver.findElement(By.xpath("//input[@id='employee_name_quick_filter_employee_list_value']")).sendKeys(completeName);
        System.out.println(completeName+" is entered as CompleteName in the Search box");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='employee_name_quick_filter_employee_list_dropdown']//div[3]/span[1]")));

        driver.findElement(By.xpath("//div[@id='employee_name_quick_filter_employee_list_dropdown']//div[3]/span[1]")).click();
        System.out.println("First result is selected from a result drop-down");

        //driver.findElement(By.xpath("//i[text()='ohrm_search']")).click();
        //System.out.println("click action is performed on Search button");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Rows per page']")));

        Wait<WebDriver> wait_EmployeeName= new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        wait_EmployeeName.until(new Function<WebDriver,Boolean>() {
            public Boolean apply(WebDriver driver){
                int numberOfRecords=driver.findElements(By.xpath("//table[@id='employeeListTable']//tbody/tr")).size();
                if(numberOfRecords==1){
                    System.out.println("Only one record is present");
                    return true;
                }else{
                    System.out.println("More than one record is present / Data is not populated");
                    return false;
                }
            }
        });


        for(int iCount=1;iCount<=3;iCount++){
            try{
                String employeeId=driver.findElement(By.xpath("//table[@id='employeeListTable']//tbody/tr[1]/td[2]")).getText();
                System.out.println("EmployeeId is :"+employeeId);
                break;
            }catch (StaleElementReferenceException se){
                Thread.sleep(1000);
                System.out.println("Stale Element Reference Exception is occurred and retrying another time");
            }catch (Exception e){
                System.out.println("Exception occurred!!!"+e.getMessage());
                e.printStackTrace();
                throw new Exception();
            }
        }

        String actualCompleteName=driver.findElement(By.xpath("//table[@id='employeeListTable']//tbody/tr[1]/td[3]")).getText();
        if(completeName.equalsIgnoreCase(actualCompleteName)){
            System.out.println("Employee Name verification is successful");
        }else{
            System.out.println("Employee Name verification is not successful");
            throw new Exception();
        }

        String actualLocation=driver.findElement(By.xpath("//table[@id='employeeListTable']//tbody/tr[1]/td[8]")).getText();
        if(explocation.equalsIgnoreCase(actualLocation)){
            System.out.println("Location verification is successful");
        }else {
            System.out.println("Location verification is not successful");
            throw new Exception();
        }

        System.out.println("Add Employee is successful");

        CommonMethods_OrangeHRM.logout_orangeHRM();
    }

    @AfterClass
    public void tearDown(){
        DriverUtils.closeBrowser();
    }

}
