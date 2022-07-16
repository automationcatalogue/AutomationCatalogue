package TestCases;
import Utils.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;

public class TC02_AddUser {
    public static void main(String[] args) throws Exception {
        String projectPath=System.getProperty("user.dir");
        System.out.println("Project Path is :"+projectPath);
        System.out.println("hello");

        Yaml yaml_Config = new Yaml();
        FileInputStream fis_Config = new FileInputStream(projectPath+ Constants.yaml_filePath);
        HashMap<String, String> map_config= (HashMap<String, String>) yaml_Config.load(fis_Config);

        String driverPath= map_config.get("chromeDriverExefilepath");
        System.out.println("ChromeDriver Path is :"+driverPath);

        //Initialize WebDriver
        System.setProperty("webdriver.chrome.driver",driverPath);
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println("Implicit timeout added for 20 seconds");
        driver.manage().window().maximize();
        //driver.get("https://automatecatalog-trials7401.orangehrmlive.com/auth/login");
        driver.get("https://testcatalogue-trials7501.orangehrmlive.com/");
        System.out.println("OrangeHRM url loaded");
        String title=driver.getTitle();
        System.out.println("Title is " +title);
        String path=System.getProperty("user.dir");
        System.out.println(path);
        FileInputStream fis=new FileInputStream(path+"\\TestData\\AddEmployee.xlsx");
        XSSFWorkbook wbk=new XSSFWorkbook(fis);
        XSSFSheet sh=wbk.getSheet("EmpDetails");
        XSSFRow row=sh.getRow(1);
        int rowCount=sh.getLastRowNum();
        System.out.println("total number of rows : "+rowCount);

        XSSFCell cell=row.getCell(1);
        String username=cell.getStringCellValue();
        System.out.println("UserName from xl file is : "+username);

        cell=row.getCell(2);
        String password=cell.getStringCellValue();
        System.out.println("Password from xl file is : "+password);

        cell=row.getCell(3);
        String firstname=cell.getStringCellValue();
        System.out.println("firstname from xl file is : "+firstname);

        cell=row.getCell(4);
        String lastname=cell.getStringCellValue();
        System.out.println("lastname from xl file is : "+lastname);

        cell=row.getCell(5);
        String location=cell.getStringCellValue();
        System.out.println("location from xl file is : "+location);

        cell=row.getCell(8);
        String empUserName=cell.getStringCellValue();
        System.out.println("Given emp username is : "+empUserName);

        driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
        System.out.println("Admin is entered as username");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
        System.out.println("Admin@123 is Entered as password");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("Login button is clicked");
        boolean isDisplayed=driver.findElement(By.linkText("oxd_home_menu")).isDisplayed();
        if(isDisplayed){
            System.out.println("Dashboard/Home  is displayed");
        }else {
           throw new Exception();
        }
        //driver.findElement(By.xpath("//span[text()='Admin']")).click();
        System.out.println("clicked on admin in menu");
        driver.findElement(By.xpath("//a[@id='sidebar-toggle']")).click();
        System.out.println("Click action is performed on side toggle button");
        driver.findElement(By.xpath("(//a[@id='menu_item_81'])[2]")).click();
        System.out.println("clicked on User Management");
        driver.findElement(By.linkText("Users")).click();
        System.out.println("clicked on users under User Management");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='highlight bordered']//tbody//tr[2]//td[@class='edit_item tooltipped']")));

        driver.findElement(By.xpath("//i[text()='add']")).click();
        System.out.println(" + Add user icon clicked ");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@id='selectedEmployee_value']")).sendKeys(firstname+" "+lastname);
        driver.findElement(By.xpath("//div[@class='angucomplete-wrapper']/div[3]")).click();
        System.out.println("First name in the drop down is selected");
        //driver.findElement(By.xpath("//input[@id='selectedEmployee_value']")).sendKeys(Keys.TAB);
        System.out.println( firstname+" "+lastname +"is Entered as Employee Name");

        driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys(empUserName+"9");
        System.out.println(empUserName+" is entered as User Name");

        driver.findElement(By.xpath("(//i[text()='arrow_drop_down'])[1]")).click();
        System.out.println("Clicked on Ess Drop down");
        driver.findElement(By.xpath("(//a[@class='dropdown-item active selected'])[1]")).click();
        System.out.println("Default ESS is selected in ESS drop-down");
       // driver.findElement(By.xpath("(//i[text()='arrow_drop_down'])[2]")).click();
        System.out.println("clicked on Supervisor Role Drop-down");
       // driver.findElement(By.xpath("(//a[@class='dropdown-item active selected'])[1]")).click();
        System.out.println("Default Supervisor is Selected under Supervisor Role");
        driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("Admin@123");
        System.out.println("Admin@123 is Entered as Password in Password field");
        driver.findElement(By.xpath("//input[@placeholder='Confirm Password']")).sendKeys("Admin@123");
        System.out.println("Admin@123 is entered as Confirm Password");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@id='modal-save-button']")).click();
        System.out.println("clicked on save button to create User");
        wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[text()='oxd_filter']")));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[text()='oxd_filter']")));
        System.out.println("waiting for filter element to be loaded");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//i[text()='oxd_filter']")).click();
        System.out.println("clicked on Ohrm filter in the users menu");
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='employee_uname_filter']")));

        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Search']")));
        //driver.findElement(By.xpath("//label[@for='employee_uname_filter']")).sendKeys("DanielDh");
        //driver.findElement(By.xpath("(//label[@for='employee_uname_filter'])[2]")).click();
        String reqUser="DaniDp";
        driver.findElement(By.xpath("//form[@id='frmSystemUserSearch']/div//input[@id='systemuser_uname_filter']")).sendKeys(empUserName);
        driver.findElement(By.xpath("//form[@id='frmSystemUserSearch']/div//input[@id='systemuser_uname_filter']")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//input[@id='employee_name_filter_value']")).sendKeys(empUserName);
        driver.findElement(By.xpath("//input[@id='employee_name_filter_value']")).sendKeys(Keys.TAB);
        //driver.findElement(By.xpath("(//div[@id='employee_name_filter_dropdown'])[2]/div[3]/span/span")).click();
        System.out.println(empUserName+"is Entered as Employee Name");
        driver.findElement(By.xpath("//a[text()='Search']")).click();
        System.out.println("Clicked on Search Button");
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Thread.sleep(1000);

        TakesScreenshot ts=(TakesScreenshot)driver;
        File src=ts.getScreenshotAs(OutputType.FILE);
        File destination=new File(path+"\\screenshots\\"+empUserName+"_Verify_Timestamp.png");
        FileUtils.copyFile(src,destination);
        System.out.println("Screenshot captured for : "+empUserName+"_Verify_Timestamp.png");

        driver.findElement(By.xpath("//span[text()='Log Out']")).click();

        driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(empUserName);
        System.out.println(empUserName+" is entered as username");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
        System.out.println(password+"is emtered as password");

        driver.findElement(By.xpath("//button[text()='Login']")).click();
        System.out.println("Click action is performed on login button with new user");

        TakesScreenshot ts1=(TakesScreenshot)driver;
        File src1=ts1.getScreenshotAs(OutputType.FILE);
        File destination1=new File(path+"\\screenshots\\"+empUserName+"_Login_Timestamp.png");
        FileUtils.copyFile(src1,destination1);
        System.out.println("Screenshot captured for : "+empUserName+"_Login_TimeStamp.png");

    }
}
