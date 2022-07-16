package selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class OrangeHRMDemo {
    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        driver.get("https://automatecatalog-trials7401.orangehrmlive.com/auth/login");
        driver.manage().window().maximize();
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        String tagName=driver.findElement(By.id("txtUsername")).getTagName();
        System.out.println(tagName);

        Point p=driver.findElement(By.id("txtUsername")).getLocation();
        System.out.println(p.getX());
        System.out.println(p.getY());

        Dimension d=driver.findElement(By.id("txtUsername")).getSize();
        System.out.println(d.getHeight());
        System.out.println(d.getWidth());


        driver.findElement(By.name("txtPassword")).sendKeys("Admin@123");
        driver.findElement(By.xpath("//*[@id='divLogin']/div[2]/div/form/div[3]/button")).click();

        driver.findElement(By.className("circle black-text")).click();
    }
}
