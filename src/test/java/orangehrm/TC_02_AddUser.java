package orangehrm;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.BaseClass;
import utilities.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class TC_02_AddUser {
    static WebDriver driver;
    public static String browserName;
    public static String yamlPath;
    public static String sExcelPath;
    public static int iRowNumber;
    public static String screenshotPath;
    public static String testcaseName;

    @Parameters("testId")
    @BeforeClass
    public void beforeAddUser(@Optional(Constant.testId) String testId) throws Exception{
        testcaseName=Thread.currentThread().getStackTrace()[1].getClassName().substring(Thread.currentThread().getStackTrace()[1].getClassName().indexOf('.')+1);
        System.out.println("TestId for the AddUser testcase is :"+testId);
        String path=System.getProperty("user.dir");
        System.out.println("Project Path is :"+path);
        screenshotPath = Utils.createScreenshotsFolder(path);
        yamlPath = path+"\\src\\main\\resources\\Config.yaml";
        browserName = YamlUtils.getYamlData(yamlPath,"browser");
        driver= Utils.launchBrowser(browserName);
        new BaseClass(driver);
        String url = YamlUtils.getYamlData(yamlPath,"orangeHRMURL");
        DriverUtils.loadURL(url);
        sExcelPath = path+"\\src\\main\\resources\\TestData.xlsx";
        ExcelUtils.setExcelFile(path+"\\src\\main\\resources\\TestData.xlsx");
        iRowNumber = ExcelUtils.getRowNumber(testId, "AddUser");
    }
    @Test
    public void adduser() throws Exception {
        //OrangeHRM Login
        String sUserName = ExcelUtils.getCellData(iRowNumber, Constant.sUserName_OrangeHRM,"AddUser");
        System.out.println("UserName from the Excel Sheet is :"+sUserName);
        String sPassword = ExcelUtils.getCellData(iRowNumber, Constant.sUserPassword_OrangeHRM,"AddUser");
        System.out.println("Password from the Excel Sheet is :"+sPassword);
        CommonMethods_OrangeHRM.login_OrangeHRM(sUserName,sPassword);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_OrangeHRMLogin");
        System.out.println("OrangeHRM login Screenshot is captured for : "+testcaseName);

        //Login verification
        boolean isLoginSuccessful= driver.findElement(By.xpath("//i[@class='material-icons'][text()='oxd_home_menu']")).isDisplayed();
        if(isLoginSuccessful){
            System.out.println("Login is successful");
        }else{
            System.out.println("Login is not successful");
            throw new Exception();
        }

        driver.findElement(By.linkText("Employee List")).click();
        System.out.println("EmployeeList link is clicked");
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        try{
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[text()='add']")));
        }catch(TimeoutException e){
            driver.navigate().refresh();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[text()='add']")));
        }

        ArrayList<String> al_EmployeeIds = new ArrayList<String>();
        List<WebElement> elementList_EmployeeIds = driver.findElements(By.xpath("//table[@id='employeeListTable']/tbody/tr/td[2]"));
        for(WebElement element_EmployeeId:elementList_EmployeeIds){
            String employeeId = element_EmployeeId.getText();
            al_EmployeeIds.add(employeeId);
        }

        Random random = new Random();
        int randomNumber;
        while(true){
            randomNumber=random.nextInt(51);
            if(randomNumber==0){
                continue;
            }else{
                System.out.println("Randomly generated number is :"+randomNumber);
                break;
            }
        }
        String employeeId = al_EmployeeIds.get(randomNumber);
        System.out.println("Randomly selected employeeId is :"+employeeId);
        ExcelUtils.setCellData(employeeId, iRowNumber, Constant.sEmployeeId_AddUser, "AddUser", sExcelPath);
        System.out.println("EmployeeId is written back to the Excel sheet :"+employeeId);

        driver.findElement(By.xpath("(//span[text()='HR Administration'])[1]")).click();
        System.out.println("HR Administration link is clicked");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='systemUserDiv']//table//tbody//tr[1]")));

        driver.findElement(By.xpath("//i[text()='add']")).click();
        System.out.println(" + Add user icon clicked ");

        //Add User
        driver.findElement(By.xpath("//input[@id='selectedEmployee_value']")).sendKeys(employeeId);
        System.out.println("Employee ID is entered as EmployeeName");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='selectedEmployee_value']")).sendKeys(Keys.TAB);

        Faker datafaker = new Faker();
        String userName=datafaker.name().username();
        driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys(userName);
        ExcelUtils.setCellData(userName,iRowNumber,Constant.sAddUser_UserName,"AddUser",sExcelPath);
        System.out.println("User Name is entered");

        String sChangePassword = ExcelUtils.getCellData(iRowNumber, Constant.sAddUser_ChangePassword,"AddUser");
        System.out.println("Change Password from the Excel Sheet is :"+sChangePassword);
        driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys(sChangePassword);
        System.out.println(sChangePassword+" is Entered as Password in Password field");
        String sConfirmChangePassword = ExcelUtils.getCellData(iRowNumber, Constant.sAddUser_ConfirmChangePassword,"Adduser");
        System.out.println("Confirm Change Password from the Excel Sheet is :"+sChangePassword);
        driver.findElement(By.xpath("//input[@placeholder='Confirm Password']")).sendKeys(sConfirmChangePassword);
        System.out.println(sConfirmChangePassword+" is entered as Confirm Password");

        WebElement element_SaveBtn=driver.findElement(By.xpath("//button[@id='modal-save-button']"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);",element_SaveBtn);
        Thread.sleep(1000);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_AddUserPage");
        js.executeScript("arguments[0].click();",element_SaveBtn);
        System.out.println("clicked on save button to create User");

        Thread.sleep(4000);

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
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(sChangePassword);
        System.out.println("Admin@123 is entered as password");

        driver.findElement(By.xpath("//button[text()='Login']")).click();
        System.out.println("Click action is performed on login button with new user");

        Utils.captureScreenshot(screenshotPath,testcaseName+"_newUserLoginPage");

        String actualEmployeeName=driver.findElement(By.xpath("//div[@id='sidebar-profile-picture']/a")).getText();
        System.out.println(actualEmployeeName+ " is logged into the application");

        if(actualEmployeeName.equalsIgnoreCase(employeeName)){
            System.out.println("Add User Verification is successful");
        }else{
            System.out.println("Add User Verification is not successful");
            throw new Exception();
        }

        CommonMethods_OrangeHRM.logout_orangeHRM();

        DriverUtils.closeBrowser();

    }
}
