package TestCases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class TC04_ApplyLeave {
    public static void main(String[] args) throws Exception{
        //System.setProperty("webdriver.chrome.driver", "C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver","C:\\Anitha\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver_win32_1\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String path = System.getProperty("user.dir");
        System.out.println("Project Path is :" + path);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println("Implicit timeout added for 20 seconds");
        driver.manage().window().maximize();

        driver.get("https://testautomation9-trials7501.orangehrmlive.com/");
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

        driver.findElement(By.xpath("(//span[text()='Leave'])[1]")).click();
        System.out.println("Click action is performed on Leave menu");

        driver.findElement(By.xpath("//a[@data-automation-id='menu_leave_applyLeave']")).click();
        System.out.println("Click action is performed on Apply under leave menu");
        driver.findElement(By.xpath("//div[@class='select-wrapper initialized']")).click();
        System.out.println("Click action is performed on Leave Type");

        driver.findElement(By.xpath("//div[@class='select-wrapper initialized']/ul/li[5]/span")).click();
        System.out.println("Sick leave is selected under Leave Type");

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));

        //leave from Date
        String from_date="17-August-2022";
        String fd_expectedYear=from_date.split("-")[2];
        String fd_expectedMonth=from_date.split("-")[1];
        String fd_expectedDate=from_date.split("-")[0];

        driver.findElement(By.xpath("(//i[text()='date_range'])[1]")).click();
        System.out.println("Click action is performed on From date menu");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select-wrapper picker__select--year']")));
        driver.findElement(By.xpath("//div[@class='select-wrapper picker__select--year']")).click();
        System.out.println("Year drop-down is clicked");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select-wrapper picker__select--year']/ul/li")));
        List<WebElement> elements_years=driver.findElements(By.xpath("//div[@class='select-wrapper picker__select--year']/ul/li"));
        for(WebElement element_year:elements_years){
            String actualYear=element_year.getText();
            if(actualYear.equalsIgnoreCase(fd_expectedYear)){
                element_year.click();
                System.out.println("Year is selected for the Date of Birth");
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select-wrapper.picker__select--month")));
        driver.findElement(By.cssSelector(".select-wrapper.picker__select--month")).click();
        System.out.println("Month drop-down is clicked");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select-wrapper picker__select--month']/ul/li")));
        List<WebElement> elements_months=driver.findElements(By.xpath("//div[@class='select-wrapper picker__select--month']/ul/li"));
        for(WebElement element_month:elements_months){
            String actualMonth=element_month.getText();
            if(actualMonth.equalsIgnoreCase(fd_expectedMonth)){
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
                if(actualDate.equalsIgnoreCase(fd_expectedDate)){
                    ele_col_date.click();
                    System.out.println("Date is selected for the Date of Birth");
                    break outer;
                }
            }
        }

        //leave to Date

        String to_dob="17-August-2022";
        String to_expectedYear=to_dob.split("-")[2];
        String to_expectedMonth=to_dob.split("-")[1];
        String to_expectedDate=to_dob.split("-")[0];

        driver.findElement(By.xpath("(//i[text()='date_range'])[2]")).click();
        System.out.println("Click action is performed on From date menu");

        driver.findElement(By.xpath("//div[@class='select-wrapper picker__select--year']/span")).click();
        System.out.println("Year drop-down is clicked");

        List<WebElement> to_elements_years=driver.findElements(By.xpath("//div[@class='select-wrapper picker__select--year']/ul/li"));
        for(WebElement element_year:to_elements_years){
            String actualYear=element_year.getText();
            if(actualYear.equalsIgnoreCase(to_expectedYear)){
                element_year.click();
                System.out.println("Year is selected for the Date of Birth");
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select-wrapper.picker__select--month")));
        driver.findElement(By.cssSelector(".select-wrapper.picker__select--month")).click();
        System.out.println("Month drop-down is clicked");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select-wrapper picker__select--month']/ul/li")));
        List<WebElement> to_elements_months=driver.findElements(By.xpath("//div[@class='select-wrapper picker__select--month']/ul/li"));
        for(WebElement element_month:to_elements_months){
            String actualMonth=element_month.getText();
            if(actualMonth.equalsIgnoreCase(to_expectedMonth)){
                element_month.click();
                System.out.println("Month is selected for the Date of Birth");
                break;
            }
        }

        List<WebElement> to_elements_rows_dates=driver.findElements(By.xpath("//label[text()='Date of Birth']//following-sibling::span[1]//table//tbody/tr"));
        outer: for(WebElement ele_row_date:to_elements_rows_dates){
            List<WebElement> elements_columns_dates=ele_row_date.findElements(By.xpath("./td/div[contains(@class,'infocus')]"));
            inner: for(WebElement ele_col_date:elements_columns_dates){
                String actualDate=ele_col_date.getText();
                if(actualDate.equalsIgnoreCase(to_expectedDate)){
                    ele_col_date.click();
                    System.out.println("Date is selected for the Date of Birth");
                    break outer;
                }
            }
        }

        driver.findElement(By.xpath("//textarea[@id='comment']")).sendKeys("Personal work");
        System.out.println("Personal work is entered under comment Textarea");

        driver.findElement(By.xpath("//button[text()='Apply']")).click();
        System.out.println("Clicked on apply button");

        //Insufficient Balance page

    }

    public static WebElement waitForElement(WebDriver driver, By locator, int timeout) {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        //WebElement waitElement = wait1.until(ExpectedConditions.presenceOfElementLocated(locator));
        //return waitElement;
        return null;
    }
}
