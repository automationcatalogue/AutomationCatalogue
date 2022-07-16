package selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrameDemo {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.manage().window().maximize();
        //WebDriver.TargetLocator t= driver.switchTo();
        //t.frame(0);
        //driver.switchTo().frame("login_page");

        WebElement ele=driver.findElement(By.xpath("//frame[@name='login_page']"));
        driver.switchTo().frame(ele);
        System.out.println("Switched into the frame");

        driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("385734538");
        driver.findElement(By.linkText("CONTINUE")).click();
    }
}
