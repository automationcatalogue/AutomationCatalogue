package selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitDemo3 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

        driver.get("http://demowebshop.tricentis.com/");
        driver.findElement(By.partialLinkText("Computers")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[contains(@title,'Show products in category Desktops')]")));

        driver.findElement(By.xpath("//img[contains(@title,'Show products in category Desktops')]")).click();
        //wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//strong[text()='Filter by price']")), "Filter by price"));
        driver.findElement(By.xpath("(//input[@value='Add to cart'])[1]")).click();

        //wait.until(ExpectedConditions.titleContains("Demo Web Shop. Desktops"));
        //wait.until(ExpectedConditions.urlContains("http://demowebshop.tricentis.com/build-your-cheap-own-computer"));

        driver.findElement(By.xpath("//div[@class='add-to-cart-panel']//input[2]")).click();

    }
}
