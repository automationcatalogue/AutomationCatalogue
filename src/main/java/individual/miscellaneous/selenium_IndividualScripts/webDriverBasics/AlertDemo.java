package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertDemo {
    public static void main(String[] args) throws  Exception{
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/alerts");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//button[@id='promtButton']")).click();
        Alert alertData=driver.switchTo().alert();
        alertData.dismiss();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@id='promtButton']")).click();

        alertData=driver.switchTo().alert();
        alertData.sendKeys("AlertTesting");
        Thread.sleep(2000);
        alertData.accept();
    }
}
