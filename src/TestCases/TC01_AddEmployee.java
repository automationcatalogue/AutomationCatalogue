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
public class TC01_AddEmployee {
    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver","C:\\Anitha\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver_win32_1\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        String path=System.getProperty("user.dir");
        System.out.println(path);
        FileInputStream fis=new FileInputStream(path+"\\TestData\\AddEmployee.xlsx");
        XSSFWorkbook wbk=new XSSFWorkbook(fis);
        XSSFSheet sh=wbk.getSheet("EmpDetails");
        XSSFRow row=sh.getRow(5);
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


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println("Implicit timeout added for 20 seconds");
        driver.manage().window().maximize();
        driver.get("https://testcatalogue-trials7501.orangehrmlive.com/");
        System.out.println("OrangeHRM url loaded");

        driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
        System.out.println("Admin is entered as username");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
        System.out.println("Admin@123 is Entered as password");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("Login button is clicked");
        //driver.findElement(By.xpath("//span[text()='PIM']")).click();
        //System.out.println("click action performed on PIM");
        driver.findElement(By.linkText("Employee List")).click();
        driver.findElement(By.xpath("//i[text()='add']")).click();
        //driver.findElement(By.xpath("//span[text()='Add Employee']")).click();
        System.out.println("click action performed on Add Employee");
        driver.findElement(By.xpath("//input[@id='first-name-box']")).sendKeys(firstname);
        System.out.println("Dani entered as first name");
        driver.findElement(By.xpath("//input[@id='last-name-box']")).sendKeys(lastname);
        System.out.println("Daniel is entered as last name");
        driver.findElement(By.xpath("//i[text()='arrow_drop_down']")).click();
        System.out.println("Drop-down for location is clicked");
        driver.findElement(By.xpath("//span[text()='India Office']")).click();
        System.out.println("Indian office is selected as location");
        driver.findElement(By.xpath("//button[@id='modal-save-button']")).click();
        System.out.println("click action performed on next button");

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='employeeId']")));

        Thread.sleep(4000);
        WebElement elementNext=driver.findElement(By.xpath("//button[text()='Next']"));

        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", elementNext);
        js.executeScript("arguments[0].click();", elementNext);
        System.out.println("next button clicked on the second page");

        driver.findElement(By.xpath("(//i[text()='arrow_drop_down'][@class='material-icons'])[7]")
        ).click();
        System.out.println("Region scroll button is clicked");
        driver.findElement(By.xpath("//a[@id='bs-select-8-1']")).click();
        System.out.println("region-1 is selected in region drop-down");
        driver.findElement(By.xpath("(//i[text()='arrow_drop_down'][@class='material-icons'])[8]")
        ).click();
        System.out.println("FTE drop-down is clicked ");
        driver.findElement(By.xpath("//a[@id='bs-select-9-3']")).click();
        System.out.println("1 is selected as FTE");
        driver.findElement(By.xpath("(//i[text()='arrow_drop_down'][@class='material-icons'])[9]")
        ).click();
        System.out.println("Temporary Department drop-down is clicked");
        driver.findElement(By.xpath("//a[@id='bs-select-10-3']")).click();
        System.out.println("Sub unit-3 is clicked on Temporary Department ");
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        System.out.println("save button is clicked");
        //driver.findElement(By.xpath("(//button[text()='Save'])[1]")).click();
        System.out.println("Click action is performed on save button");
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()='Save'])[1]")));
        driver.findElement(By.xpath("//form[@id='pimPersonalDetailsForm']//button[text()='Save']")).click();

        driver.get("https://testcatalogue-trials7501.orangehrmlive.com/");
        System.out.println("Loading the dashboard");
        //driver.findElement(By.xpath("//span[text()='PIM']")).click();
        driver.findElement(By.linkText("Employee List")).click();
        System.out.println("Click action is performed on PIM");
        //driver.findElement(By.xpath("//span[text()='Employee List']")).click();
        //System.out.println("Click action is performed on Employee list");
        //driver.findElement(By.xpath("//a[@id='menu_pim_viewEmployeeList']")).click();
        /*  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Employee List']")));
        //driver.findElement(By.xpath("//span[text()='Employee List']")).click();
        js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//span[text()='Employee List']")));
        System.out.println("Click action is performed on Employee List"); */
        driver.findElement(By.xpath("//input[@id='employee_name_quick_filter_employee_list_value']")
        ).sendKeys(firstname+" "+lastname);
        System.out.println("Test data1 is Entered in the Search box");
        driver.findElement(By.xpath("//i[text()='ohrm_search']")).click();
        System.out.println("click action is performed on Search button");
        String Empname=driver.findElement(By.xpath("//td[text()='Dani  Daniel ']")).getText();
        System.out.println(Empname + " : Is the Given Employee name");
        //driver.findElement(By.xpath("//i[text()='keyboard_arrow_down']")).click();
        //System.out.println("Click action is performed on keyboard_arrow_down for logout");
        fis.close();
        //driver.findElement(By.xpath("//span[text()='Log Out']")).click();
        //System.out.println("Click action is performed on Logout button");

    }
}
