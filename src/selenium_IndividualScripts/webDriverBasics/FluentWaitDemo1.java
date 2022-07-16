package selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.function.Function;

public class FluentWaitDemo1 {
    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://automatecatalog-trials7401.orangehrmlive.com/auth/login");
        WebElement userelement=driver.findElement(By.xpath("//input[@id='txtUsername']"));
        userelement.sendKeys("Admin");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Admin@123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("Login button is clicked");
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='Add Employee']")).click();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver ob){
                WebElement element= ob.findElement(By.xpath("//label[text()='Employee Full Name']"));
                String element_text=element.getText();
                System.out.println(element_text);
                if(element_text.equalsIgnoreCase("Employee Full Name*")){
                    return true;
                }else{
                    return false;
                }
            }
        });

        driver.findElement(By.xpath("//input[@id='first-name-box']")).sendKeys("India");
    }

}
