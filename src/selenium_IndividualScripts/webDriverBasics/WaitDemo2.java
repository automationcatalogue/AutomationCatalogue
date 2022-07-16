package selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitDemo2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

        driver.get("https://automatecatalog-trials7401.orangehrmlive.com/auth/login");
        WebElement userelement=driver.findElement(By.xpath("//input[@id='txtUsername']"));
        userelement.sendKeys("Admin");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Admin@123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement loginElement=driver.findElement(By.xpath("//button[@type='submit']"));
        //wait.until(ExpectedConditions.elementToBeClickable(loginElement));
        System.out.println("Login button is appreared and it is clickable");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("Login button is clicked");

        //wait.until(ExpectedConditions.invisibilityOf(userelement));
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[text()='PIM']")));

        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        driver.findElement(By.xpath("//span[text()='Add Employee']")).click();
    }
}
