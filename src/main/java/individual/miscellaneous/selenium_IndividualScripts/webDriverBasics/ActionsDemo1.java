package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class ActionsDemo1 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://demowebshop.tricentis.com/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        Actions action = new Actions(driver);
        action.click(driver.findElement(By.linkText("Log in"))).build().perform();
        System.out.println("Login link is clicked");

        action.sendKeys("aarosagarch@gmail.com").perform();
        System.out.println("Email is entered");
        action.sendKeys(driver.findElement(By.id("Password")),"Admin@123").build().perform();
        action.click(driver.findElement(By.id("Password"))).sendKeys("Admin@123").build().perform();

        System.out.println("Password is entered");


    }
}
