package selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitDemo1 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement ele=driver.findElement(By.name("login_page"));
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("login_page")));
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
        System.out.println("Switched into the frame");

        driver.findElement(By.xpath("//div[@class='inputfield ibvt loginData']/input")).sendKeys("46456456");
        driver.findElement(By.xpath("//div[@class='inputfield ibvt loginData']/input")).clear();
        System.out.println("userID data is cleared");
        //driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
        //System.out.println("Clicked on Continue button and waiting for alert to be present");
        //wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert is appeared");
        Alert alert=driver.switchTo().alert();
        alert.accept();
        System.out.println("alert is clicked");

    }
}
