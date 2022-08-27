package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class StaleElementDemo {
    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.amazon.in/");
        driver.findElement(By.xpath("//a[text()='Mobiles']")).click();
        System.out.println("Click action is performed on Mobiles link");
        driver.findElement(By.xpath("//span[text()='Last 30 days']")).click();
        System.out.println("Click action is performed on Last 30 days link");

        String parentSession=driver.getWindowHandle();
        System.out.println("Main Window session id before click is "+parentSession);

        for(int i=0;i<=2;i++){
            try{
                driver.findElement(By.xpath("(//div[@data-component-type='s-search-result'])[1]//h2/a/span[text()]")).click();
                System.out.println("Click action is performed on First search result");
                break;
            }catch(StaleElementReferenceException se){
                Thread.sleep(1000);
                System.out.println("Stale Element Reference Exception occurred while clicking on the result link");
            }catch (Exception e){
                e.printStackTrace();
                System.err.println(e);
                System.exit(0);
            }
        }

        driver.switchTo().window(parentSession) ;
        System.out.println("Switched back to the Main Session");
        driver.close();

        Set<String> allSessions=driver.getWindowHandles();
        System.out.println("Total number of acive sessions are :"+allSessions);
        for(String latestSession:allSessions){
            driver.switchTo().window(latestSession);
        }
        System.out.println("Switched to the latest window");

        driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
        System.out.println("Click action is performed on Add Cart button");

       /* driver.close();
        driver.switchTo().window(parentSession);
        System.out.println("Switched back to the Main Session");

        driver.findElement(By.xpath("//span[text()='Under ₹1,000']")).click();
        System.out.println("Click action is performed on Under ₹1,000 link"); */


    }

}
