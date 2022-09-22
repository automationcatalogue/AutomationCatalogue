package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.*;

import java.time.Duration;
import java.util.List;

public class OrangeHRM_AddEmployeePage extends BaseClass {
    WebDriver driver;
    public static String firstName;
    public static String lastName;

    public OrangeHRM_AddEmployeePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void addEmployee(int iRowNumber, String sExcelPath, String screenshotPath, String testcaseName) throws Exception{
        //firstName
        WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(20));
        firstName = RandomGenerator.getRandomData("firstName");
        driver.findElement(By.xpath("//input[@id='first-name-box']")).sendKeys(firstName);
        System.out.println("FirstName is entered as "+firstName);
        ExcelUtils.setCellData(firstName,iRowNumber, Constant.sFirstName,"AddEmployee",sExcelPath);
        System.out.println(firstName+" as FirstName is written back to the Excel sheet");

        //LastName
        lastName = RandomGenerator.getRandomData("lastName");
        driver.findElement(By.xpath("//input[@id='last-name-box']")).sendKeys(lastName);
        System.out.println("LastName is entered as "+lastName);
        ExcelUtils.setCellData(lastName,iRowNumber,Constant.sLastName, "AddEmployee", sExcelPath);
        System.out.println(lastName+" as LastName is written back to the Excel sheet");

        driver.findElement(By.xpath("//i[text()='arrow_drop_down']")).click();
        System.out.println("Drop-down for location is clicked");
        String explocation=ExcelUtils.getCellData(iRowNumber,Constant.sLocation,"AddEmployee");
        System.out.println("Location from the Excel sheet is :"+explocation);
        By locator_Locations = By.xpath("//label[text()='Location']//following-sibling::div//ul/li/a/span");
        Utils.selectDropdown_withoutSelectTag(locator_Locations, explocation);

        Utils.captureScreenshot(screenshotPath,testcaseName+"_EmployeePage");
        driver.findElement(By.xpath("//button[@id='modal-save-button']")).click();
        System.out.println("click action performed on next button");

        //Personal Details
        String dob=ExcelUtils.getCellData(iRowNumber, Constant.sDOB,"AddEmployee");
        System.out.println("DOB from the Excel sheet is :"+dob);
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
        Utils.captureScreenshot(screenshotPath,testcaseName+"_PersonalDetailsPage");
        System.out.println("Next button clicked in the Personal Details page");

        //Employment Details page
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//label[text()='Work Shift']"))));
        WebElement element_RegionDropDown=driver.findElement(By.xpath("//label[text()='Region']//following-sibling::div[1]/button/div/div/div"));
        js.executeScript("arguments[0].scrollIntoView(true);",element_RegionDropDown);
        element_RegionDropDown.click();
        System.out.println("Region drop-down is clicked");
        String expectedRegion=ExcelUtils.getCellData(iRowNumber,Constant.sRegion,"AddEmployee");
        System.out.println("Region from the Excel sheet is :"+expectedRegion);
        By locator_Regions = By.xpath("//label[text()='Region']//following-sibling::div[1]/div//ul/li/a/span");
        Utils.selectDropdown_withoutSelectTag(locator_Regions, expectedRegion);

        WebElement element_FTEDropDown=driver.findElement(By.xpath("//label[text()='FTE']//following-sibling::div[1]/button/div/div/div"));
        element_FTEDropDown.click();
        System.out.println("FTE drop-down is clicked");
        String expectedFTE=ExcelUtils.getCellData(iRowNumber, Constant.sFTE, "AddEmployee");
        By locator_ftes = By.xpath("//label[text()='FTE']//following-sibling::div[1]/div//ul/li/a/span");
        Utils.selectDropdown_withoutSelectTag(locator_ftes, expectedFTE);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Temporary Department']//following-sibling::div[1]/button/div/div/div")));
        WebElement element_TemporaryDepartmentDropDown=driver.findElement(By.xpath("//label[text()='Temporary Department']//following-sibling::div[1]/button/div/div/div"));
        element_TemporaryDepartmentDropDown.click();
        System.out.println("Temporary Department drop-down is clicked");
        String expectedTemporaryDepartment=ExcelUtils.getCellData(iRowNumber, Constant.sTemporaryDepartment,"AddEmployee");
        System.out.println("Temporary Department from the Excel sheet is :"+expectedTemporaryDepartment);
        By locator_TemporaryDepartments = By.xpath("//label[text()='Temporary Department']//following-sibling::div[1]/div//ul/li/a/span");
        Utils.selectDropdown_withoutSelectTag(locator_TemporaryDepartments, expectedTemporaryDepartment);

        //PersonalDetails - last page
        Utils.captureScreenshot(screenshotPath,testcaseName+"_updatedPersonalDetails");
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        System.out.println("save button is clicked");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='pimPersonalDetailsForm']//button[text()='Save']")));
        driver.findElement(By.xpath("//form[@id='pimPersonalDetailsForm']//button[text()='Save']")).click();
        System.out.println("Click action is performed on save button in Personal Details of last page");
    }
}
