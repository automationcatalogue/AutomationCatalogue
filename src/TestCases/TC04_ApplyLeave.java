package TestCases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TC04_ApplyLeave {
    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver","C:\\Anitha\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver_win32_1\\chromedriver.exe");
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
        driver.findElement(By.xpath("(//i[text()='date_range'])[1]")).click();
        System.out.println("Click action is performed on From date menu");
        //Dates
        driver.findElement(By.xpath("(//table[@class='picker__table']/tbody/tr/td/div[contains(text(),'21')])[1]")).click();
        System.out.println("21 is selected as from Date");

        driver.findElement(By.xpath("(//i[text()='date_range'])[2]")).click();
        System.out.println("Click action is performed on To date menu");

        driver.findElement(By.xpath("((//table[@class='picker__table'])[2]//tbody/tr/td/div[contains(text(),'25')]")).click();
        System.out.println("25 is selected as from Date");

        driver.findElement(By.xpath("//textarea[@id='comment']")).click();
                //sendKeys("Personal work");
        System.out.println("Personal work is entered under comment Textarea");


    }

    public static WebElement waitForElement(WebDriver driver, By locator, int timeout) {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        //WebElement waitElement = wait1.until(ExpectedConditions.presenceOfElementLocated(locator));
        //return waitElement;
        return null;
    }
}
