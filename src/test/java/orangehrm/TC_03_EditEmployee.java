package orangehrm;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.OrangeHRM_HomePage;
import pages.OrangeHRM_LoginPage;
import utilities.BaseClass;
import utilities.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class TC_03_EditEmployee {
    static WebDriver driver;
    public static String browserName;
    public static String yamlPath;
    public static String sExcelPath;
    public static int iRowNumber;
    public static String screenshotPath;
    public static String testcaseName;
    public OrangeHRM_LoginPage orangeHRM_loginPage;
    private OrangeHRM_HomePage orangeHRM_homePage;

    @Parameters("testId")
    @BeforeClass
    public void beforeEditEmployee(@Optional(Constant.testId) String testId) throws Exception{
        testcaseName=Thread.currentThread().getStackTrace()[1].getClassName().substring(Thread.currentThread().getStackTrace()[1].getClassName().indexOf('.')+1);
        System.out.println("TestId for the EditEmployee testcase is :"+testId);
        String path=System.getProperty("user.dir");
        System.out.println("Project Path is :"+path);
        screenshotPath = Utils.createScreenshotsFolder(path);
        yamlPath = path+"\\src\\main\\resources\\Config.yaml";
        browserName = YamlUtils.getYamlData(yamlPath,"browser");
        driver= Utils.launchBrowser(browserName);
        orangeHRM_loginPage = new OrangeHRM_LoginPage(driver);
        orangeHRM_homePage = new OrangeHRM_HomePage(driver);
        new BaseClass(driver);
        String url = YamlUtils.getYamlData(yamlPath,"orangeHRMURL");
        DriverUtils.loadURL(url);
        sExcelPath = path+"\\src\\main\\resources\\TestData.xlsx";
        ExcelUtils.setExcelFile(path+"\\src\\main\\resources\\TestData.xlsx");
        iRowNumber = ExcelUtils.getRowNumber(testId, "EditEmployee");
        System.out.println("Row Number for Edit Employee Testcase from Excel Sheet is :"+iRowNumber);
    }
    @Test
    public  void editEmployee() throws Exception {

        orangeHRM_loginPage.login_OrangeHRM(iRowNumber);
        Utils.captureScreenshot(screenshotPath,testcaseName+"_1_OrangeHRMLogin");

        orangeHRM_homePage.verifyLogin();
        Utils.captureScreenshot(screenshotPath,testcaseName+"_2_OrangeHRMHomePage");

        //EmployeePage
        driver.findElement(By.linkText("Employee List")).click();
        System.out.println("click action performed on Employee List link");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='employeeListTable']//tr[1]/td[2]")));
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
        ExcelUtils.setCellData(employeeId,iRowNumber,Constant.sEditEmployee_EmployeeId,"EditEmployee",sExcelPath);
        System.out.println(employeeId+"Is written back to the Excel sheet");
        Utils.captureScreenshot(screenshotPath,testcaseName+"_EmployeePage");

        driver.findElement(By.xpath("//table[@id='employeeListTable']//td[text()='0141']//following-sibling::td[1]")).click();
        System.out.println("Clicked on Employee Name of the Employee Id :"+employeeId);

        driver.findElement(By.xpath("//input[@id='lastName']")).clear();
        System.out.println("clear action is performed on Last name field");

        Faker datafaker = new Faker();
        String newLastname = datafaker.name().lastName();
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(newLastname);
        System.out.println(newLastname + " is entered as new Last name");
        ExcelUtils.setCellData(newLastname,iRowNumber,Constant.sEditEmployee_NewLastName,"EditEmployee",sExcelPath);
        System.out.println(newLastname+" is written back to the EditEmployee sheet");

        //Updating Marital Status if Marital Status is Single
        String maritalStatus = driver.findElement(By.xpath("//div[@id='emp_marital_status_inputfileddiv']/div/input")).getAttribute("value");
        if (!maritalStatus.equals("married")) {
            driver.findElement(By.xpath("//div[@id='emp_marital_status_inputfileddiv']/div/input")).click();
            System.out.println("Marital Status drop-down is clicked");

            driver.findElement(By.xpath("//div[@id='emp_marital_status_inputfileddiv']/div/ul/li[2]/span")).click();
            System.out.println("Updated Marital Status to Married");
        }

        //Updating Nationality
        driver.findElement(By.xpath("//div[@id='nation_code_inputfileddiv']/div/input")).click();
        System.out.println("Nationality drop-down is clicked");
        String expectedNationality = ExcelUtils.getCellData(iRowNumber,Constant.sEditEmployee_Nationality,"EditEmployee");
        System.out.println(expectedNationality+"is selected from the EditEmployee sheer");
        By locator_Nationalities=By.xpath("//div[@id='nation_code_inputfileddiv']/div/ul/li/span");
        Utils.selectDropdown_withoutSelectTag(locator_Nationalities,expectedNationality);

        driver.findElement(By.xpath("(//button[text()='Save'])[1]")).click();
        System.out.println("Click action is performed on save button");

        //Click on Job

        driver.findElement(By.xpath("//a[@ui-sref='pim.employees.profile.job']")).click();
        System.out.println("Click action performed on Job tab ");
        Utils.captureScreenshot(screenshotPath,testcaseName+"_JobPage");

        //Update the Location information
        driver.findElement(By.xpath("//label[@for='location_id']/../div/button")).click();
        System.out.println("Click action performed on Location drop down");
        String expectedLocation=ExcelUtils.getCellData(iRowNumber,Constant.sEditEmployee_Location,"EditEmployee");
        System.out.println(expectedLocation+" is selected from the EditEmployee sheet");
        By locator_locations=By.xpath("//div[@class='dropdown-menu show']/div/ul/li/a/span");
        Utils.selectDropdown_withoutSelectTag(locator_locations,expectedLocation);

        driver.findElement(By.xpath("//a[text()='Save']")).click();
        System.out.println("Save button is clicked in the Jobs page");

        //Updating the Event information
        driver.findElement(By.xpath("//label[text()='Event']//following-sibling::div[1]/button")).click();
        System.out.println("Event drop-down is clicked");
        String expectedEvent=ExcelUtils.getCellData(iRowNumber,Constant.sEditEmployee_JobEventInformation,"EditEmployee");
        System.out.println(expectedEvent+"is selected from the EditEmployee sheet");
        By locator_events=By.xpath("//label[text()='Event']//following-sibling::div[1]/div/div/ul/li/a/span");
        Utils.selectDropdown_withoutSelectTag(locator_events,expectedEvent);

        driver.findElement(By.xpath("//button[@id='modal-save-button']")).click();
        System.out.println("Confirm button is clicked on Employment Details-Confirm changes window");

        //Click on Salary
        driver.findElement(By.xpath("//a[@ui-sref='pim.employees.profile.salary']")).click();
        System.out.println("Click action performed on Salary tab");
        Utils.captureScreenshot(screenshotPath,testcaseName+"_SalaryPage");

        //Read the Data for Cost to Company
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='summary-card-column summary-card-left'])[1]")));
        String CostToCompany=driver.findElement(By.xpath("(//div[@class='summary-card-column summary-card-right'])[1]")).getText();
        System.out.println(CostToCompany + " : is Cost to Company");

        //Change the Cost of Living Allowance to 9000.00
        String livingAllowance=ExcelUtils.getCellData(iRowNumber,Constant.sEditEmployee_LivingAllowance,"EditEmployee");
        System.out.println(livingAllowance+"is selected from the EditEmployee sheet");
        driver.findElement(By.xpath("//span[text()='Cost of Living Allowance']/../../td[2]//input")).clear();
        driver.findElement(By.xpath("//span[text()='Cost of Living Allowance']/../../td[2]//input")).sendKeys(livingAllowance);
        System.out.println("Updated the Cost of Living Allowance "+livingAllowance);

        //And Validate the GrossPay  --> It should be sum of Annual Basic Pay + Car Allowance + Cost of Living Allowance + Monthly
        String annualBasicPay=driver.findElement(By.xpath("//span[text()='Annual Basic Payment']/../../td[3]/span")).getText();
        double dAnnualBasicPay=0.00;
        annualBasicPay=annualBasicPay.replaceAll(",","");
        if(annualBasicPay.isEmpty()){
            annualBasicPay ="5000.00";
        }
        try{
            dAnnualBasicPay=Double.parseDouble(annualBasicPay);
        }catch(NumberFormatException ne){
            annualBasicPay=annualBasicPay.substring(1);
            dAnnualBasicPay=Double.parseDouble(annualBasicPay);
        }
        System.out.println("Annual Basic pay value is :"+dAnnualBasicPay);

        String carAllowance=driver.findElement(By.xpath("//span[text()='Car Allowance']/../../td[3]/span")).getText();
        double dCarAllowance=0.00;
        if(!carAllowance.isEmpty()){
            carAllowance=carAllowance.replaceAll(",","");
            try{
                dCarAllowance=Double.parseDouble(carAllowance);
            }catch(NumberFormatException ne){
                carAllowance=carAllowance.substring(1);
                dCarAllowance=Double.parseDouble(carAllowance);
            }
        }
        System.out.println("Car Allowance value is :"+dCarAllowance);

        String costOfLivingAllowance=driver.findElement(By.xpath("//span[text()='Cost of Living Allowance']/../../td[3]/span")).getText();
        costOfLivingAllowance=costOfLivingAllowance.replaceAll(",","");
        double dCostOfLivingAllowance=0.00;
        try{
            dCostOfLivingAllowance=Double.parseDouble(costOfLivingAllowance);
        }catch(NumberFormatException ne){
            costOfLivingAllowance=costOfLivingAllowance.substring(1);
            dCostOfLivingAllowance=Double.parseDouble(costOfLivingAllowance);
        }
        System.out.println("Cost of Living Allowance value is :"+dCostOfLivingAllowance);

        String monthly=driver.findElement(By.xpath("//span[text()='Monthly']/../../td[3]/span")).getText();
        double dMonthly=0.00;
        if(!monthly.isEmpty()){
            monthly=monthly.replaceAll(",","");
            try{
                dMonthly=Double.parseDouble(monthly);
            }catch(NumberFormatException ne){
                monthly=monthly.substring(1);
                dMonthly=Double.parseDouble(monthly);
            }
        }
        System.out.println("Monthly value is :"+dMonthly);

        //Print the GrossPay
        String grossPay=driver.findElement(By.xpath("//span[text()='Gross Pay']/../..//following-sibling::td")).getText();
        grossPay=grossPay.replaceAll(",","");
        double dGrossPay=0.00;
        try{
            dGrossPay=Double.parseDouble(grossPay);
        }catch(NumberFormatException ne){
            grossPay=grossPay.substring(1);
            dGrossPay=Double.parseDouble(grossPay);
        }

        double actualGrossPay=dAnnualBasicPay+dCarAllowance+dCostOfLivingAllowance+dMonthly;
        if(actualGrossPay==dGrossPay){
            System.out.println("Gross Pay is updated correctly");
        }else{
            System.out.println("Gross Pay is not updated correctly");
            System.out.println("Actual Gross Pay is :"+actualGrossPay+" but the expected Gross Pay is :"+dGrossPay);
            throw new Exception();
        }

        //Change the EPF to 9%
        String EPF=ExcelUtils.getCellData(iRowNumber,Constant.sEditEmployee_EPF,"EditEmployee");
        System.out.println(EPF+"is selected from the EditEmployee sheet");
        driver.findElement(By.xpath("//span[text()='EPF']/../../td[2]//div/input")).clear();
        driver.findElement(By.xpath("//span[text()='EPF']/../../td[2]//div/input")).sendKeys("9.00");
        System.out.println("EPF updated to "+EPF);


        //Validate the EPF value --> It should be Annual Basic Pay * EPF%
        String epf=driver.findElement(By.xpath("//span[text()='EPF']/../../td[3]/span")).getText();
        epf=epf.replaceAll(",","");
        double dEPF=0.00;
        try{
            dEPF=Double.parseDouble(epf);
        }catch(NumberFormatException ne){
            epf=epf.substring(1);
            dEPF=Double.parseDouble(epf);
        }
        double actualEPF=(dAnnualBasicPay*0.09);
        if(actualEPF==dEPF){
            System.out.println("EPF updated correctly");
        }else{
            System.out.println("EPT not updated correctly");
            throw new Exception();
        }

        //Add validate the Total Deductions --> It should be sum of EPF + Pension Fund
        String pensionFund=driver.findElement(By.xpath("//span[text()='Pension Fund']/../../td[3]/span")).getText();
        double dPensionFund=0.00;
        if(!pensionFund.isEmpty()){
            pensionFund=pensionFund.replaceAll(",","");
            try{
                dPensionFund=Double.parseDouble(pensionFund);
            }catch(NumberFormatException ne){
                pensionFund=pensionFund.substring(1);
                dPensionFund=Double.parseDouble(pensionFund);
            }
        }
        System.out.println("Pension Fund Value is :"+dPensionFund);

        double actualTotalDeductions=dEPF+dPensionFund;
        String totalDeductions=driver.findElement(By.xpath("//span[text()='Total Deductions']/../..//following-sibling::td[1]")).getText();
        totalDeductions=totalDeductions.replaceAll(",","");
        double dTotalDeductions=0.00;
        try{
            dTotalDeductions=Double.parseDouble(totalDeductions);
        }catch(NumberFormatException ne){
            totalDeductions=totalDeductions.substring(1);
            dTotalDeductions=Double.parseDouble(totalDeductions);
        }
        if(dTotalDeductions==actualTotalDeductions){
            System.out.println("Total Deductions updated correctly");
        }else{
            System.out.println("Total Deductions not updated correctly");
            throw new Exception();
        }

        //Click on Save button
        Utils.captureScreenshot(screenshotPath,testcaseName+"_UpdatedsalaryPage");
        driver.findElement(By.xpath("//a[text()='Save']")).click();
        System.out.println("Click action is performed on Save button");

        //In Salary Confirm Changes window, change the Event to Promoted
        driver.findElement(By.xpath("//label[text()='Event']//following-sibling::div[1]/button")).click();
        System.out.println("Event drop-down is clicked");

        String expectedSalaryEvent=ExcelUtils.getCellData(iRowNumber,Constant.sEditEmployee_SalaryEventInformation,"EditEmployee");
        System.out.println(expectedSalaryEvent+"is selected from the EditEmployee sheet");
        By locator_Events=By.xpath("//label[text()='Event']//following-sibling::div[1]/div/div/ul/li/a/span");
        Utils.selectDropdown_withoutSelectTag(locator_Events,expectedSalaryEvent);

        driver.findElement(By.xpath("//button[@id='modal-save-button']")).click();
        System.out.println("Confirm button is clicked on Employment Details-Confirm changes window");

        //Click on Emergency Contacts
        Utils.captureScreenshot(screenshotPath,testcaseName+"_contactsPage");
        driver.findElement(By.xpath("//a[text()='More ']")).click();
        System.out.println("Clicked on More drop down");
        driver.findElement(By.xpath("//a[contains(text(),'Contact Details')]")).click();
        System.out.println("Cliked on Contact Details in the More drop down");
        //Enter the MobileNumber
        String mobileNumer=ExcelUtils.getCellData(iRowNumber,Constant.sEditEmployee_MobileNumber,"EditEmployee");
        System.out.println(mobileNumer+"is selected from the EditEmployee sheet");
        driver.findElement(By.xpath("//input[@id='emp_mobile']")).clear();
        driver.findElement(By.xpath("//input[@id='emp_mobile']")).sendKeys(mobileNumer);
        System.out.println("Mobile number changed ");
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        System.out.println("Clicked on save button");

        //Click on Home button
        driver.findElement(By.xpath("//a[@data-automation-id='menu_home']")).click();
        System.out.println("Home button is clicked");

        //Click on EmployeeList
        driver.findElement(By.linkText("Employee List")).click();
        System.out.println("click action performed on Employee List link");

        driver.navigate().to("https://automationcatalogue-trials76.orangehrmlive.com/client/#/pim/employees");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='employeeListTable']//tr[1]/td[2]")));

        //Click on Filter
        driver.findElement(By.xpath("//li[@data-tooltip='Filter']")).click();
        System.out.println("Filter link is clicked");

        //Search with EmployeeId
        driver.findElement(By.xpath("//input[@id='emp_search_mdl_employee_id_filter']")).sendKeys(employeeId);
        System.out.println("Filtering with Employee ID "+employeeId);

        driver.findElement(By.xpath("//a[text()='Search']")).click();
        System.out.println("Click action is performed on Search button");


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
                employeeId=driver.findElement(By.xpath("//table[@id='employeeListTable']//tbody/tr[1]/td[2]")).getText();
                System.out.println("EmployeeId is :"+employeeId);
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


        //Verify Updated EmployeeName
        String actualEmployeeName=driver.findElement(By.xpath("//table[@id='employeeListTable']/tbody/tr[1]/td[3]")).getText();
        if(actualEmployeeName.contains(newLastname)){
            System.out.println("Employee Name updated correctly");
        }else{
            System.out.println("Employee Name not updated correctly");
            throw new Exception();
        }

        //Verify Updated Location
        String actualLocation=driver.findElement(By.xpath("//table[@id='employeeListTable']/tbody/tr[1]/td[8]")).getText();
        if(actualLocation.equalsIgnoreCase(expectedLocation)){
            System.out.println("Location is updated correctly");
        }else{
            System.out.println("Location is not updated correctly");
            throw new Exception();
        }

        System.out.println("Edit Employee is successful");

        orangeHRM_loginPage.logout_orangeHRM();

        DriverUtils.closeBrowser();

    }

}
