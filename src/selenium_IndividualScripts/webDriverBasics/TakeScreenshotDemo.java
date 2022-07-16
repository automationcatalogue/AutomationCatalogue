package selenium_IndividualScripts.webDriverBasics;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class TakeScreenshotDemo {
    public static void main(String[] args) throws Exception{

        String path=System.getProperty("user.dir");
        System.out.println("path is :"+path);


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-popup-blocking");//To Disable the pop-up blocker
        options.addArguments("--ignore-certificate-errors");//To Ignore the certificate related errors
        options.addArguments("--headless");

        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.get("https://testcatalogue-trials7501.orangehrmlive.com/");
        TakesScreenshot ts=(TakesScreenshot)driver;
        File src=ts.getScreenshotAs(OutputType.FILE);
        File destination= new File(path+"\\screenshots\\oragngeHRM_Homepage.jpg");
        FileUtils.copyFile(src, destination);
        System.out.println("Orange HRM Screenshot is captured and kept in screenshots folder");


        driver.findElement(By.id("txtUserdasdaname")).sendKeys("Admin");
        driver.findElement(By.name("txtPassword")).sendKeys("Admin@123");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        Thread.sleep(6000);
        src=ts.getScreenshotAs(OutputType.FILE);
        destination= new File(path+"\\screenshots\\oragngeHRM_LoginPage.jpg");
        FileUtils.copyFile(src, destination);
        System.out.println("Orange HRM Screenshot Login is captured and kept in screenshots folder");
    }
}
