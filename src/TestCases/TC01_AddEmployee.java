package TestCases;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;

public class TC01_AddEmployee {
    public static void main(String[] args) throws Exception{
        //WebDriver Initialization
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        String path=System.getProperty("user.dir");
        System.out.println("Project Path is :"+path);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println("Implicit timeout added for 20 seconds");
        driver.manage().window().maximize();

        driver.get("https://testcatalogue-trials7501.orangehrmlive.com/");
        System.out.println("OrangeHRM url loaded");
        //OrangeHRM Login
        driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
        System.out.println("Admin is entered as username");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Admin@123");
        System.out.println("Admin@123 is Entered as password");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("Login button is clicked");

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
        String firstName="Selenium";
        String lastName="Automation";
        driver.findElement(By.xpath("//input[@id='first-name-box']")).sendKeys(firstName);
        System.out.println("FirstName is entered");
        driver.findElement(By.xpath("//input[@id='last-name-box']")).sendKeys(lastName);
        System.out.println("LastName is entered");
        driver.findElement(By.xpath("//i[text()='arrow_drop_down']")).click();
        System.out.println("Drop-down for location is clicked");
        String location="India Office";
        driver.findElement(By.xpath("//span[text()='"+location+"']")).click();
        System.out.println(location+" is selected from a drop-down");
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
        List<WebElement> elements_years=driver.findElements(By.xpath("//div[@class='select-wrapper picker__select--year']/ul/li"));
        for(WebElement element_year:elements_years){
            String actualyear=element_year.getText();
            if(actualyear.equalsIgnoreCase(expectedYear)){
                element_year.click();
                System.out.println("Year is selected for the Date of Birth");
                break;
            }
        }

        driver.findElement(By.cssSelector(".select-wrapper.picker__select--month")).click();
        System.out.println("Month drop-down is clicked");
        List<WebElement> elements_months=driver.findElements(By.xpath("//div[@class='select-wrapper picker__select--month']/ul/li"));
        for(WebElement element_month:elements_months){
            String actualMonth=element_month.getText();
            if(actualMonth.equalsIgnoreCase(expectedMonth)){
                element_month.click();
                System.out.println("Month is selected for the Date of Birth");
                break;
            }
        }

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
        WebElement element_RegionDropDown=driver.findElement(By.xpath("//label[text()='Region']//following-sibling::div[1]/button/div/div/div"));
        js.executeScript("window.scrollIntoView(true);",element_RegionDropDown);
        element_RegionDropDown.click();
        System.out.println("Region drop-down is clicked");
        String expectedRegion="Region-2";
        List<WebElement> elements_regions=driver.findElements(By.xpath("//label[text()='Region']//following-sibling::div[1]/div//ul/li/a/span"));
        for(WebElement ele_region:elements_regions){
            String actualRegion=ele_region.getText();
            if(actualRegion.equalsIgnoreCase(expectedRegion)){
                ele_region.click();
                System.out.println("Region value is selected");
            }
        }

        WebElement element_FTEDropDown=driver.findElement(By.xpath("//label[text()='FTE']//following-sibling::div[1]/button/div/div/div"));
        element_FTEDropDown.click();
        System.out.println("FTE drop-down is clicked");
        String expectedFTE="0.75";
        List<WebElement> elements_ftes=driver.findElements(By.xpath("//label[text()='FTE']//following-sibling::div[1]/div//ul/li/a/span"));
        for(WebElement ele_fte:elements_ftes){
            String actualFTE=ele_fte.getText();
            if(actualFTE.equalsIgnoreCase(expectedFTE)){
                ele_fte.click();
                System.out.println("FTE value is selected");
            }
        }

        WebElement element_TemporaryDepartmentDropDown=driver.findElement(By.xpath("//label[text()='Temporary Department']//following-sibling::div[1]/button/div/div/div"));
        element_FTEDropDown.click();
        System.out.println("Temporary Department drop-down is clicked");
        String expectedTemporaryDepartment="Sub unit-3";
        List<WebElement> elements_temporaryDepartments=driver.findElements(By.xpath("//label[text()='Temporary Department']//following-sibling::div[1]/div//ul/li/a/span"));
        for(WebElement ele_temporaryDepartment:elements_temporaryDepartments){
            String actualTemporaryDepartment=ele_temporaryDepartment.getText();
            if(actualTemporaryDepartment.equalsIgnoreCase(expectedTemporaryDepartment)){
                ele_temporaryDepartment.click();
                System.out.println("Temporary Department value is selected");
            }
        }

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

        //Add Employee verification
        String completeName=firstName+" "+lastName;
        driver.findElement(By.xpath("//input[@id='employee_name_quick_filter_employee_list_value']")).sendKeys(completeName);
        System.out.println(completeName+" is entered as CompleteName in the Search box");

        driver.findElement(By.xpath("//div[@id='employee_name_quick_filter_employee_list_dropdown']//div[3]/span[1]")).click();
        System.out.println("First result is selected from a result drop-down");
        driver.findElement(By.xpath("//i[text()='ohrm_search']")).click();
        System.out.println("click action is performed on Search button");

        String employeeId=driver.findElement(By.xpath("//table[@id='employeeListTable']//tbody/tr[1]/td[2]")).getText();
        System.out.println("EmployeeId is :"+employeeId);

        String actualCompleteName=driver.findElement(By.xpath("//table[@id='employeeListTable']//tbody/tr[1]/td[3]")).getText();
        if(completeName.equalsIgnoreCase(actualCompleteName)){
            System.out.println("Employee Name verification is successful");
        }else{
            System.out.println("Employee Name verification is not successful");
            throw new Exception();
        }

        String actualLocation=driver.findElement(By.xpath("//table[@id='employeeListTable']//tbody/tr[1]/td[8]")).getText();
        if(location.equalsIgnoreCase(actualLocation)){
            System.out.println("Location verification is successful");
        }else {
            System.out.println("Location verification is not successful");
            throw new Exception();
        }

        System.out.println("Add Employee is successful");

        //driver.findElement(By.xpath("//span[text()='Log Out']")).click();
        //System.out.println("Logged out from the OrangeHRM application");

        //driver.quit();
    }

}

