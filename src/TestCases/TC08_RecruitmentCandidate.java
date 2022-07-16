package TestCases;
import Utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TC08_RecruitmentCandidate {
    public static void main(String[] args) throws Exception{

        String projectPath=System.getProperty("user.dir");
        System.out.println("Project Path is :"+projectPath);

        Yaml yaml_Config = new Yaml();
        FileInputStream fis_Config = new FileInputStream(projectPath+ Constants.yaml_filePath);
        HashMap<String, String> map_config= (HashMap<String, String>) yaml_Config.load(fis_Config);

        String driverPath= map_config.get("chromeDriverExefilepath");
        System.out.println("ChromeDriver Path is :"+driverPath);

        //Initialize WebDriver
        System.setProperty("webdriver.chrome.driver",driverPath);
        WebDriver driver = new ChromeDriver();

        String sImplicitTimeout=map_config.get("implicitTimeOut");
        Long lImplitTimeout=Long.parseLong(sImplicitTimeout);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(lImplitTimeout));
        System.out.println("Implicit Timeout of"+lImplitTimeout+" is applied on Driver object");

        String applicationURL=map_config.get("orangehrmURL");
        driver.get(applicationURL);
        System.out.println(applicationURL+" is launched");

        driver.manage().window().maximize();

        //userName
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        System.out.println("Admin is entered as a UserName");

        //password
        driver.findElement(By.name("txtPassword")).sendKeys("Admin@123");
        System.out.println("Password is entered");

        //Login button
        driver.findElement(By.xpath("//*[@id='divLogin']/div[2]/div/form/div[3]/button")).click();
        System.out.println("Login button is clicked");

        //Recruitment(ATS)
        driver.findElement(By.xpath("(//a[@data-automation-id='menu_recruitment_Recruitment(ATS)']/span)[1]")).click();
        System.out.println("Recuritment(ATS) link is clicked");

        //Addbutton
        driver.findElement(By.xpath("//a[@id='addItemBtn']/i")).click();
        System.out.println("Add button is clicked");

        //Add Candidate
        driver.findElement(By.cssSelector("#addCandidate_resume")).click();
        System.out.println("SELECT Resume button is clicked");

        driver.findElement(By.cssSelector("#addCandidate_resume")).sendKeys(projectPath+"\\TestData\\TestResume.docx");
        driver.findElement(By.cssSelector("#addCandidate_resume")).submit();
        System.out.println("Candidate Resume is uploaded");

        driver.findElement(By.xpath("//input[@id='addCandidate_firstName']")).sendKeys("Ravi");
        System.out.println("Candidate FirstName is entered");

        driver.findElement(By.xpath("//input[@id='addCandidate_lastName']")).sendKeys("Kiran");
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

        String GivenName="Anitha P";
        String mainSession=driver.getWindowHandle();
        System.out.println("Main session window before clicking on List of employees"+mainSession);

        List<WebElement> ListOfEmp=driver.findElements(By.xpath("//table[@class='pagedata']/tbody/tr/td[3]"));
        for(WebElement ListOfCandidates:ListOfEmp){
            String CandidateList=ListOfCandidates.getText();
            if(CandidateList.equalsIgnoreCase(GivenName)){
                ListOfCandidates.click();
                System.out.println(CandidateList +"is clicked");
                break;
            }
        }

        Set<String> allSessions=driver.getWindowHandles();
        for(String latestSession:allSessions){
            driver.switchTo().window(latestSession);
        }

        System.out.println("Window Switched to the lastest Session "+GivenName);

        //CandidateSession

        driver.findElement(By.xpath("//label[text()='Current Stage of Recruitment']")).click();
        System.out.println("Click action performed on Current stage of recruitment");
        List<WebElement> RecruitmentStages=driver.findElements(By.xpath("//ul[@id='dropdownObjectSearch_g6myw']/div/li/a/p"));
        for(WebElement nextStage:RecruitmentStages){
            String NextStageText=nextStage.getText();
            if(NextStageText.equalsIgnoreCase("Hired")){
                nextStage.click();
            }
        }

        driver.findElement(By.xpath("//p//input[@type='checkbox']/../label")).click();
        System.out.println("click sction is performed on Consent chechbox");

        driver.findElement(By.xpath("//a[@id='saveCandidateDetailsButton'][text()='Save']")).click();
        System.out.println("Click action is performed on Save Button");

    }
}
