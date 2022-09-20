package individual.miscellaneous.selenium_IndividualScripts.javaPrograms;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShotDemo {
    public static void main(String[] args) throws Exception {
        String path =System.getProperty("user.dir");
        System.out.println("Project path is : "+path);
        System.setProperty("webdriver.chrome.driver","C:\\Anitha\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver_win32_1\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
         driver.get("http://demowebshop.tricentis.com/");
        TakesScreenshot ts=(TakesScreenshot)driver;
        File src=ts.getScreenshotAs(OutputType.FILE);
        File destination= new File(path+"\\src\\main\\java\\individual.miscellaneous\\selenium_IndividualScripts\\javaPrograms\\screenshots\\dws.jpg");
        FileUtils.copyFile(src,destination);
        System.out.println("DemoWebShop Screeshot is captured");
    }
}
