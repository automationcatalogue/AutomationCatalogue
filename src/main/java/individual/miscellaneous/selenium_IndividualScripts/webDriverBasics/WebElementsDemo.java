package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebElementsDemo {
    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://automatecatalog-trials7401.orangehrmlive.com/auth/login");
        driver.manage().window().maximize();

        WebElement firstNameElement=driver.findElement(By.xpath("//input[@id='txtUsername']"));

        firstNameElement.sendKeys("Sagar");
        Thread.sleep(1000);
        firstNameElement.clear();
        Thread.sleep(1000);
        firstNameElement.sendKeys("Admin");

        driver.findElement(By.name("txtPassword")).sendKeys("Admin@1234");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(4000);

        boolean found=driver.findElement(By.xpath("//li[text()='Dashboard']")).isDisplayed();
        if(found){
            System.out.println("Login is successful");
        }else{
            System.out.println("Login is unsuccessful");
        }

        driver.navigate().to("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("Cricket");
        driver.findElement(By.name("q")).submit();


    }
}
