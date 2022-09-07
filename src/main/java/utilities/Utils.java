package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.time.Duration;
import java.util.List;

public class Utils {

    private static WebDriver driver;

    /**
     * This method is used to launch the browser based on browserName
     * @param browserName
     * @return WebDriver
     * @throws Exception
     * @autor sagar
     */
    public static WebDriver launchBrowser(String browserName) throws Exception{

        if(browserName.equalsIgnoreCase("Chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized"); //To Maximize the screen
            options.addArguments("--incognito"); // To open the browser in Incognito mode
            options.addArguments("--disable-popup-blocking");//To Disable the pop-up blocker
            options.addArguments("--ignore-certificate-errors");//To Ignore the certificate related errors
            options.setAcceptInsecureCerts(true);// To accept the websites which are not having security certificate
            driver = new ChromeDriver(options);
        }else if(browserName.equalsIgnoreCase("Firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("Opera")){
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        }else if(browserName.equalsIgnoreCase("Edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }else {
            throw new Exception("Invalid browserName is provided");
        }
        System.out.println(browserName+" browser is launched");
        driver.manage().window().maximize();
        System.out.println(browserName+" is maximized");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println("WebDriver implicitly timeout configured with 20 seconds of time");
        return driver;
    }

    /**
     * This method is used to select the drop down without select tag
     * @param locator
     * @param expData
     * @author sagar
     */
    public static void selectDropdown_withoutSelectTag(By locator, String expData){
        List<WebElement> elements_list=driver.findElements(locator);
        for(WebElement element:elements_list){
            String actualData=element.getText();
            if(actualData.equalsIgnoreCase(expData)){
                element.click();
                System.out.println(expData+ " is selected from the drop-down");
                break;
            }
        }
    }
}
