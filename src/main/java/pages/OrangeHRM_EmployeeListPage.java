package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseClass;
import utilities.Constant;
import utilities.ExcelUtils;

import java.time.Duration;
import java.util.function.Function;

public class OrangeHRM_EmployeeListPage extends BaseClass {
    WebDriver driver;

    public OrangeHRM_EmployeeListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickAddEmployee(){
        driver.findElement(By.linkText("Employee List")).click();
        System.out.println("EmployeeList link is clicked");
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[text()='add']")));
        driver.findElement(By.xpath("//i[text()='add']")).click();
        System.out.println("click action performed on Add Employee");
    }

    public void verifyAddEmployeeData(int iRowNumber, String sExcelPath) throws Exception{
        WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(By.linkText("Employee Management")).click();
        System.out.println("Employee Management link is clicked");
        driver.findElement(By.linkText("Employee List")).click();
        System.out.println("Click action is performed on Employee List");

        driver.navigate().to("https://automationcatalogue-trials76.orangehrmlive.com/client/#/pim/employees");

        //Add Employee verification
        String completeName=OrangeHRM_AddEmployeePage.firstName+" "+OrangeHRM_AddEmployeePage.lastName;
        driver.findElement(By.xpath("//input[@id='employee_name_quick_filter_employee_list_value']")).sendKeys(completeName);
        System.out.println(completeName+" is entered as CompleteName in the Search box");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='employee_name_quick_filter_employee_list_dropdown']//div[3]/span[1]")));

        driver.findElement(By.xpath("//div[@id='employee_name_quick_filter_employee_list_dropdown']//div[3]/span[1]")).click();
        System.out.println("First result is selected from a result drop-down");

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
                ExcelUtils.setCellData(employeeId,iRowNumber, Constant.sEmployeeId_AddEmployee,"AddEmployee",sExcelPath);
                System.out.println("EmployeeId written back to the Excel sheet is:"+employeeId);

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
        String explocation=ExcelUtils.getCellData(iRowNumber,Constant.sLocation,"AddEmployee");
        String actualLocation=driver.findElement(By.xpath("//table[@id='employeeListTable']//tbody/tr[1]/td[8]")).getText();
        if(explocation.equalsIgnoreCase(actualLocation)){
            System.out.println("Location verification is successful");
        }else {
            System.out.println("Location verification is not successful");
            throw new Exception();
        }

        System.out.println("Add Employee is successful");
    }
}
