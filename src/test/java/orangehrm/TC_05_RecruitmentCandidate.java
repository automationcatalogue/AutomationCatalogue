package orangehrm;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.BaseClass;
import utilities.*;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class TC_05_RecruitmentCandidate {
    static WebDriver driver;
    public static String browserName;
    public static String yamlPath;
    public static String sExcelPath;
    public static int iRowNumber;
    @BeforeClass
    public void beforeRecruitmentCandidate() throws Exception{
        String path=System.getProperty("user.dir");
        System.out.println("Project Path is :"+path);

        yamlPath = path+"\\src\\main\\resources\\Config.yaml";
        browserName = YamlUtils.getYamlData(yamlPath,"browser");

        driver= Utils.launchBrowser(browserName);
        new BaseClass(driver);

        String url = YamlUtils.getYamlData(yamlPath,"orangeHRMURL");
        DriverUtils.loadURL(url);
        String sTestId = YamlUtils.getYamlData(yamlPath,"TestId");
        sExcelPath = path+"\\src\\main\\resources\\TestData.xlsx";
        ExcelUtils.setExcelFile(path+"\\src\\main\\resources\\TestData.xlsx");
        iRowNumber = ExcelUtils.getRowNumber(sTestId, "AddUser");

    }
    @Test
    public void recruitmentCandidate() throws Exception{
        //OrangeHRM Login
        String sUserName = ExcelUtils.getCellData(iRowNumber, Constant.sUserName_OrangeHRM,"AddUserRecruitmentCandidate");
        System.out.println("UserName from the Excel Sheet is :"+sUserName);
        String sPassword = ExcelUtils.getCellData(iRowNumber, Constant.sUserPassword_OrangeHRM,"RecruitmentCandidate");
        System.out.println("Password from the Excel Sheet is :"+sPassword);
        CommonMethods_OrangeHRM.login_OrangeHRM(sUserName,sPassword);

        //Login verification
        boolean isLoginSuccessful = driver.findElement(By.xpath("//i[@class='material-icons'][text()='oxd_home_menu']")).isDisplayed();
        if (isLoginSuccessful) {
            System.out.println("Login is successful");
        } else {
            System.out.println("Login is not successful");
            throw new Exception();
        }

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));

        //Recruitment(ATS)
        driver.findElement(By.xpath("//a[@id='side-menu-more']/span")).click();
        System.out.println("More link is clicked");
        driver.findElement(By.xpath("(//span[text()='Recruitment (ATS)'])[1]")).click();
        System.out.println("Recuritment(ATS) link is clicked");

        //Addbutton
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("noncoreIframe"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='table-header-action-btns']")));
        driver.findElement(By.xpath("//div[@class='table-header-action-btns']")).click();
        System.out.println("Add button is clicked");

        //driver.findElement(By.xpath("//form[@id='frmAddCandidate']/div[1]/div[1]/div[1]")).click();
        System.out.println("SELECT Resume button is clicked");

        //driver.findElement(By.xpath("//form[@id='frmAddCandidate']/div[1]/div[1]/div[1]/input")).sendKeys(yamlPath+"\\TestData\\TestResume.docx");
        //driver.findElement(By.xpath("//form[@id='frmAddCandidate']/div[1]/div[1]/div[1]")).submit();
        System.out.println("Candidate Resume is uploaded");

        Faker datafaker=new Faker();
        String firstName=datafaker.name().firstName();
        ExcelUtils.setCellData(firstName,iRowNumber,Constant.sRecruitmentCandidate_FirstName,"RecruitmentCandidate",sExcelPath);
        System.out.println(firstName+"Is written back to the RecruitmentCandidate sheet");
        String lastName=datafaker.name().lastName();
        ExcelUtils.setCellData(lastName,iRowNumber,Constant.sRecruitmentCandidate_LastName,"RecruitmentCandidate",sExcelPath);
        System.out.println(lastName+"is written back to RecruitmentCandidate sheet");
        String completeName=firstName+" "+lastName;
        driver.findElement(By.xpath("//input[@id='addCandidateForm_firstName']")).sendKeys(firstName);
        System.out.println("Candidate FirstName is entered");

        driver.findElement(By.xpath("//input[@id='addCandidateForm_lastName']")).sendKeys(lastName);
        System.out.println("Candidate LastName is entered");

        driver.findElement(By.xpath("//input[@id='addCandidateForm_email']")).sendKeys(""+firstName+""+lastName+""+"@gmail.com");
        System.out.println("Email is entered");
        String linkedInURL="https://www.linkedin.com/in/ravi-c-1b35b1124/";
        ExcelUtils.setCellData(linkedInURL,iRowNumber,Constant.sRecruitmentCandidate_LinkedInUrl,"RecruitmentCandidate",sExcelPath);
        System.out.println(linkedInURL+"is written back to RecruitmentCandidate sheet");
        driver.findElement(By.xpath("//input[@id='addCandidateForm_linkedin']")).sendKeys(linkedInURL);
        System.out.println("Linkedin URL is entered");

        driver.findElement(By.xpath("(//i[@class='oxd-icon oxd-icon--medium bi-caret-down-fill'])[2]")).click();
        System.out.println("Vacancy drop-down is clicked");

        String expectdVacancy=ExcelUtils.getCellData(iRowNumber,Constant.sRecruitmentCandidate_Vacancy,"RecruitmentCandidate");
        System.out.println(expectdVacancy+"is selected from RecruitmentCandidate sheet");
        List<WebElement> elements_Vacancies=driver.findElements(By.xpath("//div[@data-id='addCandidate_vacancy']/ul/div/li/a"));
        for(WebElement ele_Vacancy:elements_Vacancies){
            String actualVacancy=ele_Vacancy.getText();
            if(actualVacancy.equalsIgnoreCase(expectdVacancy)){
                ele_Vacancy.click();
                System.out.println(actualVacancy+" is selected from a drop-down");
                break;
            }
        }

        driver.findElement(By.cssSelector("#saveCandidateButton")).click();
        System.out.println("Save button is clicked");

        //Recruitment(ATS)
        String mainSession=driver.getWindowHandle();
        System.out.println("Main session window before clicking on List of employees"+mainSession);

        List<WebElement> ListOfEmp=driver.findElements(By.xpath("//table[@class='pagedata']/tbody/tr/td[3]"));
        for(WebElement element_candidateName:ListOfEmp){
            String candidateName=element_candidateName.getText();
            if(candidateName.equalsIgnoreCase(completeName)){
                element_candidateName.click();
                System.out.println(candidateName +"is clicked");
                break;
            }
        }
        System.out.println("Getting All Available window Handles");

        Set<String> allSessions=driver.getWindowHandles();
        for(String latestSession:allSessions){
            driver.switchTo().window(latestSession);
        }
        System.out.println("Window Switched to the latest Session ");

        //CandidateSession
        String recruitmentStage=ExcelUtils.getCellData(iRowNumber,Constant.sRecruitmentCandidate_RecruitmentStage,"RecruitmentCandidate");
        System.out.println(recruitmentStage+"is selected from RecruitmentCandidate sheet");
        driver.findElement(By.xpath("//label[text()='Current Stage of Recruitment']")).click();
        System.out.println("Click action performed on Current stage of recruitment");
        List<WebElement> RecruitmentStages=driver.findElements(By.xpath("//ul[contains(@id,'dropdownObjectSearch')][@class='dropdown-content dropdownObjectSearch active']/div/li/a/p"));
        System.out.println("RecruitmentStages size is:"+RecruitmentStages.size());
        Thread.sleep(1000);
        for(WebElement nextStage:RecruitmentStages){
            String NextStageText=nextStage.getText();
            System.out.println(NextStageText);
            if(NextStageText.equalsIgnoreCase(recruitmentStage)){
                nextStage.click();
                System.out.println("Click action is performed on "+NextStageText);
                break;
            }
        }

        driver.findElement(By.xpath("//a[@id='saveCandidateDetailsButton'][text()='Save']")).click();
        System.out.println("Click action is performed on Save Button");

        driver.close();
        System.out.println("Closing the Candidate Recruitment page");

        driver.switchTo().window(mainSession);
        System.out.println("Switched Back to the Main session");

        driver.navigate().refresh();
        System.out.println("Refreshed the Main session");

        driver.switchTo().defaultContent();
        System.out.println("Exited from the frame");

        JavascriptExecutor js=((JavascriptExecutor)driver);

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("noncoreIframe"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='addItemBtn']/i")));
        ListOfEmp=driver.findElements(By.xpath("//table[@class='pagedata']/tbody/tr/td[3]"));
        First: for(WebElement element_candidateName:ListOfEmp){
            js.executeScript("arguments[0].scrollIntoView(true);",element_candidateName);
            String candidateName=element_candidateName.getText();
            if(candidateName.equalsIgnoreCase(completeName)){
                String stage=element_candidateName.findElement(By.xpath("./../td[7]/a")).getText();
                if(stage.contains(recruitmentStage)){
                    System.out.println("Recruitment Stage is correctly updated");
                    String stageChanges=ExcelUtils.getCellData(iRowNumber,Constant.sRecruitmentCandidate_StageChanges,"RecruitmentCandidate");
                    System.out.println(stageChanges+"is selected from RecruitmentCandidate sheet");
                    WebElement element_stage=element_candidateName.findElement(By.xpath("./../td[7]/a"));
                    js.executeScript("arguments[0].click();",element_stage);
                    Thread.sleep(1000);
                    List<WebElement> elements_recruitmentStages=element_candidateName.findElements(By.xpath("./../td[7]/ul/li"));
                    Second: for(WebElement element_stageValue:elements_recruitmentStages){
                        stage=element_stageValue.getText();
                        if(stage.equalsIgnoreCase(stageChanges)){
                            element_stageValue.click();
                            System.out.println("Candidate stage changed to "+stageChanges);
                            break First;
                        }
                    }
                }else{
                    System.out.println("Recruitment Stage is not correctly updated");
                    throw new Exception();
                }
            }
        }

        System.out.println("Recruitment testcase execution is successful");

        CommonMethods_OrangeHRM.logout_orangeHRM();

        DriverUtils.closeBrowser();
    }
}
