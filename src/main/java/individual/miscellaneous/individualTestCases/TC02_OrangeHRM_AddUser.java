package individual.miscellaneous.individualTestCases;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class TC02_OrangeHRM_AddUser {
    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        String path=System.getProperty("user.dir");
        System.out.println("Project Path is :"+path);
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
        boolean isLoginSuccessful= driver.findElement(By.xpath("//i[@class='material-icons'][text()='oxd_home_menu']")).isDisplayed();
        if(isLoginSuccessful){
            System.out.println("Login is successful");
        }else{
            System.out.println("Login is not successful");
            throw new Exception();
        }


        driver.findElement(By.xpath("(//span[text()='HR Administration'])[1]")).click();
        System.out.println("HR Administration link is clicked");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='systemUserDiv']//table//tbody//tr[1]")));

        driver.findElement(By.xpath("//i[text()='add']")).click();
        System.out.println(" + Add user icon clicked ");

        //Add User
        driver.findElement(By.xpath("//input[@id='selectedEmployee_value']")).sendKeys("1061");
        System.out.println("Employee ID is entered as EmployeeName");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='selectedEmployee_value']")).sendKeys(Keys.TAB);

        String userName="testautomation";
        driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys(userName);
        System.out.println("User Name is entered");

        driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("Admin@123");
        System.out.println("Admin@123 is Entered as Password in Password field");
        driver.findElement(By.xpath("//input[@placeholder='Confirm Password']")).sendKeys("Admin@123");
        System.out.println("Admin@123 is entered as Confirm Password");

        driver.findElement(By.xpath("//button[@id='modal-save-button']")).click();
        System.out.println("clicked on save button to create User");

        Thread.sleep(4000);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement element_filter=driver.findElement(By.xpath("//i[text()='oxd_filter']"));
        js.executeScript("arguments[0].click()",element_filter);
        System.out.println("clicked on Ohrm filter in the users menu");

        driver.findElement(By.xpath("//form[@id='frmSystemUserSearch']/div//input[@id='systemuser_uname_filter']")).sendKeys(userName);
        System.out.println(userName+" is entered as UserName in filter Users");

        driver.findElement(By.xpath("//a[text()='Search']")).click();
        System.out.println("Clicked on Search Button");

        //Verification of User
        Wait<WebDriver> wait_UserName= new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        wait_UserName.until(new Function<WebDriver,Boolean>() {
            public Boolean apply(WebDriver driver){
                int numberOfRecords=driver.findElements(By.xpath("//div[@id='systemUserDiv']//table//tbody/tr")).size();
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
                String actualuserName=driver.findElement(By.xpath("//div[@id='systemUserDiv']//table//tbody/tr[1]/td[2]")).getText();
                System.out.println("User Name from the filtered users is :"+actualuserName);
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

        String employeeName=driver.findElement(By.xpath("//div[@id='systemUserDiv']//table//tbody/tr[1]/td[4]")).getText();
        System.out.println("Employee Name is :"+employeeName);

        driver.findElement(By.xpath("//span[text()='Log Out']")).click();

        driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(userName);
        System.out.println(userName+" is entered as username");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Admin@123");
        System.out.println("Admin@123 is entered as password");

        driver.findElement(By.xpath("//button[text()='Login']")).click();
        System.out.println("Click action is performed on login button with new user");

        String actualEmployeeName=driver.findElement(By.xpath("//div[@id='sidebar-profile-picture']/a")).getText();
        System.out.println(actualEmployeeName+ " is logged into the application");

        if(actualEmployeeName.equalsIgnoreCase(employeeName)){
            System.out.println("Add User Verification is successful");
        }else{
            System.out.println("Add User Verification is not successful");
            throw new Exception();
        }

        driver.findElement(By.xpath("//span[text()='Log Out']")).click();

        driver.quit();

    }
}
