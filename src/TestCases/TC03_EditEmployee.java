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

public class TC03_EditEmployee {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
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

        driver.findElement(By.linkText("Employee List")).click();
        System.out.println("click action performed on Employee List link");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='employeeListTable']//tr[1]/td[2]")));

        driver.findElement(By.xpath("//table[@id='employeeListTable']//tr[1]/td[2]")).click();
        System.out.println("Clicked on First Employee");

        driver.findElement(By.xpath("//input[@id='lastName']")).clear();
        System.out.println("clear action is performed on Last name field");
        String newLastname = "testa";
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(newLastname);
        System.out.println(newLastname + " is entered as new Last name");

        //Updating Marital Status if Marital Status is Single
        String maritalStatus = driver.findElement(By.xpath("//div[@id='emp_marital_status_inputfileddiv']/div/input")).getAttribute("value");
        if (!maritalStatus.equals("married")) {
            driver.findElement(By.xpath("//div[@id='emp_marital_status_inputfileddiv']/div/input")).click();
            System.out.println("Marital Status drop-down is clicked");

            driver.findElement(By.xpath("//div[@id='emp_marital_status_inputfileddiv']/div/ul/li[2]/span")).click();
            System.out.println("Updated Marital Status to Married");
        }

        //Updating Nationality
        String expectedNationality = "Spanish";
        driver.findElement(By.xpath("//div[@id='nation_code_inputfileddiv']/div/input")).click();
        System.out.println("Nationality drop-down is clicked");
        List<WebElement> elements_Nationalities = driver.findElements(By.xpath("//div[@id='nation_code_inputfileddiv']/div/ul/li/span"));

        for (WebElement ele_nationality : elements_Nationalities) {
            String actualNationality = ele_nationality.getText();
            if (actualNationality.equals(expectedNationality)) {
                ele_nationality.click();
                System.out.println(actualNationality + " is selected for the Nationality drop-down");
            }
        }

        driver.findElement(By.xpath("(//button[text()='Save'])[1]")).click();
        System.out.println("Click action is performed on save button");

        //Click on Job
        //Update the Location information

        //Click on Salary
        //Read the Data for Cost to Company
        //Change the Cost of Living Allowance to 9000.00
        //And Validate the GrossPay  --> It should be sum of Annual Basic Pay + Car Allowance + Cost of Living Allowance + Monthly
        //Print the GrossPay

        //Change the EPF to 9%
        //Validate the EPF value --> It should be Annual Basic Pay * EPF%
        //Add validate the Total Deductions --> It should be sum of EPF + Pension Fund
        //Click on Save button

        //In Salary Confirm Changes window, change the Event to Promoted
        //Click on Confirm button

        //Click on Emergency Contacts
        //Click on +Add button
        //Click Name and Enter some Random Data
        //Enter some relationship
        //Enter the MobileNumber
        //Click on Save

        //Click on Home button
        //Click on EmployeeList
        //Click on Filter
        //Search with EmployeeId
        //Verify Updated EmployeeName
        //Verify Updated Location

        //Logout
        //close the browser







    }

}
