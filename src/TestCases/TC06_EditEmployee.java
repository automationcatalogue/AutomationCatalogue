package TestCases;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;

public class TC06_EditEmployee {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Anitha\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver_win32_1\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String path = System.getProperty("user.dir");
        System.out.println(path);
        FileInputStream fis = new FileInputStream(path + "\\TestData\\AddEmployee.xlsx");
        XSSFWorkbook wbk = new XSSFWorkbook(fis);
        XSSFSheet sh = wbk.getSheet("EmpDetails");
        XSSFRow row = sh.getRow(1);
        int rowCount = sh.getLastRowNum();
        System.out.println("total number of rows : " + rowCount);

        XSSFCell cell = row.getCell(1);
        String username = cell.getStringCellValue();
        System.out.println("UserName from xl file is : " + username);
        cell = row.getCell(2);
        String password = cell.getStringCellValue();
        System.out.println("Password from xl file is : " + password);
        cell = row.getCell(3);
        String firstname = cell.getStringCellValue();
        System.out.println("firstname from xl file is : " + firstname);
        cell = row.getCell(4);
        String lastname = cell.getStringCellValue();
        System.out.println("lastname from xl file is : " + lastname);
        cell = row.getCell(5);
        String location = cell.getStringCellValue();
        System.out.println("location from xl file is : " + location);
        cell = row.getCell(8);
        String empUserName = cell.getStringCellValue();
        System.out.println("Given emp username is : " + empUserName);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println("Implicit timeout added for 20 seconds");
        driver.manage().window().maximize();
        driver.get("https://testcatalogue-trials7501.orangehrmlive.com/");
        System.out.println("OrangeHRM url loaded");
        String title = driver.getTitle();
        System.out.println("Title of the web page is : " + title);

        driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
        System.out.println("Admin is entered as username");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
        System.out.println("Admin@123 is Entered as password");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("Login button is clicked");
        //driver.findElement(By.xpath("//span[text()='PIM']")).click();
        driver.findElement(By.linkText("Employee List")).click();
        System.out.println("click action performed on PIM link");
        //driver.findElement(By.xpath("//span[text()='Employee List']")).click();
        System.out.println("Click action is performed on EmployeeList link");

        By employeeListEle = By.xpath("//table[@id='employeeListTable']//td[2][text()='1061']");
        System.out.println("Explicit wait of 10s is applied with condition PresenceOfElementLocated");
        waitForElement(driver, employeeListEle, 10).click();
        //driver.findElement(By.xpath("//table[@id='employeeListTable']//td[2][text()='1061']")).click();
        System.out.println("click action is performed on first employee in the list");

        driver.findElement(By.xpath("//input[@id='lastName']")).clear();
        System.out.println("clear action is performed on Last name field");
        String newLastname = "Balwinabc";
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(newLastname);
        System.out.println("Balwina is entered as new Last name");
        driver.findElement(By.xpath("(//i[text()='date_range'])[1]")).click();
        System.out.println("Click action is performed on The Date-range for DOB");
        driver.findElement(By.xpath("(//button[text()='Clear'])[1]")).click();
        System.out.println("Click action is performed on Clear Button on date-range");
        driver.findElement(By.xpath("(//i[text()='date_range'])[1]")).click();
        System.out.println("Click action is performed on The Date-range for DOB");


        driver.findElement(By.xpath("//div[@class='select-wrapper picker__select--year']")).click();
        System.out.println("Click action is performed Year DropDown");

        String yearTxt = "1990";
        List<WebElement> yearElementsOfDOB = driver.findElements(By.xpath("//div[@class='select-wrapper picker__select--year']/ul/li"));
        for (WebElement yearEle : yearElementsOfDOB) {
            String resultYearTxt = yearEle.getText();
            if (resultYearTxt.equalsIgnoreCase(yearTxt)) {
                yearEle.click();
                break;
            }
        }
        System.out.println("Click action is performed on year 1990 is selected as Year ");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.findElement(By.xpath("//div[@class='select-wrapper picker__select--month']")).click();
        System.out.println("Click action is performed on Month Drop Down");
        Thread.sleep(2000);
        String monthTxt = "January";
        List<WebElement> monthEleDOB = driver.findElements(By.xpath("//div[@class='select-wrapper picker__select--month']/ul/li/span"));
        for (WebElement monthEle : monthEleDOB) {
            String resultMonthTXT = monthEle.getText();
            if (resultMonthTXT.equalsIgnoreCase(monthTxt)) {
                //monthEle.click();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                //wait.until(ExpectedConditions.elementToBeClickable(monthEle));

                js.executeScript("arguments[0].click();", monthEle);
                break;
            }
        }
        System.out.println("Click action is Performed and Month selected as January");

        driver.findElement(By.xpath("(//table[@class='picker__table'])[1]/tbody//tr//td//div[contains(text(),'23')]")).click();
        System.out.println("Click action is performed on day picker and 23 is selected as day");
        //#nation_code_inputfileddiv div
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='nation_code_inputfileddiv']/div/input")).click();
        System.out.println("Click action is performed on nationality drop down");
        List<WebElement> natinalityElements = driver.findElements(By.xpath("//div[@id='nation_code_inputfileddiv']/div/ul/li"));
        for (WebElement nationalEle : natinalityElements) {
            String SelectEle = nationalEle.getText();
            if (SelectEle.equalsIgnoreCase("Indian")) {
                nationalEle.click();
                break;
            }
        }
        System.out.println("Indian is selected as nationality");

        driver.findElement(By.xpath("(//button[text()='Save'])[1]")).click();
        System.out.println("Click action is performed on save button");
        driver.findElement(By.xpath("(//button[text()='Save'])[2]")).click();
        System.out.println("Click action is performed on save button");

        driver.findElement(By.xpath("//i[text()='oxd_home_menu']")).click();
        System.out.println("Click Action is performed on Home button");
        driver.findElement(By.linkText("Employee List")).click();
        driver.findElement(By.xpath("//input[@id='employee_name_quick_filter_employee_list_value']")).sendKeys(newLastname);
        driver.findElement(By.xpath("//input[@id='employee_name_quick_filter_employee_list_value']")).sendKeys(Keys.TAB);

        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(path + "\\screenshots\\_EditEmployee_Timestamp.png");
        FileUtils.copyFile(src, destination);
        System.out.println("Screenshot captured for : _EditEmployee_Timestamp.png");
    }

    public static WebElement waitForElement(WebDriver driver, By locator, int timeout) {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        //WebElement waitElement = wait1.until(ExpectedConditions.presenceOfElementLocated(locator));
        //return waitElement;
        return  null;
    }


   /* public static WebElement waitForElement1(WebDriver driver, int timeout, Function function) {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement waitElement = wait1.until(ExpectedConditions.elementToBeClickable(locator));
        return waitElement;
    } */
}
