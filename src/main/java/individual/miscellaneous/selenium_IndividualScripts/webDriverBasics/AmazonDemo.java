package individual.miscellaneous.selenium_IndividualScripts.webDriverBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonDemo {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\AutomationCatalogue\\Drivers\\Chrome\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();

       // driver.findElement(By.className("nav-line-2 nav-progressive-content")).click();
        String txt=driver.findElement(By.xpath("//div[@id='desktop-grid-2']/div/div/h2")).getText();
        System.out.println(txt);

        String value=driver.findElement(By.xpath("//div[@id='desktop-grid-2']/div/div/h2")).getAttribute("class");
        System.out.println(value);

        String csstext=driver.findElement(By.xpath("//div[@id='desktop-grid-2']/div/div/h2")).getCssValue("font-size");
        System.out.println(csstext);

    }
}
