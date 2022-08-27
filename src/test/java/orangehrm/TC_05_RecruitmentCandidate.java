package orangehrm;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class TC_05_RecruitmentCandidate {
    public static void main(String[] args) throws Exception{

        System.setProperty("webdriver.chrome.driver", "C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver","C:\\Anitha\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver_win32_1\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String path = System.getProperty("user.dir");
        System.out.println("Project Path is :" + path);
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
        boolean isLoginSuccessful = driver.findElement(By.xpath("//i[@class='material-icons'][text()='oxd_home_menu']")).isDisplayed();
        if (isLoginSuccessful) {
            System.out.println("Login is successful");
        } else {
            System.out.println("Login is not successful");
            throw new Exception();
        }

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));

        //Recruitment(ATS)
        driver.findElement(By.xpath("(//span[text()='Recruitment (ATS)'])[1]")).click();
        System.out.println("Recuritment(ATS) link is clicked");

        //Addbutton
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("noncoreIframe"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='addItemBtn']/i")));
        driver.findElement(By.xpath("//a[@id='addItemBtn']/i")).click();
        System.out.println("Add button is clicked");

        driver.findElement(By.xpath("//form[@id='frmAddCandidate']/div[1]/div[1]/div[1]")).click();
        System.out.println("SELECT Resume button is clicked");

        driver.findElement(By.xpath("//form[@id='frmAddCandidate']/div[1]/div[1]/div[1]/input")).sendKeys(path+"\\TestData\\TestResume.docx");
        driver.findElement(By.xpath("//form[@id='frmAddCandidate']/div[1]/div[1]/div[1]")).submit();
        System.out.println("Candidate Resume is uploaded");


        String firstName="Ravi";
        String lastName="BCDE";
        String completeName=firstName+" "+lastName;
        driver.findElement(By.xpath("//input[@id='addCandidate_firstName']")).sendKeys(firstName);
        System.out.println("Candidate FirstName is entered");

        driver.findElement(By.xpath("//input[@id='addCandidate_lastName']")).sendKeys(lastName);
        System.out.println("Candidate LastName is entered");

        driver.findElement(By.xpath("//input[@id='addCandidate_email']")).sendKeys("ravikiran@gmail.com");
        System.out.println("Email is entered");

        driver.findElement(By.xpath("//input[@id='addCandidate_linkedin']")).sendKeys("https://www.linkedin.com/in/ravi-c-1b35b1124/");
        System.out.println("Linkedin URL is entered");

        driver.findElement(By.cssSelector("#textarea_addCandidate_vacancy")).click();
        System.out.println("Vacancy drop-down is clicked");

        String expectdVacancy="Software QA Engineer";

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
        String recruitmentStage="Shortlisted";
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
                    WebElement element_stage=element_candidateName.findElement(By.xpath("./../td[7]/a"));
                    js.executeScript("arguments[0].click();",element_stage);
                    Thread.sleep(1000);
                    List<WebElement> elements_recruitmentStages=element_candidateName.findElements(By.xpath("./../td[7]/ul/li"));
                    Second: for(WebElement element_stageValue:elements_recruitmentStages){
                        stage=element_stageValue.getText();
                        if(stage.equalsIgnoreCase("Hired")){
                            element_stageValue.click();
                            System.out.println("Candidate stage changed to Hired");
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

        driver.findElement(By.xpath("//span[text()='Log Out']")).click();
        System.out.println("Logged out from the OrangeHRM application");

        driver.quit();
    }
}
