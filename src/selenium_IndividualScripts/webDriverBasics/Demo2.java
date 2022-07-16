package selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

public class Demo2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new OperaDriver();
        driver.get("https://automatecatalog-trials7401.orangehrmlive.com/auth/login");
        By b1 = By.id("txtUsername");
       // WebElement ele=driver.findElement();

        driver.manage().window().maximize();

        WebDriver.Options option=driver.manage();
        WebDriver.Window window=option.window();
        window.maximize();



    }
}
